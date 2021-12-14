package lotto2ByMe.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lotto2ByMe.domain.Count;
import lotto2ByMe.domain.LottoMachine;
import lotto2ByMe.domain.LottoNumber;
import lotto2ByMe.domain.LottoNumbers;
import lotto2ByMe.domain.LottoNumbersList;
import lotto2ByMe.domain.Money;
import lotto2ByMe.view.InputView;
import lotto2ByMe.view.OutputView;

public class Controller {
	public void runLotto() {
		// 메인로직
		LottoMachine lottoMachine = LottoMachine.getInstance();
		lottoMachine.init();

		OutputView.printInputMoneyInstruction(); // 구입금액을 입력해 주세요.
		Money money = Money.of(InputView.getMoney());

		OutputView.printInputManualNumberInstruction(); //수동으로 구매할 로또 수를 입력해 주세요.
		Count manualCount = Count.of(InputView.getManualCount(money.toLottoCount()));
		OutputView.printInputManualNumbers(); //수동으로 구매할 번호를 입력해 주세요.

		Count autoCount = Count.of(money.toLottoCount() - manualCount.toInt());

		OutputView.printResultOfCount(manualCount.toInt(), autoCount.toInt()); //수동으로 3장, 자동으로 11개를 구매했습니다.

		List<LottoNumbers> tempList = new ArrayList<>();

		//카운팅볏누로 쓰면.. 내부에서 변하기 때문에 소모되어버린다ㅣ..ㅋㅋ
		while (manualCount.isEnd()) {
			tempList.add(getWinNumbers());
		}

		LottoNumbersList manualLottoNumbers = LottoNumbersList.from(tempList);

		// OutputView.printLottoCount(money); // 14개를 구매했습니다.

		// lottoMachine.insert(money);
		// 추가. 수동넘버도 관리자에게 넘긴다.
		lottoMachine.insert(money, manualLottoNumbers);
		lottoMachine.generateAutoLottoNumbersList(autoCount);
		OutputView.printResultOfTotalLottoNumbers(lottoMachine);//[44, 30, 12, 17, 20, 9]

		OutputView.printInputWinNumbers(); //지난 주 당첨 번호를 입력해 주세요.
		LottoNumbers winNumbers = getWinNumbers();
		// lottoMachine.insert(winNumbers);
		OutputView.printInputBonusNumber(); //보너스 볼을 입력해 주세요.
		LottoNumber bonusNumber = LottoNumber.of(InputView.getNumber());
		lottoMachine.insert(winNumbers, bonusNumber);

		// 본게임 -> 바로 통계
		OutputView.printStatisticInstruction();
		String CountResultString = lottoMachine.matchingLotto();
		double prizeTotal = lottoMachine.calculatePrize();
		OutputView.printStatisticResult(CountResultString);
		OutputView.printStatisticProfits(prizeTotal);

	}

	private LottoNumbers getWinNumbers() {
		return LottoNumbers.from(InputView.getWinNumbers().stream()
			.mapToInt(Integer::parseInt)
			.mapToObj(winNumer -> LottoNumber.of(winNumer))
			.collect(Collectors.toList()));
	}
}
