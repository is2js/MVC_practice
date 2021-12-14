package lotto2ByMe.domain;

import java.util.Arrays;

public enum LottoPrize {
	// 외부사용: Enum.findBycount(더 쉬운변수) -> Enum객체 -> Enum객체.getprize;
	// -> Prize.findBycount( int1 ).getPrize() -> int2
	MISS(0, 0),
	ONE(1, 0),
	TWO(2, 0),
	THREE(3, 5000),
	FOUR(4, 50_000),
	FIVE(5, 1_500_000),
	BONUS(5, 3_000_000),
	SIX(6, 2_000_000_000);

	private final int count;
	private final int prize;
	// Enum안의 싱글톤 변수로 쓰려면, public static로 선언하고 -> 관리자 싱글톤 init()에서도 초기화 해준다.
	// public static int count;

	LottoPrize(int count, int prize) {
		this.count = count;
		this.prize = prize;
	}

	public static LottoPrize findByCount(Integer count) {
		return Arrays.stream(LottoPrize.values())
			.filter(e -> e.count == count)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("해당하는 값이 없습니다."));
	}

	public int toPrize() {
		return this.prize;
	}

	public int getCount() {
		return this.count;
	}

	public boolean isBonus() {
		return this.equals(LottoPrize.BONUS);
	}
}
