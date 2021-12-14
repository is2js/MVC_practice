package lotto1ByEdu2.view;

import java.util.List;

import lotto1ByEdu2.domain.LottoCount;
import lotto1ByEdu2.domain.LottoNumbers;
import lotto1ByEdu2.domain.ManualLottoCount;

public class OutputView {

	public static final String PURCHASE_INSTRUCTION = "구입금액을 입력해 주세요.";
	public static final String COUNT_INSTRUCTION = "개를 구매했습니다.";
	public static final String INPUT_WIN_NUMBERS_INTRUCTION = "지난 주 당첨 번호를 입력해 주세요.";
	public static final String INPUT_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요. ";
	public static final String INPUT_MANUAL_INTRUCTION = "수동으로 구매할 번호를 입력해 주세요.";
	public static final String INPUT_BONUS_INSTRUCTION = "보너스 볼을 입력해 주세요.";

	public static void printPurchaseInstruction() {
		System.out.println(PURCHASE_INSTRUCTION);
	}

	public static void printCountInstruction(LottoCount lottoCount,
		ManualLottoCount manualLottoCount) {
		// System.out.println(lottoCount.toInt() + COUNT_INSTRUCTION);
		System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.", manualLottoCount.toInt(), lottoCount.toInt());
		System.out.println();
	}

	public static void printInputWinNumbersIntruction() {
		System.out.println(INPUT_WIN_NUMBERS_INTRUCTION);
	}

	public static void printLottoNumbers(List<LottoNumbers> lottoNumbers) {
		for (LottoNumbers lottoNumber : lottoNumbers) {
			System.out.println(lottoNumber.toList());
		}
		System.out.println();
	}

	public static void printError(String message) {
		System.out.println("[ERROR] " + message);
	}

	public static void printInputManualLottoCount() {
		System.out.print(INPUT_MANUAL_LOTTO_COUNT);
	}

	public static void printInputManualInstruction() {
		System.out.println(INPUT_MANUAL_INTRUCTION);
	}

	public static void printInputBonusInstruction() {
		System.out.println(INPUT_BONUS_INSTRUCTION);
	}
}
