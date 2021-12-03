package calculatorAddByYoungyooon.domain;

public class Calculator {
	//1. 메인로직을 수행할 싱글톤 객체는 클래스안에서 일단 생성해놓느다.
	// + 추가로 싱글톤객체도 하나의 인스턴스로서, init()전까지 관리할 인스턴스변수를 가질 수 있다. ex> private Balls balls
	private static Calculator calculator = new Calculator();

	//2. 생성자는 private ->
	// 1) 생성된 싱글톤을 가져오는 public static 클래스 getInstance() 메소드 +
	// 2) public void init() 메서드를 가진다.
	private Calculator() {
	}

	public static Calculator getInstance() {
		return calculator;
	}

	public void init() {
		//TODO: 계산마다 초기화 필요시
	}

	//21.
	public Integer calculate(MathExpression mathExpression) {
		return mathExpression.getAdd();
	}
}
