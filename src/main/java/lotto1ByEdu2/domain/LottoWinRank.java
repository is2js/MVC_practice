package lotto1ByEdu2.domain;

import java.util.Arrays;

public enum LottoWinRank {
	ZERO(0, 0),
	ONE(1, 0),
	TWO(2, 0),
	THREE(3, 5000),
	FOUR(4, 50_000),
	FIVE(5, 1_500_000),
	FIVE_BONUS(5, 1_700_000),
	SIX(6, 2_000_000_000);
	// BONUS(5, 3_000_000),

	private final int count;
	private final int Prize;
	public static int winCount; // Enum안의 싱글톤 변수로 쓰려면, public static로 선언하고 -> 관리자 싱글톤 init()에서도 초기화 해준다.

	LottoWinRank(int count, int Prize) {
		this.count = count;
		this.Prize = Prize;
	}

	public static LottoWinRank findBycount(int count) {
		return Arrays.stream(LottoWinRank.values())
			.filter(e -> e.count == count)
			.findFirst()
			.orElse(LottoWinRank.ZERO);
		// .orElseThrow(() -> new IllegalArgumentException("해당 당첨내역이 없습니다."));
	}

	public int getPrize() {
		return this.Prize;
	}

	public int getCount() {
		return count;
	}

	public boolean isFive() {
		System.out.println("5개 맞춘 상태.. 보너스는?");
		return this == LottoWinRank.FIVE;
	}
}
