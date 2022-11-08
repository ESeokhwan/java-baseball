package baseball;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public GameNumber readGameNumberInput() {
        String inputString = Console.readLine();
        return GameNumber.getInstance(inputString);
    }

    public RestartMode readRestartInput() {
        String input = Console.readLine();
        return RestartMode.makeInstance(input);
    }
}
