package baseball.view;

public class OutputView {

	public static final String INPUT_NUMBER_INSTRUCTION = "숫자를 입력해주세요 : ";
	public static final String THREE_STRIKE_ENDING_MENT = "3개의 숫자를 모두 맞히셨습니다!게임 종료";
	public static final String RESTART_INSTRUNCTION = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";

	public static void printInstruction() {
		System.out.print(INPUT_NUMBER_INSTRUCTION);
	}

	public static void printEndInstruction() {
		System.out.println(THREE_STRIKE_ENDING_MENT);
		System.out.println(RESTART_INSTRUNCTION);
	}
}
