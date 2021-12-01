package calculatorby2.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OperateTypeTest {

	@Test
	void enum_finder_test() {
		OperateType operateType = OperateType.finderOperator("-");
		Integer operation = operateType.operation(1, 3);
		System.out.println(operation);
	}
}
