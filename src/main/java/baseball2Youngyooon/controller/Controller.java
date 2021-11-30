package baseball2Youngyooon.controller;

import baseball2Youngyooon.domain.Computer;
import baseball2Youngyooon.domain.Result;
import baseball2Youngyooon.view.InputView;
import baseball2Youngyooon.view.OutputView;

public class Controller {
	public void runGame() {

		//1.
		// 메인로직 용 거의 컨트롤러 Computer싱글톤 객체를 일단 받아와서 초기화해주자.
		Computer computer = Computer.getInstance();
		computer.init();
		//2. inputView를 스태틱으로 사용해서 싱글톤으로 작성하자.

		//3.
		// InputView.getInput();


		//4. 이제 반복해서 받아야하니, while문을 작성한다.
		while (true) {
			// if () {
			// 	break;
			// }
			//7. 인풋대기전에 안내문을 스태틱으로 ㅁ나들어주자.
			OutputView.printInputInstruction();
			Result result = computer.matchBalls(InputView.getInput());
			System.out.println((result.report()));

		}


	}
}
