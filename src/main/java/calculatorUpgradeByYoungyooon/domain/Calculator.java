package calculatorUpgradeByYoungyooon.domain;

import java.util.List;

public class Calculator {
	private static Calculator calculator = new Calculator();
	private Integer result;

	private Calculator() {
	}

	public static Calculator getInstance() {
		return calculator;
	}

	public void init() {
		result = 0;
	}

	public Integer calculate(Expression expression) {
		//9. 이제 공백제거후, 검증된 식이 들어오므로, 숫자[기호]숫자[기호]숫자 식으로  숫자별 기호별 추출한다.
		// -> 다시 한번 숫자를 제거한 것 -> operators
		// -> 기호를 제거한 것 -> operands 로 나눠야한다.
		// -> replaceAll + 정규식으로 제거한 것은 반복되므로.. 유틸로 뺀다.

		List<String> operators = expression.getOperators();
		List<Integer> operands = expression.getOperands();

		//10. 계산은 1번째 숫자만 result에 넣어두고, 연산자 - 숫자 세트로 놀면서 result를 업데이트 해나간다.
		// -> operator마다 매핑된 식을 사용하기 위해, Enum 및 finder 정의해서 사용한다.
		// --> 나같은 경우 싱글톤이 result를 관리하게 했으니 거기에 넣어놓고 계산하게 한다.
		// --> init()에서 새 계산시 result = 0;확인
		result = operands.get(0);
		// System.out.println("초기값>> " + result);
		// 숫자는 1번째index부터, 연산자는 그  -1인 0부터 시작하게 한다.
		for (int i = 1; i <= operands.size() - 1; i++) {

			// operators.get(i-1) , operands.get(i)
			// -> 11. 연산자 기호마다 람다식을 매핑해서 꺼내오기 위해, enum을 생성한다.
			// --> enum의 변수들은 들어오는 값이 변하닌, final을 붙이지말자.
			// --> enum의 finder는 enum객체가 나오므로 -> 람다식을 빼쓸거면 enum객체가 쓰도록 static안붙인다.
			// --> enum은 finder만 스태틱메서드로 만든다.
			Operator operator = Operator.findOperator(operators.get(i - 1));
			result = operator.calculate(result, operands.get(i));

			//TODO: 삭제
			// System.out.println("계산중>> " + operator.name() + "  "+ result);
		}

		// System.out.println("최종>>" + result);
		return result;

	}
}
