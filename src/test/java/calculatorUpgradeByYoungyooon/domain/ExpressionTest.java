package calculatorUpgradeByYoungyooon.domain;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class ExpressionTest {

	@Test
	void 정규표현식_연습() {
		String[] split = "0++1-3*3".split("[0-9]");
		boolean b = Arrays.stream(split)
			.anyMatch(op -> op.length() > 1);
		System.out.println(b);
	}
}
