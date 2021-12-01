package baseball2Youngyooon.controller;

import baseball2Youngyooon.domain.Computer;
import baseball2Youngyooon.domain.Result;
import baseball2Youngyooon.view.InputView;
import baseball2Youngyooon.view.OutputView;

public class Controller {
	public void runGame() {

		Computer computer = Computer.getInstance();
		computer.init();

		//1. 업데이트되는 결과값변수-> 조건변수는 while문 위로 빼서 초기화하자.
		// -> 할수있다면, 조건을 while ( !조건 )으로 옮겨보자. -> 결과값변수를 초기화할 수가.... Balls vs Balls 비교해야 들어가는거라
		// -> 못옮긴다면, 업데이트 직후 -> if break로 위치시킨다.

		//3. status변수는 일단 while문위에서 true로 초기화 해놓고 -> while문에 넣는다.
		// -> true도 상수화할 예정임.
		boolean gameStatus = true;
		// while (true) {
		while (gameStatus) {
			OutputView.printInputInstruction();
			Result result = computer.matchBalls(InputView.getInput());
			OutputView.printResult(result.report());
			//2. while true -> if 의 2 indent를 줄이려면, if절을 boolean함수화
			// -> status변수로서, 위에서 true로 초기화하고, 사용자에게 break에 해당하는 입력( while(false))을 받던지, 게임결과로 break에 해당하는 입력을 바게 한다.
			// if (result.is3Strike()) {
			// 	break;
			// }
			//4. while문을 멈추게하는 [status에 false 넣기]를 업데이트되는 결과값변수 -> 결과값변수.is그만둬야되지?() -> 그 때 false로 들어가도록 짠다.
			// 1) result.is3Strike() -> true일 때 break되어야함
			// 2) !result.is3Strike() -> falses가됨. -> status변수에 넣기
			gameStatus = !result.is3Strike();
			
		}

	}

}
