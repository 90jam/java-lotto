package lotto.view;

import lotto.exception.NotInstanceException;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String PURCHASE_AMOUNT_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBERS_INPUT_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";

    private InputView() {
        throw new NotInstanceException();
    }

    public static String getPurchaseAmount() {
        System.out.println(PURCHASE_AMOUNT_INPUT_MESSAGE);
        return SCANNER.nextLine();
    }

    public static String getWinningNumbers() {
        System.out.println(WINNING_NUMBERS_INPUT_MESSAGE);
        return SCANNER.nextLine();
    }
}
