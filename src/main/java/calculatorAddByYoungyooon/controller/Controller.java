package calculatorAddByYoungyooon.controller;

import calculatorAddByYoungyooon.domain.Calculator;
import calculatorAddByYoungyooon.domain.MathExpression;
import calculatorAddByYoungyooon.view.InputView;
import calculatorAddByYoungyooon.view.OutputView;

public class Controller {
	public void runCalcultor() {
		//TODO: 메인로직
		// 0. 메인로직을 담당할 싱글톤 떠있는 model(Calculator 작성)후 싱글톤 객체 가져오기
		Calculator calculator = Calculator.getInstance();
		calculator.init(); // 아직 메인로직마다 초기화할 내용은 안들어가 있음.

		//2. 입력을 받고나서야 calculator가 메인로직이실행될거니..입력부터 처리한다.
		// String mathExpression = InputView.getInput();
		//3. 검증이 필요한 것은 객체로서 class로 포장한다.
		// -> 포장시, 1)private원시변수2)private생성자 with 검증된 것을 convert정도만 3)public static of/from객체생성기 with 검증후 생성자호출
		// -> 5) 객체로서, 객체끼리 비교를 위한 equals hashcode 오버라이딩, 혹은 다른비교함수 작성

		//5.
		OutputView.printInputInstruction();
		MathExpression mathExpression = MathExpression.from(InputView.getInput());

		Integer result = calculator.calculate(mathExpression);
		OutputView.printResult(result);

	}
}
