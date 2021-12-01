package calculatorby2.controller;

import java.util.List;

import calculatorby2.domain.Calculator;
import calculatorby2.domain.Token;
import calculatorby2.view.InputView;
import calculatorby2.view.OutputView;

public class Controller {
	public void runCalculator() {
		OutputView.printInputInstruction();
		Token token = Token.from(InputView.getInput());
		List<Integer> operands = token.getOperands();
		List<String> operators = token.getOperators();

		Integer result = Calculator.calculate(operands, operators);
		OutputView.printResult(result);
	}
}
