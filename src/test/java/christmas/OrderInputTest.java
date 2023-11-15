package christmas;

import christmas.errors.ErrorHandler;
import christmas.view.InputView;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderInputTest {

    @Test
    @DisplayName("주문 메뉴 및 개수 입력 테스트")
    void invalidMenuInputTest() {
        // Given
        InputView inputView = new InputView();
        ErrorHandler errorHandler = new ErrorHandler(inputView);

        String input = "InvalidMenu-2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            try {
                inputView.parseOrderInput(input, errorHandler);
            } catch (IllegalArgumentException e) {
                throw e;
            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        });
    }
}

