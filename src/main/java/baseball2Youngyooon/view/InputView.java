package baseball2Youngyooon.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

	public static String getInput() {
		//3. 이미 스캐너객체가 Console로 만들어져잇으니, private static객체를 만들필요는 없음.
		// -> public static getInput() 만 만들어준다.
		return Console.readLine();
	}
}
