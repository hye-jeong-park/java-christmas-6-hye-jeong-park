package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.errors.ErrorHandler;

public class InputView {

    public int readDate(ErrorHandler errorHandler) {
        int visitDate;
        while (true) {
            try {
                System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
                visitDate = readIntegerInput();

                if (visitDate < 1 || visitDate > 31) {
                    errorHandler.handleException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
                }
                break;
            } catch (NumberFormatException e) {
                errorHandler.handleException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            } catch (IllegalArgumentException e) {
                errorHandler.handleException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
        }
        return visitDate;
    }

    public int readIntegerInput() {
        String input = Console.readLine();
        return Integer.parseInt(input);
    }
}