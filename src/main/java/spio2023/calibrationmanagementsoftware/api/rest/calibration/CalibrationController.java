package spio2023.calibrationmanagementsoftware.api.rest.calibration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spio2023.calibrationmanagementsoftware.api.database.calibration.Calibration;
import spio2023.calibrationmanagementsoftware.api.database.calibration.CalibrationRepository;
import spio2023.calibrationmanagementsoftware.api.database.results.DoubleValue;
import spio2023.calibrationmanagementsoftware.api.model.engine.DefaultCalibrationEngine;
import spio2023.calibrationmanagementsoftware.api.model.procedure.TestStepInterface;
import spio2023.calibrationmanagementsoftware.api.model.sample.SampleData_PP_METEX_3610;
import spio2023.calibrationmanagementsoftware.api.rest.EntityNotFoundException;
import spio2023.calibrationmanagementsoftware.api.rest.SuperController;

import java.util.Stack;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CalibrationController extends SuperController<Calibration, CalibrationController> {

    private static final String calibrations = "calibrations";
    private static final String calibration = "calibration";

    protected CalibrationController(@Autowired CalibrationRepository repository) {
        super(repository);
    }

    @GetMapping("/" + calibrations)
    public CollectionModel<EntityModel<Calibration>> all() {
        return getAll();
    }

    @GetMapping("/" + calibrations + "/{id}")
    public EntityModel<Calibration> one(@PathVariable Long id) {
        return getOne(id);
    }

    @PostMapping("/" + calibrations)
    public ResponseEntity<?> newEntity(@RequestBody Calibration entity) {
        EntityModel<Calibration> entityModel = toModel(repository.save(entity));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @Override
    protected Link[] additionalLinks(Calibration entity) {
        return new Link[]{
                linkTo(methodOn(ControlPointToInputsWithResultsController.class).all()).withRel(collectionName())
        };
    }

    @GetMapping("/" + calibrations + "/{id}/" + "output")
    public String output(@PathVariable Long id) {
        var one = getOne(id);
        var calibration = one.getContent();
        if (calibration == null) {
            throw new EntityNotFoundException(entityName(), id);
        }
        var output = calibration.getControlPointToInputsWithResults();
        var referenceInputs = new Stack<Double>();
        var testInputs = new Stack<Double>();
        output.forEach(item -> {
            var reference = item.getInputs().getReferenceDoubleValues().stream()
                    .map(DoubleValue::getInternalValue)
                    .toList();
            referenceInputs.addAll(reference);
            var test = item.getInputs().getTestDoubleValues().stream()
                    .map(DoubleValue::getInternalValue)
                    .toList();
            testInputs.addAll(test);
        });
        var modelProcedure = SampleData_PP_METEX_3610.procedure();
        var engine = new DefaultCalibrationEngine(new TestStepInterface(modelProcedure.getSettings(), referenceInputs, testInputs));
        var calibrationOutput = engine.runCalibration(
                modelProcedure,
                SampleData_PP_METEX_3610.multimeter_INMEL7000()
        );
        return calibrationOutput.toString();
    }

    @Override
    protected CalibrationController getController() {
        return methodOn(CalibrationController.class);
    }

    @Override
    protected String collectionName() {
        return calibrations;
    }

    @Override
    protected String entityName() {
        return calibration;
    }

}
