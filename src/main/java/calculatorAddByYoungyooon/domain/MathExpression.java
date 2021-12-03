package calculatorAddByYoungyooon.domain;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MathExpression {
	//18.
	// private String mathexpression;
	private List<Integer> mathexpression;

	private MathExpression(String mathexression) {
		//TODO:  convert or
		//7. if "0"으로 들어온다면, 그냥 진자 0을 반환해야함.
		//8. [나중검증] 각 수들만 추출 -> 음수확인은...stream().filter( number -> 자체check함수(number)) 로
		// -> [boolean true or thr 메서드]를 넣어서 가능하다.!!

		//9. 일단 패턴식이면, 패턴을 찾고 아니면 ",|:"로 split해서 숫자만 추출해서 -> convert도 해야한다.
		List<Integer> integers = extractIntNumbers(mathexression);
		//14.작성하다보니, 인트만 추출이 아니라 인트추출후 sum() Add계산기 매인로직까지 다해버렸다.
		// -> 원래는 계산기가 받아서, 추출해야함.
		// -> int로 컨버트만 하고 List<Integer>를 반환하는 것으로 수정하자.

		this.mathexpression = integers;
	}

	//TODO: 나중에 유틸쪽으로 옮긴다. -> public static으로 생성하자.
	//17.
	// public static int extractIntNumbers(String mathexpression) {
	public static List<Integer> extractIntNumbers(String mathexpression) {
		//9. 패턴 객체생성은 static final로 빼도된다.
		Pattern pattern = Pattern.compile("//(.)\n(.*)");
		Matcher matcher = pattern.matcher(mathexpression);
		if (matcher.find()) {
			String delimeter = matcher.group(1);
			String expression = matcher.group(2);
			// return Arrays.stream(expression.split(delimeter))
			// 	.mapToInt(Integer::parseInt)
			// 	// 10. 집계를 위한 형변환 IntStream의 숫자들을 모은 상태라면, 반드시 음수thr검사를 filter로 검사해준다!!ㄴ
			// 	.filter( i -> checkNegative(i) )
			// 	// 12. 음수검사 끝난 것들은 sum()이 되단.
			// 	.sum();

			//16. 추출만하고 합계는 계산기가 하게 한다. 데이터만 추출하게 한다.
			// -> 이 때, convert과정이니, stream으로 음수검사까진 하게 한다.
			return Arrays.stream(expression.split(delimeter))
				.mapToInt(Integer::parseInt)
				.filter(i -> checkNegative(i))
				.boxed()
				.collect(Collectors.toList());
		}
		// 13. 패턴이 아니라면, .:; 2개 딜리미러로 split하여 더한다
		// -> 이 때도 음수검사(입력숫자split stream -> IntStream때) 한다.
		return Arrays.stream(mathexpression.split(",|:"))
			.mapToInt(Integer::parseInt)
			.filter(i -> checkNegative(i))
			// .sum();
			.boxed()
			.collect(Collectors.toList());

	}

	private static boolean checkNegative(int i) {
		if (i < 0) {
			throw new RuntimeException("stream중에 검사한 음수검사에 걸렸어요");
		}
		return true;
	}

	public static MathExpression from(String mathexression) {
		//TODO: validation
		//6. 검증은 checkXXXX로 아니면 thr다
		// -> 체크를 thr할 거아니면, chekcXXXX()메서드로 보내면 안된다.
		// -> checkValidation() 끝나고 return new ()생성자 호출하던 것을
		// -> if 필터링을 걸러, if 필터링에서 <생성자에 반환될 값을 넣어서 원시변수에 특정값을 넣어서 > 생성자 호출 return을 해버린다.
		// --> 검증끝난 뒤 바로 파라미터 -> 생성자호출하는 로직이 생긴 이유가 여기있다.
		// --> 특정값을 반환해야한다면, if필터링으로 new 생성자(특정값)을 반환해서 생성자 다르게 호출한다.
		// checkValidation(mathexression);
		if (mathexression == null || mathexression.trim().equals("")) {
			return new MathExpression("0");
		}

		// 22. if null이면 0을 입력한 래핑생성자를 호출했지만,
		// -> 그외에는  기존처럼 검증을 들어간 후 -> 생성자 호출되게 한다.
		// 1)빈 문자열, null 일 경우 0 반환

		// 2) 숫자 하나를 입력 할 경우 해당 숫자 반환 -> 길이검사 (생략)
		// 3) ",",":"으로 split 후 더하기 연산하기
		// 4) 커스텀 구분자 \\ /n 사이의 구분자로 split 후 더하기 연산 -> conver에서
		// 5) 숫자가 아닐 경우 RuntimeException 발생 -> 포맷검사?
		// 6) 음수 일 경우 RuntimeException 발생 -> stream에서

		return new MathExpression(mathexression);
	}

	public List<Integer> getMathexpression() {
		return this.mathexpression;
	}

	//23.
	public int getAdd() {
		int sum = 0;
		for (Integer integer : mathexpression) {
			sum += integer;
		}
		return sum;
	}
}
