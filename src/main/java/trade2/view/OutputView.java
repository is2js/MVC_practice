package trade2.view;

import trade2.domain.Item;

public class OutputView {

	public static final String TITLE_INSTRUCTION = "중고나라에 오신 것을 환영합니다.";
	public static final String NAME_INSTRUCTION = "물품명을 입력해주세요";
	public static final String MONEY_INSTRUCTION = "금액을 입력해주세요";

	public static void printTitle() {
		System.out.println(TITLE_INSTRUCTION);
	}

	public static void printNameInstruction() {
		System.out.println(NAME_INSTRUCTION);
	}

	public static void printMoneyInstruction() {
		System.out.println(MONEY_INSTRUCTION);
	}

	public static void printItem(Item item) {
		System.out.printf("입력한 상품 %s가 %s원에 등록되었습니다.\n", item.getItemName(), item.getItemMoney().toString());
	}
}
