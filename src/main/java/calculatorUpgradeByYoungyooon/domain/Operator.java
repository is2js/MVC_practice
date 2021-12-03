package calculatorUpgradeByYoungyooon.domain;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Operator {
	PLUS("+", (a, b) -> (a + b)),
	MINUS("-", (a, b) -> (a - b)),
	MULTIPLY("*", (a, b) -> (a * b)),
	DIVIDE("/", (a, b) -> (a / b));

	private String operator;
	private BiFunction<Integer, Integer, Integer> bifunction;

	Operator(String operator,
		BiFunction<Integer, Integer, Integer> bifunction) {
		this.operator = operator;
		this.bifunction = bifunction;
	}

	public static Operator findOperator(String s) {
		return Arrays.stream(Operator.values())
			.filter(e -> e.operator.equals(s))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("연산자가 없습니다."));
	}

	public Integer calculate(Integer a, Integer b) {
		return bifunction.apply(a, b);
	}
}
