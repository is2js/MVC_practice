package trade.view;

import trade.domain.Item;

import java.util.Locale;

public class OutputView {

    public static void printTitle() {
        System.out.println("중고나라에 오신 것을 환영합니다.");
    }
    public static void printNameInstruction() {
        System.out.println("등록할 물품명을 입력해주세요.");
    }
    public static void printMoneyInstruction() {
        System.out.println("등록할 금액을 입력해주세요.");
    }
    public static void printRegisterInstruction() {
        System.out.println("해당 아이템을 해당 금액에 등록하시겠습니까? (Y, N)");
    }

    public static void printItem(Item item){
        System.out.println(item.getName() + " 제품의 가격은 " + item.getMoney() +"원 입니다. ");
    }

    public static void printRegisterItemInstruction(String answerRegister) {

        if (answerRegister.equalsIgnoreCase("y")) {
            System.out.println("정상 등록 되었습니다.");
            //TODO: 실제 등록
            return;
        }
        if (answerRegister.equalsIgnoreCase("n")) {
            System.out.println("등록 취소 되었습니다.");
            //TODO: 실제 등록 취소
            return;
        }
        System.out.println("y나 n을 입력해주세요.");
    }
}
