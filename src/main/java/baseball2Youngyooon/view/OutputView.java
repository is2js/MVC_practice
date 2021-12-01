package baseball2Youngyooon.view;

public class OutputView {
	public static void printInputInstruction() {
		System.out.print("숫자를 입력해주세요 : ");
	}

	public static void printResult(String report) {
		System.out.println(report);
	}

	public static void printRestartInstrunction() {
		System.out.println("재시작 하시겠습니까? 1:재시작 2: 종료");
	}
}
