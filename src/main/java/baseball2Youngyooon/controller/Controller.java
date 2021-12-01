package baseball2Youngyooon.controller;

import baseball2Youngyooon.domain.Computer;
import baseball2Youngyooon.domain.Result;
import baseball2Youngyooon.view.InputView;
import baseball2Youngyooon.view.OutputView;

public class Controller {
	public void runGame() {

		Computer computer = Computer.getInstance();
		computer.init();

		while (true) {
			OutputView.printInputInstruction();
			Result result = computer.matchBalls(InputView.getInput());
			//1. 메인로직의 결과도 OutputView에서 뿌려주도록 -> 결과객체X 결과String만 전달
			OutputView.printResult(result.report());
			//2. 매번 업데이트될 결과값변수(조건변수) result에 대해 if () {break;}절에서
			// -> 조건변수(업데이트 되는 결과값 변수)로 1) break;조건 찾고  .equals() or   ==    -> 2) 메소드로 if조건절내용을  클래스안에서 메소드()로 리팩토링
			// -> String으로 찾기보다는, 숫자, 특히 갯수로 파악할 수 있음 그걸로 하는데,
			// -> 결과변수도 타클래스라서 관련 숫자뽑으려면 result.get써야한다면? -> 메세지를 보내자(보내서 t/f로 받자 -> isXX) -> 필요한 값을 파라미터로 주든지해서(상수아니면), 가지고 가서 Class내에서 메소드를 정의하자. 자체해결임.
			// -> 외부에서 ctrl+ alt + M하면, 외부에 함수가 생기니 조심 -> [객체 자체 확인가능한 조건절이면, 객체Class 내부에서 처리되도록 변수는 파라미터로 넘겨서 처리한다]
			// -> result객체. 메소드로()로 작성해놓고 메소드에서 리팩토링하자. 객체도 묶어서 리팩토링하면, 외부 거기서 메소드 작성됨.
			// if(result.getStrikeSum() ==3 ) {
			if(result.is3Strike()) {
				break;
			}

		}

	}


}
