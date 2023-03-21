package spio2023.cms.core.procedure;

public interface StepInterface {

    void showMessage(String message);

    Double[] getReferencedInput();

    Double[] getCheckedInput();

}
