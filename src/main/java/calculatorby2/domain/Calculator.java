package calculatorby2.domain;

import java.util.Iterator;
import java.util.List;

public class Calculator {

	public static Integer calculate(List<Integer> operands, List<String> operators) {
		Iterator<String> iterOperators = operators.iterator();
		Iterator<Integer> iterOperands = operands.iterator();

		Integer firstValue = iterOperands.next();

		while (iterOperators.hasNext()) {
			String operator = iterOperators.next();
			Integer secondValue = iterOperands.next();
			// -> 1) 문자열 가지고 -> <finder>를 통해, 내부 분기별처리로 -> <개별enum 중 1개로 특정 or 안되면 예외발생>되게 한다.
			// -> 2) [내부 분기처리되서 특정된 개별enum]  .operation()  으로  <분기별 람다식>을 사용할수 있게, 메서드를 정의한다

			// 1. 이넘에 작성한 스태틱finder로 람다식 가져오기
			// -> 결과는 다형성으로서, Enum Type에 담긴다.
			OperateType operateType = OperateType.finderOperator(operator);
			// 2. 내부에 담긴 연산자타입으로, 람다식을 쓸수있게 정의한 메서드를 호출함.
			firstValue = operateType.operation(firstValue, secondValue);
		}
		return firstValue;
	}
}
