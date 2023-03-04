package spio2023.calibrationmanagementsoftware.api.rest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import spio2023.calibrationmanagementsoftware.api.database.BaseEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public abstract class SuperController<E extends BaseEntity, C> {

    protected final JpaRepository<E, Long> repository;

    public SuperController(JpaRepository<E, Long> repository) {
        this.repository = repository;
    }

    protected CollectionModel<EntityModel<E>> getAll(Link... links) {
        List<EntityModel<E>> entities = repository.findAll().stream()
                .map(entity -> toModel(entity, links))
                .collect(Collectors.toList());

        return CollectionModel.of(entities, linkTo(getController().all()).withSelfRel());
    }

    protected EntityModel<E> getOne(Long id, Link... links) {
        var entity = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException(entityName(), id));
        var allLinks = getAllLinks(entity, links);
        return EntityModel.of(entity, allLinks);
    }

    protected EntityModel<E> toModel(E entity, Link... links) {
        var allLinks = getAllLinks(entity, links);
        return EntityModel.of(entity, allLinks);
    }

    private List<Link> getAllLinks(E entity, Link[] links) {
        var basicLinks = basicLinks(entity.getId());
        var addLinks = additionalLinks(entity);
        return Stream.of(basicLinks, addLinks, links)
                .flatMap(Stream::of)
                .toList();
    }

    private Link[] basicLinks(Long id) {
        return new Link[]{
                linkTo(getController().one(id)).withSelfRel(),
                linkTo(getController().all()).withRel(collectionName())
        };
    }

    protected Link[] additionalLinks(E entity) {
        return new Link[]{};
    }

    public abstract CollectionModel<EntityModel<E>> all();

    public abstract EntityModel<E> one(@PathVariable Long id);

    protected abstract SuperController<E,C> getController();

    protected abstract String collectionName();

    protected abstract String entityName();

}
