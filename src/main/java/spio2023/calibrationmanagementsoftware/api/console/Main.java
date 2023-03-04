package spio2023.calibrationmanagementsoftware.api.console;

import lombok.Data;
import spio2023.calibrationmanagementsoftware.api.model.instrument.ReferenceInstrument;
import spio2023.calibrationmanagementsoftware.api.model.procedure.Procedure;
import spio2023.calibrationmanagementsoftware.api.model.sample.SampleData_BC06;
import spio2023.calibrationmanagementsoftware.api.model.sample.SampleData_PP_METEX_3610;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;

public class Main {

    public static Map<String, Supplier<ProcedureWithInstrument>> procedureNameToSupplier = Map.of(
            "BC06", () -> {
                return new ProcedureWithInstrument(SampleData_BC06.procedure(), SampleData_BC06.thermometer_P755());
            },
            "PP_METEX_3610", () -> {
                return new ProcedureWithInstrument(SampleData_PP_METEX_3610.procedure(), SampleData_PP_METEX_3610.multimeter_INMEL7000());
            }
    );

    public static void main(String[] args) {
        var procedureNames = procedureNameToSupplier.keySet().toArray(new String[]{});
        if (args.length == 0) {
            System.out.println("Nie podano nazwy procedury. Dostępne procedury: " + Arrays.toString(procedureNames));
            return;
        }
        var procedureName = args[0];
        var supplier = procedureNameToSupplier.get(procedureName);
        if (supplier == null) {
            System.out.println("Nie rozpoznano procedury. Dostępne procedury: " + Arrays.toString(procedureNames));
            return;
        }
        var procedureWithInstrument = supplier.get();
        var output = new ConsoleApplication(procedureWithInstrument.procedure, procedureWithInstrument.referenceInstrument).run();
        var report = new Report(output);
        report.print();
    }

    @Data
    private static class ProcedureWithInstrument {
        private final Procedure procedure;
        private final ReferenceInstrument referenceInstrument;
    }

}
