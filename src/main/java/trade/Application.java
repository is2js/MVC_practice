package trade;
//사용자가 상품명과 금액을 입력할 수 있어야 한다. - InputView#inputItem() -> InputView#getInput()
// 상품이 정상적으로 등록 될 때 결과를 확인할 수 있어야 한다. - OutputView#printRegisterResult()

// 상품과 금액을 Item으로 등록할 수 있어야 된다. - Register#registerItem()


import trade.domain.Item;
import trade.domain.Register;
import trade.view.InputView;
import trade.view.OutputView;

public class Application {
    public static void main(String[] args) {
        //1.main에 일단 몰아서 한다고 치면, class도 여기 내부에서 만들어줘도 된다.
        // -> 일단은 나누서 작성해보려고함.

        //2. 컨트롤러로 옮길 예정인데, 테스트도 여기서 해보자.
        Item item = new Item("오버워치", 340);
        Register register = new Register();

        //3. 안내문들은 OutputView의 메서드로 다 빼논다.
        OutputView outputView = new OutputView();
        outputView.printTitle();
        //4. InputView에선 static scanner객체를 건네주는 유틸메소드를 이용해서 string만 넘겨받는다.
        outputView.printNameInstruction();
        String itemName = InputView.getInput();
        outputView.printMoneyInstruction();
        int itemMoney = Integer.parseInt(InputView.getInput());
        outputView.printRegisterInstruction();
        String answer = InputView.getInput();
        outputView.printRegisterResult(answer);



//        String result = item.toString();
//        System.out.println(result);

//        register.registerItem(item);



    }
    }
