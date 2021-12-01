package calculator2;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringTest {
	@DisplayName("split한 배열을 정확히 포함하는지 containsExactly")
	@Test
	void String_split_contains() {
		java.lang.String[] splitedValue = "1,2".split(",");

		assertThat(splitedValue).containsExactly("1", "2");
		// assertThat(splitedValue).contains("1", "2");
	}

	@DisplayName("substring으로 앞뒤 괄호제거하기 -> 0번, n-1번을 제외시키자. 1시작~n-1전까지 -> (1, n-1")
	@Test
	void String_substring_test() {
		//given
		java.lang.String data = "(1,2)";
		System.out.println(data.substring(0));
		System.out.println(data.substring(0, data.length() - 1));

		java.lang.String actual = data.substring(1, data.length() - 1);

		assertThat(actual).isEqualTo("1,2");
	}

	@DisplayName("String은 딱히 toCharArray안해도 .charAt으로 뽑아지네.. 대신 작은따옴표로! + index 테스트")
	@Test
	void string_charat_test() {
		java.lang.String abc = "abc";
		assertThat(abc.charAt(0)).isEqualTo('a');
		assertThat(abc.charAt(1)).isEqualTo('b');
		assertThat(abc.charAt(2)).isEqualTo('c');

		assertThatThrownBy(() -> abc.charAt(4))
			.isInstanceOf(IndexOutOfBoundsException.class);
	}
}
