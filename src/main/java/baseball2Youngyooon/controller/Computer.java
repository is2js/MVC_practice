package baseball2Youngyooon.controller;

import java.util.LinkedHashSet;

import baseball2Youngyooon.domain.Balls;
import baseball2Youngyooon.domain.Result;
import camp.nextstep.edu.missionutils.Randoms;

public class Computer {
	//1. private static scanner객체를 public static 으로 꺼내쓰듯, controller가 단순반복시킬동안 계속 떠있는 메인로직 가동용 객체Computer도 static으로 싱글톤화한다.
	//  클래스내부에 private static으로 new객체를 생성해놓고 -> public static 메서드 getInstance()로 가져온다. (싱글톤?)
	private static Computer computer = new Computer();
	private Balls balls;

	// 2. 생성자는 비워두고(메서드가동용이라 객체단위 관리결과값이 없는 상태?)
	private Computer() {
	}

	//3. static 메서드로 호출시만, 이미 생성한 인스턴스를 내어준다. (싱글톤)
	public static Computer getInstance() {
		return computer;
	}

	//4. 생성자에서 초기화하지말고, 인스턴스메서드로 init() 따로 작성해서, 가동용객체.init()으로 초기화를 호출하게 해준다.
	// -> 스새틱Computer.getinstance()객체 --> .init()
	public void init() {
		//5. 컨트롤러 객체 초기화시, domain 모델 객체 중 미리 만들어놓을 것을 만든다.
		// -> 우형 라이브러리로 랜덤하게 만들자.
		// -> 게임동안 일단은 필요해서 초기화해놓고, <<< 변동가능성>>>있으면  (객체단위, 가동단위)private 인변 ballss으로 두어야한다. -> 다른메서드에서 변경한다(재시작시)ㄴ
		balls = Balls.from(makeRandomNumbers());
		//8. 테스트는.. init()만 해도 자동으로 에러나는지 안나는지 테스트된다.
		// -> 일급콜렉션(List래핑클래스)은ㄴ 생성만 해도 자동으로 thr검증이 되는 것이다.
		// -> Test로 가서 init()만 여러번 호출해보자.
	}

	//6. 랜덤넘버 작성은 메서드로 따로 빼서 해보자.
	private String makeRandomNumbers() {
		// -> 중복 불허하면서 3개를 모아야하므로, LinkedHashSet을 이용하자.
		// -> Balls.from은 문자열숫자를 받으니 문자열로 바꿔서 넣어줘야한다 by stream
		LinkedHashSet<Integer> hashSet = new LinkedHashSet<>();
		while (hashSet.size() < 3) {
			int randomNumber = Randoms.pickNumberInRange(1, 9);
			hashSet.add(randomNumber);
		}
		//7. stream으로 list급 hashset을  .stream()-> 찢어서string변환 -> reduce로 문자열 누적합 -> get()로 누적합해서 문자열숫자를 뭉칠 수 있음.
		return hashSet.stream().map(Object::toString).reduce((a,b)->a+b).get();
	}
	//8. 랜덤숫자생성 및 들어오는 playerball과의 비교를 한다.
	// -> 메인로직이 있는 객체다. Controller가 계속띄울 객체. -> 싱글톤임.
	public Result matchBalls(Balls inputBalls) {
		return balls.compare(inputBalls);
	}


}
