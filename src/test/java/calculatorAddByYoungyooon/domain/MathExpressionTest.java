package calculatorAddByYoungyooon.domain;

import java.util.List;

import org.junit.jupiter.api.Test;

class MathExpressionTest {

	@Test
	void extractIntNumbers_test() {
		// int sum = MathExpression.extractIntNumbers("//,\n3,2,1");
		// int sum2 = MathExpression.extractIntNumbers("1:1:1");
		// int sum3 = MathExpression.extractIntNumbers("1:1:-1");
		// System.out.println(sum);
		// List<Integer> integers = MathExpression.extractIntNumbers("//,\n3,2,1");
		List<Integer> integers = MathExpression.extractIntNumbers("//k\n1k2k3");
		System.out.println(integers);

	}
}
