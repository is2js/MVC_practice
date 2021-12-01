package calculator2;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SetTest {

	HashSet<Object> numbers;

	@BeforeEach
	void setUp() {
		numbers = new HashSet<>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
	}

	@DisplayName("set의 사이즈 확인 테스트")
	@Test
	void set_test() {
		int actual = numbers.size();

		assertThat(actual).isEqualTo(4);
	}

	@DisplayName("set의 값 포함여부 확인")
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6})
	@Test
	void set_contains(int val) {
		boolean actual = numbers.contains(val);

		assertThat(actual).isTrue();
	}
}
