package baseball2Youngyooon.controller;

import baseball2Youngyooon.domain.Computer;
import baseball2Youngyooon.domain.Result;
import baseball2Youngyooon.view.InputView;
import baseball2Youngyooon.view.OutputView;

public class Controller {
	Computer computer = Computer.getInstance();

	public void runGame() {
		computer.init();
		String restartGame = "1";

		while (restartGame.equals("1")) {
			OutputView.printInputInstruction();
			Result result = computer.matchBalls(InputView.getInput());

			OutputView.printResult(result.report());
			restartGame = askRestart(restartGame, result);
		}
	}

	private String askRestart(String restartGame, Result result) {
		if (result.is3Strike()) {
			OutputView.printRestartInstrunction();
			restartGame = InputView.getInput();
			// 1.게임 종료상황에서 && 재시작할때 -> 게임의 컴퓨터 초기화해야함
			initComputerBall(restartGame);
			// 2. 입력값을 1)길이, 2)포맷 3) 범위  4) 기타 validation도 해야함.
			checkValidation(restartGame);
			

		}
		return restartGame;
	}

	private void checkValidation(String restartGame) {
		checkValidLength(restartGame);
		checkValidFormat(restartGame);
		checkValidRange(restartGame);

	}

	private void checkValidRange(String restartGame) {
		if (!isValidRange(restartGame)) {
			throw new IllegalArgumentException("1 혹은 2를 입력해주세요.");
		}
	}

	private boolean isValidRange(String restartGame) {
		return restartGame.equals("1") || restartGame.equals("2");
	}

	private void checkValidFormat(String restartGame) {
		char character = restartGame.toCharArray()[0];
		if (isDigit(character)) {
			throw new IllegalArgumentException("숫자를 입력해주세요");
		}
	}

	private boolean isDigit(char c) {
		return !(Character.isDigit(c));
	}

	private void checkValidLength(String restartGame) {
		if (!isValidLength(restartGame)) {
			throw new IllegalArgumentException("1글자만 입력해주세요");
		}
	}

	private boolean isValidLength(String restartGame) {
		return restartGame.length() ==1;
	}

	private void initComputerBall(String restartGame) {
		if (restartGame.equals("1")) {
			computer.init();
		}
	}

}
