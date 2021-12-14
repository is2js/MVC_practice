package lotto2ByMe.view;

import lotto2ByMe.domain.LottoMachine;
import lotto2ByMe.domain.LottoNumbersList;
import lotto2ByMe.domain.Money;

public class OutputView {

	public static final String ERROR_INSTRUCTION = "[ERROR] ";
	public static final String INPUT_MONEY_INSTRUCTION = "구입금액을 입력해 주세요.";
	public static final String LOTTO_COUNT_INSTRUCTION = "%d개를 구매했습니다.";
	public static final String INPUT_WIN_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
	public static final String RESULT_MESSAGE_STATISTIC_PROFITS = "총 수익율은 %.1f%%입니다";
	public static final String INPUT_MANUAL_NUMBER_INSTRUCTION = "수동으로 구매할 로또 수를 입력해 주세요.";
	public static final String INPUT_MANUAL_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";
	public static final String INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";

	public static void printError(String message) {
		System.out.println(ERROR_INSTRUCTION + message);
	}

	public static void printInputMoneyInstruction() {
		System.out.println(INPUT_MONEY_INSTRUCTION);
	}

	public static void printLottoCount(Money money) {
		System.out.printf(LOTTO_COUNT_INSTRUCTION, money.toLottoCount());
		System.out.println();
	}

	public static void printLottos(LottoMachine lottoMachine) {
		System.out.println(lottoMachine);
	}

	public static void printInputWinNumbers() {
		System.out.println(INPUT_WIN_NUMBERS);
	}

	public static void printStatisticInstruction() {
		System.out.println("당첨 통계");
		System.out.println("---------");
	}

	public static void printStatisticResult(String countResultString) {
		System.out.println(countResultString);
	}

	public static void printStatisticProfits(double prizeTotal) {
		System.out.printf(RESULT_MESSAGE_STATISTIC_PROFITS, prizeTotal);
		System.out.println();

	}

	public static void printInputManualNumberInstruction() {
		System.out.println(INPUT_MANUAL_NUMBER_INSTRUCTION);
	}

	public static void printInputManualNumbers() {
		System.out.println(INPUT_MANUAL_NUMBERS);
	}

	public static void printResultOfCount(int manualCount, int autoCount) {
		System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.", manualCount, autoCount);
		System.out.println();
	}

	public static void printResultOfTotalLottoNumbers(LottoMachine lottoMachine) {
		LottoNumbersList totalLottoNumbersList = lottoMachine.toTotalLottoNumbersList();
		totalLottoNumbersList.toList().stream()
			.forEach(LottoNumbers -> System.out.println(LottoNumbers));
	}

	public static void printInputBonusNumber() {
		System.out.println(INPUT_BONUS_NUMBER);
	}
}
