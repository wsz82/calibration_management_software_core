package spio2023.calibrationmanagementsoftware.api.rest.calibration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spio2023.calibrationmanagementsoftware.api.database.calibration.ControlPointToInputsWithResults;
import spio2023.calibrationmanagementsoftware.api.database.calibration.ControlPointToInputsWithResultsRepository;
import spio2023.calibrationmanagementsoftware.api.rest.SuperController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ControlPointToInputsWithResultsController extends SuperController<ControlPointToInputsWithResults, ControlPointToInputsWithResultsController> {

    private static final String controls = "controlPoints";
    private static final String control = "controlPoint";

    protected ControlPointToInputsWithResultsController(@Autowired ControlPointToInputsWithResultsRepository repository) {
        super(repository);
    }

    @GetMapping("/" + controls)
    public CollectionModel<EntityModel<ControlPointToInputsWithResults>> all() {
        return getAll();
    }

    @GetMapping("/" + controls + "/{id}")
    public EntityModel<ControlPointToInputsWithResults> one(@PathVariable Long id) {
        return getOne(id);
    }

    @PostMapping("/" + controls)
    public ResponseEntity<?> newEntity(@RequestBody ControlPointToInputsWithResults entity) {
        var entityModel = toModel(repository.save(entity));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @Override
    protected ControlPointToInputsWithResultsController getController() {
        return methodOn(ControlPointToInputsWithResultsController.class);
    }

    @Override
    protected String collectionName() {
        return controls;
    }

    @Override
    protected String entityName() {
        return control;
    }

}
