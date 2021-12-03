package calculatorUpgradeByYoungyooon.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import calculatorUpgradeByYoungyooon.utils.Util;

public class Expression {
	private String expression;

	private Expression(String expresion) {
		this.expression = expresion;
	}

	public static Expression from(String input) {
		//검증 [[직전]] (한글자씩 입력 ->  .split("")으로 배열-> stream() 만들어야하는데! 공백이 껴버리면 제거해놓는다 )
		//1. 공백이 같이 낄 수 있다면, replaeAll을 활용한다.
		String removedSpaceInput = removeSpace(input);
		//2. checkXXX검증을 시작한다.
		checkValidation(removedSpaceInput);

		return new Expression(removedSpaceInput);
	}

	private static Expression checkValidation(String removedSpaceInput) {
		//3. 첫입력글자 - 숫자 를 판단할 때는, 무지성 string.charAt(0)를 이용 && 1~9 한글자범위는 char ''숫자매직을 이용한다.
		// -> 첫글자(무지성.charAt(0))가  0~9사이의 숫자(포맷) -> char '0' <= &&  <= '9' 매직
		// -> Charactter. isDigit를 때려도되지만, char매직을 이용해서도 포맷을 검사할 수 있다!!
		checkFirstValue(removedSpaceInput);
		checkLastValue(removedSpaceInput);
		//5. 대박1)연산자들 검증은 -> 정규식으로 기호(연산자)들만 추출해서, 검사해야한다.
		// ->정규식으로 뽑는 것보다, **정규식을 이용한 replace로 삭제가 더 빠르다!!**
		// -> 숫자들을 제외하고 뽑거나, 숫자들을 삭제한 것으로 추출하면 됨.
		checkOperators(removedSpaceInput);
		//7. 대박2) 연산자들의 중복(2번연속입력)을 검사하는 방법
		// 1) 정규식split()으로 [0-9]숫자제거하며 split해서 연산자들만 남긴다.
		// -> 맨앞이 숫자라  빈 배열도 포함된다.
		// 2) 연산자들이 배열로 남게되는 와중에, 연산자가 2번 연속입력되면, length가 1이 아닌 2이 이상으로 filter에 걸린다.
		// -> but filter는 골라서 모으기 원함이고, anyMatch로 t/f를 반환받을 수 있어, 조건식에 넣을 수 있다.
		// -> my) 조건식에 넣을 stream은 anyMatch로 끝난다~
		checkOperatorDuplicates(removedSpaceInput);
		//8. 문자열계산식에서 0으로 나눌경우는 /0이 입력된 경우임 -> 입력을 contains하고 있으면 thr
		checkDivideByZero(removedSpaceInput);

		return new Expression(removedSpaceInput);

	}

	private static void checkDivideByZero(String removedSpaceInput) {
		if (removedSpaceInput.contains("/0")) {
			throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
		}
	}

	private static void checkOperatorDuplicates(String removedSpaceInput) {
		if (Arrays.stream(removedSpaceInput.split("[0-9]")).anyMatch(operator -> operator.length() > 1)) {
			throw new IllegalArgumentException("연산자를 연속해서 입력하셨습니다. 연산자 다음에는 숫자를 입력해주세요.");
		}
	}

	private static void checkOperators(String removedSpaceInput) {
		String operators = Util.removeDigit(removedSpaceInput, "[0-9]");
		//6. 남은 연산자들이 4개 중 1개 인지 검사해야한다.
		// -> 4개를 삭제 -> 다른 기호가 남아있다면 == ""빈스트링 아니라면, 잘못된 기호가 있는 것
		operators = Util.removeDigit(operators, "[\\+|\\*|/|-]");
		if (!operators.equals("")) {
			throw new IllegalArgumentException("잘못된 연산자가 포함되어있습니다.");
		}

	}

	private static void checkLastValue(String removedSpaceInput) {
		char lastValue = removedSpaceInput.charAt(removedSpaceInput.length() - 1);
		if (!('0' <= lastValue && lastValue <= '9')) {
			throw new IllegalArgumentException("마지막 입력은 숫자여야합니다.");
		}
	}

	private static void checkFirstValue(String removedSpaceInput) {
		char firstValue = removedSpaceInput.charAt(0);
		if (!('0' <= firstValue && firstValue <= '9')) {
			throw new IllegalArgumentException("첫번째 입력은 숫자여야합니다.");
		}
	}

	private static String removeSpace(String expresion) {
		return expresion.replaceAll(" ", "");
	}

	public List<String> getOperators() {
		// 10. 주의할 점 : 숫자가 맨 앞에 있어서 빈""문자열로 포함되니 filter로 제거한다.
		return Arrays.stream(this.expression.split("[0-9]"))
			.filter(op -> !op.trim().isEmpty())
			.collect(Collectors.toList());
	}

	public List<Integer> getOperands() {
		// 11. filter후 list (or reduce.get or sum 등의 집계) 가 필요없으면 굳이 split(Array)를 stream으로 만들필요는 없다 바로 lits로
		//12. but 생각해보니 convert가 필요함.
		// return Arrays.asList(this.expression.split("[\\+|\\*|/|-]"));
		return Arrays.stream(this.expression.split("[\\+|\\*|/|-]"))
			.mapToInt(Integer::parseInt)
			.boxed()
			.collect(Collectors.toList());

	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Expression that = (Expression)o;
		return Objects.equals(expression, that.expression);
	}

	@Override
	public int hashCode() {
		return Objects.hash(expression);
	}

	@Override
	public String toString() {
		return "Expression{" +
			"expression='" + expression + '\'' +
			'}';
	}
}
