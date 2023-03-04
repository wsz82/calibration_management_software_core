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
import spio2023.calibrationmanagementsoftware.api.rest.SuperController;

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
