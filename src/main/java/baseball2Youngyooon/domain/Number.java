package baseball2Youngyooon.domain;

public class Number {
	public static final int MIN_NUMBER = 1;
	public static final int MAX_NUMBER = 9;

	private int number;

	public Number(int number) {
		checkValidNumber(number);
		this.number = number;
	}

	private void checkValidNumber(int number) {
		if (!isValidRange(number)) {
			throw new IllegalArgumentException("1~9사이 숫자를 입력하세요");
		}
	}

	private boolean isValidRange(int number) {
		return MIN_NUMBER <= number && number <= MAX_NUMBER;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj ==null || obj.getClass() != this.getClass()) {
			return false;
		}
		Number number1 = (Number)obj;
		return this.number == number1.number;
	}
}
