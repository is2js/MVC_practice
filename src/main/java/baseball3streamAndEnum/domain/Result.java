package baseball3streamAndEnum.domain;

import java.util.Arrays;

public enum Result {
	STRIKE("스트라이크"),
	BALL("볼"),
	NOTHING("낫씽");

	private final String name;

	Result(String name) {
		this.name = name;
	}

	public static Result findByName(String name) {
		return Arrays.stream(Result.values())
			.filter(e -> e.name.equals(name))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("해당하는 값이 없습니다."));
	}

	public boolean isStrike() {
		// enum에서 this는 상수객체를 의미한다.
		// finder없다면 isXXXX 는   this == ENum.상수 로 확인하다.
		// System.out.println(findByName("스트라이크")+ "조회중");
		// System.out.println("this" +  this);
		// System.out.println("findByName(\"스트라이크\")" + findByName("스트라이크"));
		// System.out.println("this == findByName(\"스트라이크\")" + (this == findByName("스트라이크")));
		// System.out.println("this == Result.STRIKE)" + (this == Result.STRIKE));
		// System.out.println("this == STRIKE)" + (this == STRIKE));
		return this == findByName("스트라이크");
	}

	public boolean isBall() {
		// System.out.println(findByName("볼") + "조회중");
		// System.out.println("this" +  this);
		// System.out.println("findByName(\"볼\")" + findByName("볼"));
		// System.out.println("this == findByName(\"볼\")" + (this == findByName("볼")));
		// System.out.println("this == Result.BALL)" + (this == Result.BALL));
		// System.out.println("this == BALL)" + (this == BALL));
		return this == findByName("볼");
	}
}
