package spio2023.calibrationmanagementsoftware.api.rest.deivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import spio2023.calibrationmanagementsoftware.api.database.device.DeviceRepository;
import spio2023.calibrationmanagementsoftware.api.database.device.TestDevice;
import spio2023.calibrationmanagementsoftware.api.rest.SuperController;
import spio2023.calibrationmanagementsoftware.api.rest.procedure.ProcedureController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class DeviceController extends SuperController<TestDevice, DeviceController> {

    private static final String devices = "devices";
    private static final String device = "device";

    protected DeviceController(@Autowired DeviceRepository repository) {
        super(repository);
    }

    @GetMapping("/" + devices)
    public CollectionModel<EntityModel<TestDevice>> all() {
        return getAll();
    }

    @GetMapping("/" + devices+ "/{id}")
    public EntityModel<TestDevice> one(@PathVariable Long id) {
        return getOne(id);
    }

    @Override
    protected Link[] additionalLinks(TestDevice entity) {
        return new Link[]{
                linkTo(methodOn(ProcedureController.class).one(entity.getProcedureData().getId())).withSelfRel()
        };
    }

    @Override
    protected DeviceController getController() {
        return methodOn(DeviceController.class);
    }

    @Override
    protected String collectionName() {
        return devices;
    }

    @Override
    protected String entityName() {
        return device;
    }

}
