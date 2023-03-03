package spio2023.calibrationmanagementsoftware.api.rest.calibration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import spio2023.calibrationmanagementsoftware.api.database.calibration.Settings;
import spio2023.calibrationmanagementsoftware.api.database.calibration.SettingsRepository;
import spio2023.calibrationmanagementsoftware.api.rest.SuperController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class SettingsController extends SuperController<Settings, SettingsController> {

    private static final String settings = "settings";
    private static final String setting = "setting";

    protected SettingsController(@Autowired SettingsRepository repository) {
        super(repository);
    }

    @GetMapping("/" + settings)
    public CollectionModel<EntityModel<Settings>> all() {
        return getAll();
    }

    @GetMapping("/" + settings + "/{id}")
    public EntityModel<Settings> one(@PathVariable Long id) {
        return getOne(id);
    }

    @Override
    protected SettingsController getController() {
        return methodOn(SettingsController.class);
    }

    @Override
    protected String collectionName() {
        return settings;
    }

    @Override
    protected String entityName() {
        return setting;
    }

}
