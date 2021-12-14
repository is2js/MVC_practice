package lotto1ByEdu2;

import lotto1ByEdu2.domain.LottoWinRank;
import lotto1ByEdu2.domain.WinCounter;

public class Test {

	public static void main(String[] args) {
		WinCounter winCounter = new WinCounter();
		// winCounter.printCount();
		winCounter.plusCount(LottoWinRank.THREE);
		System.out.println(winCounter);
		System.out.println(winCounter.CountOf(LottoWinRank.FIVE));
	}
}
