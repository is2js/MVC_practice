package trade2.domain;

public class Money {
	public static final String WRONG_FORMAT = "잘못된 형식을 입력하셨습니다.";
	public static final String WRONG_AMOUNT = "잘못된 금액을 입력하였습니다.";
	//1. 최종 반환될 int는 private하게 외부접근은 막아놓는다.
	private int amount;

	//2. string숫자를 생성자에서 받아서 처리한다.
	public Money(String money) {
		//0) 일단 문자열숫자 -> 진짜숫자로 받아줄 변수가 필요하다.
		// -> try catch안에서는 생성 안될수도있으니 미리 만들어놓고 받아주는 역할
		int parsedAmount;
		//1) parseInt를 도전한다. -> catch에서 넘버포맷익셉션을 잡는다.
		try {
			parsedAmount = Integer.parseInt(money);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(WRONG_FORMAT);
		}
		//2)에러나서 종료되지 않았으면 -> 진짜숫자를 받은 상태 -> 범위검사
		// -> **범위검사는 if not  continue느낌으로 틀린것일시 오류나서 종료시키는 식으로 한ㄴ다.**
		if (parsedAmount <= 0) {
			throw new IllegalArgumentException(WRONG_AMOUNT);
		}
		//3. 다 통과시 할당.
		this.amount = parsedAmount;
	}
	//4.
	@Override
	public String toString() {
		return Integer.toString(amount);
	}
}
