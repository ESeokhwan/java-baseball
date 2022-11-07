package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TargetNumber {
    public static final int TARGET_NUMBER_LENGTH = 3;

    private final List<Integer> numberListByDigit;

    public static TargetNumber getInstance(String input) {
        validateInput(input);

        List<Integer> integerList = new ArrayList<>();
        for(int i = 0; i < input.length(); i++) {
            integerList.add((int) input.charAt(i) - '0');
        }

        return new TargetNumber(integerList);
    }

    public static TargetNumber getRandomInstance() {
        return new TargetNumber(makeRandomOneDigitIntListWithNoDuplicate());
    }

    private TargetNumber(List<Integer> integerList) {
        numberListByDigit = new ArrayList<>();

        numberListByDigit.addAll(integerList);
        Collections.reverse(numberListByDigit);
    }

    private static void validateInput(String input) {
        checkExactNumberLength(input);
        checkAllDigit1To9(input);
        checkAllDigitNotDuplicate(input);
    }

    private static void checkExactNumberLength(String input) {
        if(input == null || input.length() != TARGET_NUMBER_LENGTH) {
            throw new IllegalArgumentException();
        }
    }

    private static void checkAllDigit1To9(String input) {
        if(input == null || !input.matches("^[1-9]+")) {
            throw new IllegalArgumentException();
        }
    }

    private static void checkAllDigitNotDuplicate(String input) {
        for(int i = 0; i < input.length() - 1; i++) {
            char currentChar = input.charAt(i);
            String nextToTail = input.substring(i + 1);

            if(nextToTail.indexOf(currentChar) != -1) {
                throw new IllegalArgumentException();
            }
        }
    }

    private static List<Integer> makeRandomOneDigitIntListWithNoDuplicate() {
        List<Integer> output = new ArrayList<>();
        while (output.size() < TARGET_NUMBER_LENGTH) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!output.contains(randomNumber)) {
                output.add(randomNumber);
            }
        }

        Collections.reverse(output);
        return output;
    }

    public int toInt() {
        int result = 0;
        int digitLevel = 1;
        for(int i = 0; i < TARGET_NUMBER_LENGTH; i++) {
            result += numberListByDigit.get(i) * digitLevel;
            digitLevel *= 10;
        }

        return result;
    }

    public BallStrikeResult compareTo(TargetNumber oth) {
        BallStrikeResult result = new BallStrikeResult();
        for(int i = 0; i < this.numberListByDigit.size(); i++) {
            for(int j = 0; j < oth.numberListByDigit.size(); j++) {
                countBallOrStrike(
                        result,
                        this.numberListByDigit.get(i),
                        oth.numberListByDigit.get(j),
                        i == j
                );
            }
        }
        return result;
    }

    private void countBallOrStrike(BallStrikeResult result, int digit1, int digit2, boolean isStrike) {
        if(digit1 == digit2) {
            if(isStrike) {
                result.addStrikeCount();
                return;
            }
            result.addBallCount();
        }
    }
}
