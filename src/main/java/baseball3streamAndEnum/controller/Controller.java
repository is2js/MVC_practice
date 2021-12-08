package baseball3streamAndEnum.controller;

import java.util.List;

import baseball3streamAndEnum.domain.Balls;
import baseball3streamAndEnum.domain.Refree;
import baseball3streamAndEnum.domain.ResultTotal;
import baseball3streamAndEnum.view.InputView;
import baseball3streamAndEnum.view.OutputView;

public class Controller {
	// 1부터 9까지 //  서로 다른 수로 //  3자리의 수 //
	//
	// 	같은 수가 전혀 없으면 포볼 또는 낫싱이란 힌트를 얻고, 그 힌트를 이용해서 먼저 상대방(컴퓨터)의 수를 맞추면 승리한다.
	// 	같은 수가 같은 자리에 있으면 스트라이크,
	// 	         다른 자리에 있으면 볼,

	// 	예) 상대방(컴퓨터)의 수가 425일 때
	// 	123을 제시한 경우 : 1스트라이크
	// 	456을 제시한 경우 : 1볼 1스트라이크
	// 	789를 제시한 경우 : 낫싱
	// 	위 숫자 야구 게임에서 상대방의 역할을 컴퓨터가 한다.
	// 	컴퓨터는 1에서 9까지 서로 다른 임의의 수 3개를 선택한다.
	// 		컴퓨터는 입력한 숫자에 대한 결과를 출력한다.
	// 	게임 플레이어는 컴퓨터가 생각하고 있는 3개의 숫자를 입력하고,
	// 	이 같은 과정을 반복해 컴퓨터가 선택한 3개의 숫자를 모두 맞히면 게임이 종료된다.
	// 	게임을 종료한 후 게임을 다시 시작하거나 완전히 종료할 수 있다.
	// 	사용자가 잘못된 값을 입력할 경우 IllegalArgumentException을 발생시킨 후 애플리케이션은 종료되어야 한다.
	// 	아래의 프로그래밍 실행 결과 예시와 동일하게 입력과 출력이 이루어져야 한다.
	// 		3자리의 수
	// 		게임이 끝난 경우 재시작/종료를 구분하는 1과 2 중 하나의 수

	// 1볼 1스트라이크
	// 	하나도 없는 경우
	// 	낫싱
	// 	3개의 숫자를 모두 맞힐 경우
	// 	3스트라이크
	// 	3개의 숫자를 모두 맞히셨습니다! 게임 종료
	public void runGame() {
		//메인로직
		Refree refree = Refree.getInstance();
		refree.init();

		// Balls computerBalls = Balls.from(generateComputerBalls());
		// Balls computerBalls = Balls.from(Arrays.asList("1", "2", "3"));
		// System.out.println("컴퓨터볼");
		// computerBalls.getBalls().stream().
		// 	forEach(ball -> System.out.println(ball.getIndex() + ball.getValue()));

		boolean isOnGoing = true;
		while (isOnGoing) {
			List<String> balls = InputView.getBalls();
			Balls playerBalls = Balls.from(balls);
			refree.insert(playerBalls);
			ResultTotal resultTotal = refree.playGame();
			String resultFinal = refree.analyzeResult(resultTotal);
			OutputView.printResult(resultFinal);
			if (resultFinal.equals("3스트라이크")) {
				isOnGoing = InputView.getAnswer();
				if (isOnGoing) {
					refree.init();
				}
			}

		}

		// 7-1. 일단 Strike의 갯수를 파악해보자.
		// -> map ( 객체Class -> Enum?? :: 결과값 return 메서드  )
		// resultListList.stream()
		// TODO: [개념]8 특정원소 갯수 파악시 -> 결과값List로 진입했으면, filter + hasXXXX(stream()-개별result.anyMatch로 개별 isXXX파악)로
		//  <갯수파악 로직들어가기 전 포함여부검사> 특정 결과값을 list에 가지고 있는지 파악할 수 있다.
		// -> has에 걸릴 시에만, 갯수를 파악한다. ->
		// cf) 한단계 더 찢으면 결과값 isXXX로 특정 결과값 여부를 판단할 수 있다.
		// -> 객체리스트가 일급리스트가 아니면, 객체List.메서드()를 정의못한다.
		// .filter(resultList -> resultList.hasStrike()) // -> 객체리스트 -> 일급컬렉션..
		// strike를 가진것들만.. 한해서 갯수를 센다
		// -> 갯수도 map (결과값return)으로 받아온다.
		// .map( filteredList -> filteredList.countStrike())
		// .collect(Collectors.toList());
		// .mapToInt( filteredList -> filteredList.countStrike())
		// .sum();

		// return this.playerBalls.match(this.computerBalls);

		//1개의 볼당, 3개의 결과를 가지고 총 9개의 결과를 가진 상태다.
		// 결과list를 찢어지면서,
		// 0) map ( 결과값 return함수)로 결과값들 리스트를 모았으면
		// -> 1) filter로 if해당하는 결과값만 골라담든가?
		// -> 2) anyMatch has -> is로 카운트만 세든가

	}

}
