package lotto2ByMe.domain;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lotto2ByMe.utils.Util;

public class LottoMachine {
	private static final LottoMachine lottoMachine = new LottoMachine();
	private Money money;
	private LottoNumbersList autoLottoNumbersList;
	private LottoNumbers winNumbers;
	private WinnerCounter winnerCounter;
	private LottoNumbersList manualLottoNumbersList;
	private LottoNumber bonusNumber;
	// 메인로직에 사용되는 insert될 객채(혹은 list), 혹은 결과값(을 뽑아낼 수있음 생략) 변수와 객체를, 싱글톤이 관리.
	//TODO singleton1)  외부에서 관리자가 메인로직을 실행할 때, << 필요한 값을 받아 함수를 만드는데 >>>
	// -> 필요한 것 =  함수의 파라미터로 들어오는 것 -> 관리하는 변수로 취급해서 선언한다.
	//private Cars cars;

	// 2) 싱글톤 private생성자는 비워둠
	private LottoMachine() {
	}

	//3) 외부에서 이미생성된 싱글톤을 부르는 스태틱메서드
	public static LottoMachine getInstance() {
		return lottoMachine;
	}

	//4) 관리하는 insert된 변수들, 결과값 변수들 초기화
	// TODO singleton2) 메서드에 필요 -> 관리변수 -> 매 로직시마다 초기화도 가능하도록 한다.
	public void init() {
		this.money = null;
		this.autoLottoNumbersList = null;
		this.winNumbers = null;
		this.winnerCounter = new WinnerCounter();
		this.manualLottoNumbersList = null;
		this.bonusNumber = null;
	}

	public void generateAutoLottoNumbersList(Count autoCount) {
		// List<LottoNumbers> collect = IntStream.range(0, this.money.toLottoCount())
		this.autoLottoNumbersList = LottoNumbersList.from(IntStream.range(0, autoCount.toInt())
			.mapToObj(i -> createLottoNumbers())
			.collect(Collectors.toList()));
	}

	private LottoNumbers createLottoNumbers() {
		return LottoNumbers.from(IntStream.range(0, 6)
			.mapToObj(i -> LottoNumber.of(Util.generateLottoNumber()))
			.collect(Collectors.toList()));
	}

	public void insert(Money money, LottoNumbersList manualLottoNumbers) {
		this.money = money;
		this.manualLottoNumbersList = manualLottoNumbers;
	}

	@Override
	public String toString() {
		return this.autoLottoNumbersList.toList().stream()
			.map(LottoNumbers::toString)
			.collect(Collectors.joining(System.lineSeparator()));
	}

	public void insert(LottoNumbers winNumbers) {
		this.winNumbers = winNumbers;
	}

	public LottoNumbersList toTotalLottoNumbersList() {
		return LottoNumbersList.from(
			Stream.concat(
				this.manualLottoNumbersList.toList().stream(),
				this.autoLottoNumbersList.toList().stream()
			).collect(Collectors.toList()));
	}

	public void insert(LottoNumbers winNumbers, LottoNumber bonusNumber) {
		this.winNumbers = winNumbers;
		this.bonusNumber = bonusNumber;
	}

	public String matchingLotto() {
		Results results = this.toTotalLottoNumbersList().match(this.winNumbers, this.bonusNumber);

		//1. 결과값 countermap에 갯수 기록 ( this.map을 쓸수 있고, 관리안할 결과값list나 추출된 순간)
		results.toCounterMap(this.winnerCounter);

		return reportResults(results); // 2. 문자열로 바꿔서 반환하기
	}

	public String reportResults(Results results) {
		// 당첨갯수(결과값) -> 상금을 매핑할 Enum -> Enum마다 카운트해줄 counterMap을 만든다.
		// winnerCounter.insertResultFrom(results);
		// map에 결과값List 전체를 insert하지말고, 싱글톤관리자 내부에서, inserted된 변수 활용해서
		// 관리자내부에서 for + 개별 결과값1개를 매번 입력해주는 식으로 한다.

		return winnerCounter.convertToString();

	}

	public double calculatePrize() {
		double prizeSum = this.winnerCounter.calculate();
		return prizeSum / this.money.toInt();
	}

}
