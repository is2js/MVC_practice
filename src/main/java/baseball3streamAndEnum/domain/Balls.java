package baseball3streamAndEnum.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Balls {
	//2) private한 객체List를 감싸고 있는데, 관리대상인 원시타입List를 변수로 작성한다.
	private List<Ball> balls;

	public Balls(List<String> list) {
		// this.balls = list.stream()
		// 	.map(Ballof)
		// 	.collect(Collectors.toList());
		this.balls = IntStream.range(0, list.size())
			.mapToObj(i -> Ball.of(list.get(i), i))
			.collect(Collectors.toList());
	}

	public List<Ball> getBalls() {
		return balls;
	}

	public static Balls from(List<String> list) {
		return new Balls(list);
	}

	// [개념]9 결과값객체(Enum) List가 보이는 순간부터 바로 일급컬렉션으로 간다.
	//===========================
	// 기존 객체List 자리 : this.stream()... .collect(Collectors.toLit()) 자리에
	// -> 일급컬렉셔CLASSs.from(   ) 를 씌워서, 일급컬렉션 객체로 만들어줘야하다.!!!
	//===========================
	// public List<Result> match(Ball computerBall) {
	public Results match(Ball computerBall) {
		return Results.from(this.balls.stream()
			.map(ball -> ball.match(computerBall))
			.collect(Collectors.toList()));

		// 뭐야.. 기준은 파라미터의 컴퓨터 볼 1개에 대해서..
		// 1개볼당 3개의 결과값에서, 추린다?
		//컴1-1 strike가능성, 컴1-2볼가능성, 컴1-3볼가능성

	}

	// [개념5] 일급 vs 일급  -> 한쪽 스트림으로
	// -> stream객체 vs 일급 비교    들어오는 일급을 쪼갤 순 없으니
	// -> **일급 vs 객체로 정의해놓고 -> 사용을  stream객체 .map ( 파라미터로들어오는 일급.메서드(stream객체) **
	// -> 객체 vs 객체 정의
	// --> **비교메서드를 map으로 쓰고, 메서드는 return 결과값해서, map에서 모아지게 한다**

	// public List<Results> match(Balls computerBalls) {
	public ResultTotal match(Balls computerBalls) {
		// [개념6] 객체끼리 비교로 결과값을 받으려면 map(대상Class::메서드) or map(같은클래스같은이름있는메서드시 특정객체::메서드)
		return ResultTotal.from(
			this.balls.stream()
				.map(ball -> computerBalls.match(ball))
				.collect(Collectors.toList()));

	}

}
