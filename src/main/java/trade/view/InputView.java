package trade.view;

import java.util.Scanner;

public class InputView {
    // InputView에서는 항상 scanner를 사용해야하는데,
    // controller에서 객체를 생성할 순 없다 여기서 해야한다.
    // -> static으로 메모리에 애초에 올려놓고, 객체없이 사용 &
    // -> private으로 내부에서만 사용하는데, public 메소드로 반환해줌.

    private static Scanner scanner = new Scanner(System.in);
    // inputView는 public static 유틸메소드로서, 싱글톤형태의 scanner를 리턴만 해준다.
    // 처리는 contoller에서 scanner받아서 한다? -> 일케 되면,, contoller에서 Scanner참조변수 만들어야함
    // -> nextLine()으로 받은 문자열을 건네주자. (contoller에 scanner 안가게)
    public static String getInput(){
        return scanner.nextLine();
    }
}
