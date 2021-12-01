package calculatorby2.domain;

import java.util.Arrays;
import java.util.function.BinaryOperator;

public enum OperateType {
	DIVIDE("/", (a, b) -> a / b),
	MINUS("-", (a, b) -> a - b),
	MULTIPLY("*", (a, b) -> a * b),
	PLUS("+", (a, b) -> a + b);

	private String symbol;
	private BinaryOperator<Integer> operation;

	OperateType(String symbol, BinaryOperator<Integer> operation) {
		this.symbol = symbol;
		this.operation = operation;
	}

	public Integer operation(int a, int b) {
		return this.operation.apply(a, b);
	}

	public static OperateType finderOperator(String symbol) {
		return Arrays.stream( OperateType.values())
			.filter( e -> e.symbol.equals( symbol ))
			.findAny()
			.orElseThrow( () -> new IllegalArgumentException("not a arithmetic symbols"));
	}


}
