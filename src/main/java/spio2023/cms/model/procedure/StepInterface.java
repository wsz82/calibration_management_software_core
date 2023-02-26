package spio2023.cms.model.procedure;

public interface StepInterface {

    void showMessage(String message);

    Double[] getReferencedInput();

    Double[] getCheckedInput();

}
