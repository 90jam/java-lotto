package lotto.view;

import lotto.domain.exception.InvalidManualLottoCountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("사용자 입력 뷰 테스트")
public class InputViewTest {

    private StringWriter stringWriter = new StringWriter();
    private PrintWriter systemWriter = new PrintWriter(System.out);

    @DisplayName("구입금액 입력")
    @ParameterizedTest
    @CsvSource(value = {"100:100", "15000:15000"}, delimiter = ':')
    public void enterMoney(String input, int expectedMoney) {
        InputView inputView = new InputView(new Scanner(input), systemWriter);

        int money = inputView.getMoney();

        assertThat(money).isEqualTo(expectedMoney);
    }

    @DisplayName("구입금액 입력시 문구")
    @Test
    public void showPhraseForMoney() {
        InputView inputView = new InputView(new Scanner("1000"), new PrintWriter(stringWriter));

        inputView.getMoney();

        assertThat(stringWriter.toString()).isEqualTo("구입금액을 입력해 주세요.\n");
    }

    @DisplayName("지난주 당첨번호 입력")
    @ParameterizedTest
    @MethodSource("getLastLottoNumbers")
    public void enterLastWeekLottoNumber(String input, Integer[] expectedLastNumbers) {
        InputView inputView = new InputView(new Scanner(input), systemWriter);

        List<Integer> lastLottoNumbers = inputView.getLastLottoNumbers();

        assertThat(lastLottoNumbers).containsOnly(expectedLastNumbers);
    }

    static Stream<Arguments> getLastLottoNumbers() {
        return Stream.of(
                arguments("1, 2, 3, 4, 5, 6", new Integer[]{1, 2, 3, 4, 5, 6}),
                arguments("3, 4, 5, 6, 7, 8", new Integer[]{3, 4, 5, 6, 7, 8})
        );
    }

    @DisplayName("지난주 당첨번호 입력시 문구")
    @Test
    public void showPhraseForLastLottoNumbers() {
        InputView inputView = new InputView(new Scanner("3, 4, 5, 6, 7, 9"), new PrintWriter(stringWriter));

        inputView.getLastLottoNumbers();

        assertThat(stringWriter.toString()).isEqualTo("지난 주 당첨 번호를 입력해 주세요.\n");
    }

    @DisplayName("보너스 볼 입력")
    @ParameterizedTest
    @CsvSource(value = {"10:10", "15:15"}, delimiter = ':')
    public void enterBonusBall(String input, int expectedNumber) {
        InputView inputView = new InputView(new Scanner(input), systemWriter);

        int number = inputView.getBonusLottoNumber();

        assertThat(number).isEqualTo(expectedNumber);
    }

    @DisplayName("보너스 볼 입력시 문구")
    @Test
    public void showPhraseForBonusBall() {
        InputView inputView = new InputView(new Scanner("9"), new PrintWriter(stringWriter));

        inputView.getBonusLottoNumber();

        assertThat(stringWriter.toString()).isEqualTo("보너스 볼을 입력해 주세요.\n");
    }

    @DisplayName("수동으로 구매할 로또 수 입력")
    @ParameterizedTest
    @CsvSource(value = {"10:10", "15:15"}, delimiter = ':')
    public void enterManualLottoCount(String input, int expectedNumber) {
        InputView inputView = new InputView(new Scanner(input), systemWriter);

        int number = inputView.getManualLottoCount();

        assertThat(number).isEqualTo(expectedNumber);
    }

    @DisplayName("수동으로 구매할 로또 수 입력시 문구")
    @Test
    public void showPhraseForManualLottoCount() {
        InputView inputView = new InputView(new Scanner("9"), new PrintWriter(stringWriter));

        inputView.getManualLottoCount();

        assertThat(stringWriter.toString()).isEqualTo("수동으로 구매할 로또 수를 입력해 주세요.\n");
    }

    @DisplayName("수동으로 구매할 로또 번호 입력")
    @ParameterizedTest
    @MethodSource("getManualLotto")
    public void enterManualLottoNumber(String input, List<List<Integer>> expectedLottoNumbers) {
        InputView inputView = new InputView(new Scanner(input), systemWriter);

        List<List<Integer>> lastLottoNumbers = inputView.getManualLottoNumbers(2);

        expectedLottoNumbers.forEach(expectedLottoNumber -> assertThat(lastLottoNumbers).contains(expectedLottoNumber));
    }

    static Stream<Arguments> getManualLotto() {
        return Stream.of(
                arguments("1, 2, 3, 4, 5, 6\n3, 4, 5, 6, 7, 8", Arrays.asList(Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(3, 4, 5, 6, 7, 8)))
        );
    }

    @DisplayName("수동으로 구매할 로또 번호 입력시 문구")
    @Test
    public void showPhraseForManualLotto() {
        InputView inputView = new InputView(new Scanner("1, 2, 3, 4, 5, 6\n3, 4, 5, 6, 7, 8"), new PrintWriter(stringWriter));

        inputView.getManualLottoNumbers(2);

        assertThat(stringWriter.toString()).isEqualTo("수동으로 구매할 번호를 입력해 주세요.\n");
    }

    @DisplayName("수동으로 구매할 로또 개수가 0개 일 때")
    @Test
    public void noManualLotto() {
        InputView inputView = new InputView(new Scanner(""), new PrintWriter(stringWriter));

        int result = inputView.getManualLottoNumbers(0).size();

        assertThat(result).isEqualTo(0);
    }

    @DisplayName("수동으로 구매할 로또 개수가 0개 일 때 입력 문구")
    @Test
    public void phraseForNoManualLotto() {
        InputView inputView = new InputView(new Scanner(""), new PrintWriter(stringWriter));

        inputView.getManualLottoNumbers(0);

        assertThat(stringWriter.toString()).isEqualTo("");
    }

    @DisplayName("수동으로 구매할 로또 개수가 0개보다 작을때")
    @Test
    public void invalidManualLotto() {
        InputView inputView = new InputView(new Scanner(""), new PrintWriter(stringWriter));

        assertThatThrownBy(() -> {
            inputView.getManualLottoNumbers(-1);
        }).isInstanceOf(InvalidManualLottoCountException.class)
                .hasMessageContaining("수동으로 구매할 로또 수는 0 이상이어야 합니다.");
    }

}