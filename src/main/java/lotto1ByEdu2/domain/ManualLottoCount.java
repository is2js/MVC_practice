package lotto1ByEdu2.domain;

public class ManualLottoCount {
	// TODO wrap 2) 단일객체가 가진 변수들  private 선언
	private Integer manualLottoCount;

	public ManualLottoCount(Integer manualLottoCount) {
	    this.manualLottoCount = manualLottoCount;
	}

	public static ManualLottoCount of(Integer manualLottoCount, LottoCount lottoCount) {
	    checkManualLottoCountValidationAndChangeAutoCount(manualLottoCount, lottoCount);
	    return new ManualLottoCount(manualLottoCount);
	}

	private static void checkManualLottoCountValidationAndChangeAutoCount(Integer manualLottoCount,
		LottoCount lottoCount) {
		int countTotal = lottoCount.toInt();
		if (manualLottoCount > countTotal) {
			throw new IllegalArgumentException("구매금액보다 더 많은 수동로또를 원하십니다.");
		}
		lottoCount.changeCount(manualLottoCount);

	}

	public int toInt() {
		return this.manualLottoCount;
	}
}
