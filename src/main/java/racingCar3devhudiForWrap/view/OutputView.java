package racingCar3devhudiForWrap.view;

public class OutputView {
	public static void printInputNameInstruction() {
		System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");

	}

	public static void printInputCountInstruction() {
		System.out.println("시도할 회수는 몇회인가요?");
	}

	public static void printError(String message) {
		System.out.println("[ERROR] " + message);
	}

	public static void printInputNumbers() {
		System.out.println("숫자들을 입력 테스트");
	}

	public static void printResultInstruction() {
		System.out.println("실행 결과");
	}

	public static void printEveryResult(String result) {
		System.out.println(result);
		System.out.println();
	}

	public static void printWinner(String winnerNames) {
		System.out.print("최종 우승자 : ");
		System.out.println(winnerNames);
	}
}
