package lotto.util;

import java.util.LinkedList;
import java.util.List;

public class StringUtil {
    private static final String SEPARATOR = ", |,";

    public static String[] separator(String inputString){
        return inputString.split(SEPARATOR);
    }

    public static List<Integer> stringArrayToIntegerList(String[] inputStringArray) {
        List<Integer> integerList = new LinkedList<>();
        for (String inputString : inputStringArray) {
            integerList.add(Integer.parseInt(inputString));
        }
        return integerList;
    }
}
