package baseball2Youngyooon.controller;

import baseball2Youngyooon.domain.Computer;
import baseball2Youngyooon.domain.Result;
import baseball2Youngyooon.domain.Token;
import baseball2Youngyooon.view.InputView;
import baseball2Youngyooon.view.OutputView;

public class Controller {
	Computer computer = Computer.getInstance();

	public void runGame() {
		computer.init();
		boolean gameStatus = true;

		while (gameStatus) {
			OutputView.printInputInstruction();
			Result result = computer.matchBalls(InputView.getInput());

			OutputView.printResult(result.report());
			gameStatus = askRestart(gameStatus, result);
		}
	}

	private boolean askRestart(boolean gameStatus, Result result) {
		if (result.is3Strike()) {
			OutputView.printRestartInstrunction();
			//5. ? Token 타입을 만듬. -> 사용자입력을 유효성 검증해서 뱉음.
			// restartGame = InputView.getInput();
			Token token = Token.from(InputView.getInput());
			//6. 원시타입을 포장해서 class에 가두면, get으로 직접못꺼내보고 내부에서 처리된 결과값만 보내야한다.
			// restartGame = token.number;
			// 7. number를 꺼내보는게 아니라 get대신,, isXXX메소드를 만들어서 t/f를 반환하게 한다.
			gameStatus = token.isRestart();

			// 1.게임 종료상황에서 && 재시작할때 -> 게임의 컴퓨터 초기화해야함
			initComputerBall(gameStatus);
			// 2. 입력값을 1)길이, 2)포맷 3) 범위  4) 기타 validation도 해야함.
			// checkValidation(restartGame);
			

		}
		return gameStatus;
	}



	private void initComputerBall(boolean gameStatus) {
		if (gameStatus == true) {
			computer.init();
		}
	}

}
