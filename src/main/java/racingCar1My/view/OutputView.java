package racingCar1My.view;

import java.util.List;

public class OutputView {

	public static final String ERROR_MESSAGE = "[ERROR] ";

	public static void printInputNameInstruction() {
		System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
		// 경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)
		// pobi,woni,jun
		// 시도할 회수는 몇회인가요?
	}

	public static void printError(IllegalArgumentException e) {
		System.out.println(ERROR_MESSAGE + e);
	}

	public static void printInputCountInstruction() {
		System.out.println("시도할 회수는 몇회인가요?");
	}

	public static void printResult(List<String> results) {
		for (String result : results) {
			System.out.println(result);
		}
	}

	public static void printWinners(List<String> winnerNames) {
		System.out.println("최종 우승자 : " + String.join(",", winnerNames));
	}
}
