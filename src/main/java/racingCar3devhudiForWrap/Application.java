package racingCar3devhudiForWrap;

import racingCar3devhudiForWrap.controller.Controller;

public class Application {
	public static void main(String[] args) {
		// System.out.println((Rank.findGroupByElement("삼성 소프트웨어")));
		// System.out.println(Result.findResult("평균").agg(Arrays.asList(1, 2, 3)));
		// System.out.println((Result.findResult("Y").isWINNER()));
		// System.out.println((Result.findResult(false).isLOSER()));

		Controller controller = new Controller();
		controller.runRacing();
	}
}
