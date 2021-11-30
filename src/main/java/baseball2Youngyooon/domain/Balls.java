package baseball2Youngyooon.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import baseball2Youngyooon.utils.Util;

public class Balls {
	//1. Ball들을 모은 list도 class로 감싼다.
	private List<Ball> balls;

	//2 . list에 대한 검증 추가작업은 private 생성자에서 한다
	// -> **최소단위객체Ball는 각 int타입으로 가지고 있지만, Ball list를 포장한 Balls는 string으로 유저에게 입력받는다.**
	// -> 각 자리(index)의 숫자(number)는 int로 변환된 상태이나,,
	// -> 사용자에게 문자열숫자로 입력받을 Ball의 list는 첨부터 [문자열숫자 String ]을 입력받도록 하자.
	// -> list 포장 class는 [ String입력 숫자list -> Integer 숫자list]변환기능이 1번이네..
	private Balls(String stringBalls) {
		// 3. 일단 문자열 숫자 list -> Integer list로 Format변환작업을 한다.
		// -> 변환작업은 utils패키지에서 스태틱메소드로 하자.
		// split String[] arr -> Arrays.stream -> mapToInt ->
		List<Integer> integerBalls = Util.ConvertStrToIntList(stringBalls);
		//4. Integer list의 index만큼 IntStream.Range(0,N)으로 index 만들어 돌면서 list의 값을 뽑은 뒤, 그 값을 Ball.of()에 넣어서 Ball을 만든다.
		// -> Ball list를 만들면, this.balls에 넣을 수 있다.
		// **IntStream으로 index를 만들고, 람다식으로 index ->  .get(index)로 list 값을 순서대로 index 계속 쓰면서 돌수 있다니..**
		// **Type이 변형되면, matToInt이외에 내가 정의한 객체는 mapToObj로 변환되는 구나.**
		this.balls = IntStream.range(0, integerBalls.size())
			.mapToObj(i -> Ball.of(i, integerBalls.get(i)))
			.collect(Collectors.toList());
	}

	//5. private 생성자를 대신해서, 객체를 만드러 반환해줄 스태틱 of메서드
	public static Balls of(String stringBalls) {
		return new Balls(stringBalls);
	}

	//6. Ball vs Ball의 같은 (최소단위)객체 끼리 비교 뿐만 아니라
	// -> Balls vs Balls의 같은 (list)객체 끼리 비교도 메서드로 만들 수 있다.
	// --> my) 같은리스트래핑객체 vs 리스트객체 비교하기전에, m:1 메서드부터 작성해야함.
	// -> 같은 객체를 받더라도 변수는 상대느낌으로 받아주자.
	// -> 그 때 뭘 반환해줄 것인지는 자기가 생각해서 만든다.
	// cf) Ball 객체끼리 비교 -> Score compare(Ball ball) { -> this vs ball
	// public void compare(Balls computerBalls) {
		// 7. 비교시 this.balls(현재 객체의 Ball List)가 기준이 되서, list로 stream을 만들어 진행한다.
		// this.balls.stream().
		// -> **stream()을 map()으로 하나씩 불러들이면, 각 ball마다 변형을 하므로**
		// -> map속 각 ball마다 computerBalls(특정객체)의 내부 compare를 요청하여
		// -> this.balls속ball 1개씩 -> computerBalls.compare를 요청한다.
		// -> 그렇다면 Balls리스트.compare ( ball1개) 가 정의되어있어야한다.
	// }

	//8. 일단 현재Balls리스트에 대해, 기준ball1개가 들어오면, 기준ball - 0,1,2 볼리스트의 비교결과값(Score)를 반환해주도록 하자.
	// -> 즉, this.balls -> 쪼개서 각 ball마다  [ ball compare ball ]이 호출되어야한다.
	// public List<Score> compare(Ball computerball) {
	// map(특정객체::compare) -> 특정객체.compare( 각 stream==thils.balls에 있는 각 ball1개)
	// -> 파라미터객체.메서드( ) 1개씩 적용할 때, map(객체::메서드)
	// --> compare의 결과는 enum[Score]로 정해놨으니 각 enum상수가 모여 List<Enum>으로 반환된다.
	// 	return this.balls.stream().map(computerball::compare).collect(Collectors.toList());
	// }
	//9. enum인 Score도 list다 -> class Scores를 또 먼저 만들어주자.
	// -> 기능말고  private변수 + private 생성자 -> public static of 메서드 정도만 만들어주자.
	// public List<Score> compare(Ball computerball)  -> public Scores ~~
	public Scores compare(Ball computerball) {
		// 11.enum인 Score의 list Scores도 of로 생성되도록 하였다.
		// return this.balls.stream().map(computerball::compare).collect(Collectors.toList());
		return Scores.of(this.balls.stream().map(computerball::compare).collect(Collectors.toList()));
	}

	//12. 이제 다시 Ball리스트 객체끼리의 비교 메서드를 작성해보자.
	// -> Balls vs Balls -> 1개(this)는 stream 타면서 this.의 ball vs 파라미터로 온 balls -> 1:m 비교메서드
	// -> 1:m의 결과값list -> Scores -> n:m의 결과값 -> Scores의 list...??
	public Result compare(Balls computerBalls) {
		//my) 객체list는 stream().map()으로 최소단위객체를 사용하도록 한다.
		//my) stream()으로 list에서 꺼낸 개별 객체는 ->  map( 클래스or특정객체::메서드)의 인자,파라미터가 된다.
		//this.balls.stream().map(computerBalls::compare).collect(Collectors.toList());
		// -> 여기까지가.. Scores List다..

		// 13. 1:m의 결과 Scores를 모아둔 Scores의 list(n:m의 결과)도 만든다.
		// -> Result -> List<Scores> class작성하기
		
		// 14. stream을 통해 this.balls가 찍어지면서  파라미터balls와 비교하는 과정 정리
		// 1) this.balls.stream().map( : 각 ball이 나옴 
		// 2) this.balls.stream().map(computerBalls::compare): 
		// -> 파라미터로 들어온 balls리스트.compare( )의 인자로 들어가서, 각 1:m으로 ScoreList이 Scores를 만듬
		// 3) this.balls.stream().map(computerBalls::compare).collect(Collectors.toList()); :
		// -> this.ball의 ball마다 결과값인 Scores를 모아 -> Scores(ScoreList)의 List -> Result를 생성할 수 있음.
		// 4) Result.of ( List<Scores> ) -> Result 생성함
		return Result.of(this.balls.stream().map(computerBalls::compare).collect(Collectors.toList()));
	}
	//15. 최소단위 1개 객체 -> 서로 1:1비교해서 쉬움. 분기별 결과값 바로 생김
	//    객체리스트 n:m비교는
	// 1) 1:m 메소드부터 작성하여 한쪽은 stream을 찢어져서 -> 1:1 여러개 하도록 작성 -> 결과값 list class(Scores)생성
	// 2) n:m 메소드를 한쪽은 stream으로 찍어져서 -> 1:m 여러개 하도록 작성 -> 결과값list의 list class(Results)생성

}
