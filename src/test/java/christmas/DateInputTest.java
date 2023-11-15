package christmas;

import christmas.errors.ErrorHandler;
import christmas.view.InputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DateInputTest {

    @Test
    @DisplayName("예상 방문 날짜 입력 테스트")
    void validDateInputTest() {
        // Given
        String simulatedUserInput = "15\n";
        InputStream in = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(in);
        ErrorHandler errorHandler = new ErrorHandler(new InputView());

        // When
        int visitDate = new InputView().readDate(errorHandler);

        // Then
        assertEquals(visitDate, 15);
    }
}
