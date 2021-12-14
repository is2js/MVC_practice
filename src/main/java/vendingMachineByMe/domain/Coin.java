package vendingMachineByMe.domain;

import java.util.Arrays;

public enum Coin {
	COIN_500(500),
	COIN_100(100),
	COIN_50(50),
	COIN_10(10);
	// COIN_0(0); // TODO 3:  못찾을 경우 반환할 것

	private final int amount;

	Coin(final int amount) {
		this.amount = amount;
	}

	public static Coin findByAmount(int amount) {
		return Arrays.stream(Coin.values())
			.filter(e -> e.amount == amount)
			.findFirst()
			.get();
		// .orElse(COIN_0);
	}

	public int toAmount() {
		return this.amount;
	}
}

