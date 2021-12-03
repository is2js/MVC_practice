package racingCar1Youngyoon.view;

public class OutputView {

	public static final String ERROR_MESSAGE = "[ERROR] ";

	public static void printInputNameInstruction() {
		System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
		// 경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)
		// pobi,woni,jun
		// 시도할 회수는 몇회인가요?
	}

	public static void printNullError() {
		System.out.println(ERROR_MESSAGE + "빈칸이면 입력하면 안된다.");
	}

	public static void printOverLength() {
		System.out.println(ERROR_MESSAGE + "이름은 5자 이하여야 한다.");
	}

	public static void printNameNullError() {
		System.out.println(ERROR_MESSAGE + "각 이름은 빈칸이면 안된다.");
	}
}
