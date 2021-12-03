package calculatorby1regex.domain;

import java.util.List;

public class Calculator {
	// 1. run메서드 돌아갈 동안, 계속 먼저 떠있을 & 핵심로직을 가진놈.. ->  거의 컨트롤러 객체
	// -> 재시작한다면, 매번 새로생성ex> 랜덤번호생성 등은  init()만 해주면 된다.
	// -> baseball2처럼, static 싱글톤으롬 만들어보자.
	private static Calculator calculator = new Calculator();

	private Calculator() {
	}

	public static Calculator getInstance() {
		return calculator;
	}

	public void init() {

	}

	public Integer calcuate(List<Integer> numbers) {
		return numbers.stream().reduce((a, b) -> a + b).get();
	}
}
