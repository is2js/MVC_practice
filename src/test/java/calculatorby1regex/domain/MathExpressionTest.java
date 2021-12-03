package calculatorby1regex.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MathExpressionTest {

	@Test
	void name() {
		MathExpression mathExpression = new MathExpression(new String[] {"1", "2", "3"});
		System.out.println((mathExpression.getNumbers()));

	}
}
