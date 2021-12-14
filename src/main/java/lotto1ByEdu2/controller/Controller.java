package lotto1ByEdu2.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lotto1ByEdu2.domain.EnumCounter;
import lotto1ByEdu2.domain.LottoCount;
import lotto1ByEdu2.domain.LottoFactory;
import lotto1ByEdu2.domain.LottoNumbers;
import lotto1ByEdu2.domain.ManualLottoCount;
import lotto1ByEdu2.domain.Money;
import lotto1ByEdu2.view.InputView;
import lotto1ByEdu2.view.OutputView;

public class Controller {
	public void runGame() {
		//메인로직
		// 1. 싱글톤 관리자 -> 로또 Factory? 로또 Machine?
		LottoFactory lottoFactory = LottoFactory.getInstance();
		lottoFactory.init();

		// 2. 입력받은 것마다 래핑하기
		OutputView.printPurchaseInstruction();
		String moneyString = InputView.getInput();
		Money money = Money.of(moneyString);
		LottoCount lottoCount = LottoCount.of(money);

		// 추가1. 수동 구매수 입력해야함.
		OutputView.printInputManualLottoCount();
		ManualLottoCount manualLottoCount = ManualLottoCount.of(InputView.getManualCount(),
			lottoCount);//TODO: 래핑클래스에서 범위를..  구매금액 -> 최대로또갯수보다는 작게.. 추가체크해줘야함..

		OutputView.printInputManualInstruction();

		OutputView.printCountInstruction(lottoCount, manualLottoCount);

		// 3. 자동생성, 컴퓨터생성은 싱글톤관리자가 한다.
		List<LottoNumbers> lottoNumbersList = lottoFactory.generateAutoNumbersWithCount(lottoCount);

		OutputView.printLottoNumbers(lottoNumbersList);
		List<LottoNumbers> manualLottoNumbersList = IntStream.range(0, manualLottoCount.toInt())
			.mapToObj(i -> {
				return LottoNumbers.from(InputView.getWinnerNumbers());
			})
			.collect(Collectors.toList());
		// 추가2. list합치기는 배열합치기랑 좀 다른가보다.
		// -> 배열과 달리 각각을 stream으로 바로 만든다.
		// Stream<List<LottoNumbers>> lottoNumbersList1 = Stream.of(lottoNumbersList, lottoNumbersList);
		// Stream<List<LottoNumbers>> listStream = lottoNumbersList1.flatMap(Stream::of);
		List<LottoNumbers> lottoNumbersListTotal = Stream.concat(lottoNumbersList.stream(),
				manualLottoNumbersList.stream())
			.collect(Collectors.toList());

		// 4. 당첨번호 입력받기
		OutputView.printInputWinNumbersIntruction();
		//5. 뭐야.. 우승번호 < 로또번호에 포함된 것...
		// -> 클래스 따로 만들필요없잖아....
		// winnerNumbers winnerNumbers = winnerNumbers.from(InputView.getWinnerNumbers());
		LottoNumbers winnerNumbers = LottoNumbers.from(InputView.getWinnerNumbers());

		//추가5. 보너스볼 입력받기
		OutputView.printInputBonusInstruction();
		int bonus = InputView.getBonus();

		// Map<Integer, Integer> countPerCorrectNumber = new TreeMap<>();
		// map에 밸류가 안차도 꺼내야하므로, default값 0을 밀어넎어 놓는다?
		// Arrays.stream(LottoWinRank.values())
		// 	.forEach(ev -> countPerCorrectNumber.put(ev.getCount(), 0));

		// WinnerCount winnerCount = new WinnerCount();
		// System.out.println("경기전" + winnerCount);

		// 추가3. 수동까지 합친 리스트를 보낸다.
		// lottoFactory.insert(winnerNumbers, lottoNumbersList);
		// lottoFactory.insert(winnerNumbers, lottoNumbersListTotal);
		// 추가6. 보너스넘버도 넘긴다.
		lottoFactory.insert(winnerNumbers, lottoNumbersListTotal, bonus);
		// EnumCounter counter = lottoFactory.compare(winnerNumbers, lottoNumbersList);
		EnumCounter counter = lottoFactory.compare();
		System.out.println(counter);

		//TODO: 계산1) 나눗셈이 포함되는 순간, [나누기직전에] 1개를 int -> double로 바꾸기 위해, mapToInt 를 mapToDouble로 반환했다.
		// -> double이 되는 순간 format은 %.0f를 default(소수점미표기)로 %.2f등 소수점 까지 명시해주자.
		// --> int %d (double의 d아님!!), 소수는 %f

		double profit = lottoFactory.calculateProfit();
		int purchase = lottoFactory.calculatePurchase();
		double profitTotal = ((profit - purchase) / purchase) * 100;

		//TODO: 계산2) [나누기 직전]에 둘다 BigDecimal.valueOf로 바꾸고, .divide .multiply로 계산후
		// -> print는 %.f로 해주기 // %f안붙이고 그냥 프린트하면 정수는 정수 / 소수점은 4자리까지 나오는듯?
		int profitInt = lottoFactory.calculateProfitInt();
		BigDecimal result = BigDecimal.valueOf(profitInt - purchase).divide(BigDecimal.valueOf(purchase)).multiply(
			BigDecimal.valueOf(100));
		// System.out.println("BigDecimal로 계산 >>" + result);

		System.out.printf("총 수익률(이익%.0f, 나간것%d)은 수익률 %.2f 입니다.", profit, purchase, profitTotal);
		//String.format("%.2f", yield)  부분 포맷스트링
	}
}
