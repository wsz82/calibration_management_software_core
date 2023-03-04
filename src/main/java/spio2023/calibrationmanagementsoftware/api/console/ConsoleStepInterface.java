package spio2023.calibrationmanagementsoftware.api.console;

import spio2023.calibrationmanagementsoftware.api.model.procedure.Settings;
import spio2023.calibrationmanagementsoftware.api.model.procedure.StepInterface;

import java.util.Scanner;

public class ConsoleStepInterface implements StepInterface {
    private final Scanner in;
    private final Settings settings;

    public ConsoleStepInterface(Scanner in, Settings settings) {
        this.in = in;
        this.settings = settings;
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public Double[] getReferencedInput() {
        return getInput();
    }

    @Override
    public Double[] getCheckedInput() {
        return getInput();
    }

    private Double[] getInput() {
        var inputArr = new Double[settings.getMeasurementsNumber()];
        for (int i = 0; i < inputArr.length; i++) {
            askUserInput(inputArr, i);
            System.out.println(inputArr[i]);
        }
        return inputArr;
    }

    private void askUserInput(Double[] inputArr, int i) {
        try {
            inputArr[i] = in.nextDouble();
        } catch (Exception e) {
            System.out.println("Błędne dane");
            askUserInput(inputArr, i);
        }
    }

}
