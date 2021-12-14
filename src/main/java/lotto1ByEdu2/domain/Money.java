package lotto1ByEdu2.domain;

public class Money {

	private int money;

	public Money(String money) {
	    this.money = convertStringToInt(money);
	}
	private int convertStringToInt(String money) {
	    return Integer.parseInt(money);
	}

	public static Money of(String money) {
	    // TODO: 검증필요시 추가
		checkMoneyValidation(money);
	    return new Money(money);
	}


		private static void checkMoneyValidation(String money) {
			checkNullOrEmpty(money);
			// checkValidLengthOfMoney(money);
			checkValidFormatOfMoney(money);
			// checkValidRangeMoney(money);
			checkValidMoney(money);

		}

	private static void checkValidMoney(String money) {
		int moneyInt = Integer.parseInt(money);
		if (!(moneyInt % 1000 == 0)) {
			throw new IllegalArgumentException("1000원 단위로 입력해주세요.");
		}
	}

	private static void checkNullOrEmpty(String money) {
			if (money == null || money.trim().isEmpty()) {
				throw new IllegalArgumentException("빈칸 입력은 허용하지 않는다.");
			}
		}


		private static void checkValidFormatOfMoney(String money) {
			if (!(money.chars().allMatch(Character::isDigit))) {
				throw new IllegalArgumentException("시도 횟수는 숫자여야 한다.");
			}
		}


	public Integer toLottoCount() {
		return this.money/1000;
	}
}
