package baseball2Youngyooon.domain;

public class Number {
	public static final int MIN_NUMBER = 1;
	public static final int MAX_NUMBER = 9;
	//2. 원시타입 private으로 선언
	private int number;

	public Number(int number) {
		//3. 검증은 생성자에서 하는데, 복잡하니 메서드로
		checkValidNumber(number);
		this.number = number;
	}

	private void checkValidNumber(int number) {
		//4. 길이, 포맷은 이미 검증되어 숫자로 들어오나보다.
		// -> 범위, 중복여부
		if (!isValidRange(number)) {
			throw new IllegalArgumentException("1~9사이 숫자를 입력하세요");
		}
	}

	private boolean isValidRange(int number) {
		return MIN_NUMBER <= number && number <= MAX_NUMBER;
	}

	//5. 원시속성 선언 -> 생성자에서 검증 -> equals 오버라이딩
	// == 가 사용중인데, 정의안해주면 꺼내고 꺼내서, 안꺼내고 객체.equals메서드가 처리하도록 비교되도록
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
