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

	// 개별 사용은 Enum.작성메서드로 ->  XXXoperator함수형인페는 .apply()를 호출하는  람다식.apply()가 반환되도록 작성해서 사용한다.
	// -> 사용은 [Enum.개별이넘값]이 this.(객체)처럼 쓰여진다. ->  개별 이넘값을 탐색되어서 사용되어야할듯싶다.
	// -> 1) 문자열 가지고 -> <finder>를 통해, 내부 분기별처리로 -> <개별enum 중 1개로 특정 or 안되면 예외발생>되게 한다.
	// --> 더 아래 finder
	// -> 2) [내부 분기처리되서 특정된 개별enum]  .operation()  으로  <분기별 람다식>을 사용할수 있게, 메서드를 정의한다
	// --> here
	public Integer operation(int a, int b) {
		return this.operation.apply(a, b);
	}


	// if분기를 없애기 위해 만든, 내부 분기별처리용 finder메서드()에서 ->  유효성검증도 동시에 되게한다.
	// -> 없는 문자열값 들어오면  내부 분기검색하는 & 없으면 예외던지기 finder 작성하기
	// --> finder는 Enum속 유일 스태틱/공유메서드다.
	// --> 결과는 다형성으로서, Enum Type으로 반환함. 담긴다.
	public static OperateType finderOperator(String symbol) {
		//finder의 구성은
		// 1) values()로 [Enum들을 문자열??? -> Enum실제객체의  배열]로 만들고 -> stream으로 돌면서
		// 2) 유저입력값으로 filter검색한 뒤
		// 3) 하나라도 있으면, findAny -> filter에 걸린 enum객체 1개를 가져옴. -> 정의된 enum.매서드() or enum.상수값  쓰면 됨.
		// 4) 없으면 예외 orElseThrow( ( ) -> new ~ )
		return Arrays.stream( OperateType.values())
			.filter( e -> e.symbol.equals( symbol ))
			.findAny()
			.orElseThrow( () -> new IllegalArgumentException("not a arithmetic symbols"));
	}


}
