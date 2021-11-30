package baseball2Youngyooon.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class IndexTest {
	@DisplayName("Index 입력 예외발생 검증")
	@ParameterizedTest
	@CsvSource({"1", "2", "3", "4"})
	@Test
	void checkValidTest(int index) {
		//10. 예외발생 검증 테스트는 assertThatThrownBy로 하며,
		// assertThat(actual).isEqualTo(expected);과 달리, ()인자에 노파라미터 람다식으로 () -> new 클래스()의 생성자로 객체 생성할 수 있다.
		// 이어서 .isInstanceOf(  예외종류 .class)를 이용해 발생시킬 예외종류도 결정한다.
		assertThatThrownBy(() -> new Index(index)).isInstanceOf(IllegalArgumentException.class);
	}

	//11. 입력검증시 통과테스트는, assert없이 그냥 내부에서 객체를 생성해서 받아보면된다.
	@DisplayName("Index 입력 통과 검증")
	@ParameterizedTest
	@CsvSource({"0", "1", "3"})
	public void checkValidPassTest(int index) {
		Index index1 = new Index(index);
	}
}
