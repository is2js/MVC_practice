package racingCar2YoungyoonForStream.view;

import java.util.List;

public class OutputView {
	public static void printError(IllegalArgumentException e) {
		System.out.println("[ERROR] " + e);
	}

	public static void printWinner(List<String> winnersName) {
		System.out.println(String.join(",", winnersName));
	}

	public static void printInputNameInstruction() {
		System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
	}

	public static void printInputCountInstruction() {
		System.out.println("시도할 회수는 몇회인가요?");
	}

	public static void printRoundInfo(String roundInfo) {
		System.out.println(roundInfo);
	}
}
