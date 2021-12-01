package baseball2Youngyooon.controller;

import baseball2Youngyooon.domain.Computer;
import baseball2Youngyooon.domain.Result;
import baseball2Youngyooon.view.InputView;
import baseball2Youngyooon.view.OutputView;

public class Controller {
	//1. 재시작을 새롭게 runGame()한다는 개념 -> 모든 게임마다 계속떠있는 싱글톤 computer객체는..
	// -> 게임을 돌리는 것보다 더 바깥에 떠있어야한다.
	// my)싱글톤객체는 게임돌아가는 것보다 훨씬 먼저 생성, 더 윗단계에 있다.
	// -> 게임마다 처리하려고, 싱글톤이지만, init()메소드를 정의한 것 같다.ㄴ
	Computer computer = Computer.getInstance();
	//computer.init(); // 이것 매 게임마다 시작할때 해줘야하는 것..?

	public void runGame() {
		// 매 게임마다 싱글톤객체가 초기화해줘야하는 부분을 초기화해준다.
		computer.init();

		// boolean gameStatus = true;
		//4. 아무리 생각해도.. 리게임여부는... runGame()안에서 판단해야할 것 같다.
		// -> restart도 true초기화 status변수를 활용한다.
		// -> while조건문에 직접적으로 넣어야한다.
		String restartGame = "1";
		//게임결과로 바로 종료가 아니라면, while문의 조건절은 사용자입력을 받는 문자열Status변수에게 양보
		// while (gameStatus) {
		while (restartGame.equals("1")) {
			OutputView.printInputInstruction();
			Result result = computer.matchBalls(InputView.getInput());

			OutputView.printResult(result.report());
			// gameStatus = !result.is3Strike();
			//5. 게임끝났을때 추가분기문 때문에 어쩔수없이 if문이..?
			restartGame = askRestart(restartGame, result);
		}
	}

	private String askRestart(String restartGame, Result result) {
		if (result.is3Strike()) {
			OutputView.printRestartInstrunction();
			restartGame = InputView.getInput();
		}
		return restartGame;
	}

}
