package spio2023.calibrationmanagementsoftware.api.rest.instrument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import spio2023.calibrationmanagementsoftware.api.database.instrument.InstrumentRepository;
import spio2023.calibrationmanagementsoftware.api.database.instrument.ReferenceInstrument;
import spio2023.calibrationmanagementsoftware.api.rest.SuperController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class InstrumentController extends SuperController<ReferenceInstrument, InstrumentController> {

    private static final String instruments = "instruments";
    private static final String instrument = "instrument";

    protected InstrumentController(@Autowired InstrumentRepository repository) {
        super(repository);
    }

    @GetMapping("/" + instruments)
    public CollectionModel<EntityModel<ReferenceInstrument>> all() {
        return getAll();
    }

    @GetMapping("/" + instruments + "/{id}")
    public EntityModel<ReferenceInstrument> one(@PathVariable Long id) {
        return getOne(id);
    }

    @Override
    protected InstrumentController getController() {
        return methodOn(InstrumentController.class);
    }

    @Override
    protected String collectionName() {
        return instruments;
    }

    @Override
    protected String entityName() {
        return instrument;
    }

}
