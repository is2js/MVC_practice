package lotto1ByEdu2.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto1ByEdu2.utils.Util;

public class LottoFactory {
	private static final LottoFactory lottoFactory = new LottoFactory();
	// 메인로직에 사용되는 insert될 객채(혹은 list), 혹은 결과값(을 뽑아낼 수있음 생략) 변수와 객체를, 싱글톤이 관리.
	//private Cars cars;
	private int count;
	EnumCounter counter;
	private LottoNumbers winnerNumbers;
	private List<LottoNumbers> lottoNumbersList;
	private Integer bonus;

	// 2) 싱글톤 private생성자는 비워둠
	private LottoFactory() {
	}

	//3) 외부에서 이미생성된 싱글톤을 부르는 스태틱메서드
	public static LottoFactory getInstance() {
		return lottoFactory;
	}

	//4) 관리하는 insert된 변수들, 결과값 변수들 초기화
	// TODO singleton2) 메서드에 필요 -> 관리변수 -> 매 로직시마다 초기화도 가능하도록 한다.
	public void init() {
		// TODO: 로직용 관리할 변수, 결과변수 생길시마다 초기화넣어주기
		// this.cars = new ArrayList<>();
		count = 0;
		LottoWinRank.winCount = 0;
		counter = new EnumCounter();
		winnerNumbers = null;
		lottoNumbersList = null;
		bonus = null;

	}

	public List<LottoNumbers> generateAutoNumbersWithCount(LottoCount lottoCount) {
		count = lottoCount.toInt();
		return IntStream.range(0, count)
			.mapToObj(i -> Util.generateLottoNumbers())
			.collect(Collectors.toList());
	}

	// public EnumCounter compare(LottoNumbers winnerNumbers, List<LottoNumbers> lottoNumbersList) {
	public EnumCounter compare() {
		for (LottoNumbers lottoNumbers : lottoNumbersList) {
			//TODO: 당첨갯수를 반환 -> 당첨Enum List를 반환받음.
			// -> 로또별로 map에 넣자.map에 넣자?
			int count = lottoNumbers.compare(winnerNumbers);
			LottoWinRank rank = LottoWinRank.findBycount(count);
			// 추가11. 분기별 결과값이 Enum에 추가되었으면,
			// -> 그 놈에 대한 plusCount할때 조절해줘야한다.
			if (rank.isFive() && lottoNumbers.isContains(this.bonus)) {
				System.out.println("5개 맞췄찌만, 보너스를 포함 -> 2등으로 간주합니다.");
				rank = LottoWinRank.FIVE_BONUS;

			}
			counter.plusCount(rank);
		}
		return counter;
	}

	public double calculateProfit() {
		// 추가9. coutnerMap내부 계산시 bonus도 필요해서 건네준다.
		// return this.counter.calculateProfit();
		return this.counter.calculateProfit(bonus);
	}

	public int calculateProfitInt() {
		return this.counter.calculateProfitInt();
	}

	public int calculatePurchase() {
		return this.lottoNumbersList.size() * 1000;
	}

	public void insert(LottoNumbers winnerNumbers, List<LottoNumbers> lottoNumbersList, int bonus) {
		this.winnerNumbers = winnerNumbers;
		this.lottoNumbersList = lottoNumbersList;
		this.bonus = bonus;
	}
}
