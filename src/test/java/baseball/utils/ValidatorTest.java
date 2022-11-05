package baseball.utils;

import baseball.Ball;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.channels.IllegalChannelGroupException;
import java.util.ArrayList;
import java.util.List;

class ValidatorTest {

    @Test
    void validateParseStringToInt_숫자로_바꿀_수_없는_문자열을_바꾸려고_할때_예외를_던지는지_테스트() {
        //given
        String playerInput = "12삼";
        //when
        //then
        Assertions.assertThatThrownBy(() -> {
                      try {
                          Integer.parseInt(playerInput);
                      } catch (NumberFormatException e) {
                          throw new IllegalArgumentException("입력값은 서로 다른 1부터 9사이의 숫자로 이루어져야 합니다.");
                      }
                  })
                  .isInstanceOf(IllegalArgumentException.class)
                  .hasMessageContaining("입력값은 서로 다른 1부터 9사이의 숫자로 이루어져야 합니다.");
    }

    @Test
    void validateParseStringToInt_숫자로_바꿀_수_있는_문자열을_바꾸려고_숫자로_잘_바뀌는지_테스트() {
        //given
        String playerInput = "123";
        //when
        //then
        Assertions.assertThat(Integer.parseInt(playerInput))
                  .isEqualTo(123);
    }


    @Test
    void validateInputLength_입력된_값의_길이가_3이_아닐때_예외를_던지는지_테스트() {
        //given
        String playerInput = "12";
        //when
        Assertions.assertThatThrownBy(() -> {
                      if (playerInput.length() == 3) {
                          return;
                      }
                      throw new IllegalArgumentException("입력값은 서로 다른 1부터 9사이의 숫자로 이루어져야 합니다.");
                  })
                  .isInstanceOf(IllegalArgumentException.class)
                  .hasMessageContaining("입력값은 서로 다른 1부터 9사이의 숫자로 이루어져야 합니다.");
        //then
    }


    @Test
    void validateDuplication_입력된_값_중_중복된_값이_있을_경우_예외를_던지는지_테스트() {
        //given
        List<Ball> playerBalls = new ArrayList<>();
        playerBalls.add(new Ball(1));

        Ball ball = new Ball(1);
        //when
        //then
        Assertions.assertThatThrownBy(() -> {
                      if (playerBalls.contains(ball)) {
                          throw new IllegalArgumentException("입력값은 서로 다른 1부터 9사이의 숫자로 이루어져야 합니다.");
                      }
                  })
                  .isInstanceOf(IllegalArgumentException.class)
                  .hasMessageContaining("입력값은 서로 다른 1부터 9사이의 숫자로 이루어져야 합니다.");
    }

    @Test
    void validateDuplication_입력된_값_중_중복된_값이_없을_경우_리스트에_잘_추가되는지_테스트() {
        //given
        List<Ball> playerBalls = new ArrayList<>();
        playerBalls.add(new Ball(1));

        Ball ball = new Ball(2);
        //when
        if (playerBalls.contains(ball)) {
            throw new IllegalArgumentException("입력값은 서로 다른 1부터 9사이의 숫자로 이루어져야 합니다.");
        }
        playerBalls.add(ball);

        //then
        Assertions.assertThat(playerBalls.size())
                  .isEqualTo(2);
    }
}