package spio2023.calibrationmanagementsoftware.api.model.procedure;

public interface StepInterface {

    void showMessage(String message);

    Double[] getReferencedInput();

    Double[] getCheckedInput();

}
