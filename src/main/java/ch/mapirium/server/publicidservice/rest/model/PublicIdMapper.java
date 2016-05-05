package ch.mapirium.server.publicidservice.rest.model;

import ch.mapirium.server.publicidservice.model.PublicIdEntity;
import ch.mapirium.server.publicidservice.rest.PublicIdRestController;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Mapper zwischen der JPA und REST-Entität
 */
@Component
public class PublicIdMapper {
    public PublicIdResource fromEntity(PublicIdEntity entity) {
        PublicIdResource result = new PublicIdResource();
        result.setPublicId(entity.getPublicId());
        result.setCreatedAt(entity.getCreatedAt());

        result.add(linkTo(methodOn(PublicIdRestController.class).getPublicId(result.getPublicId())).withSelfRel());

        return result;
    }
}
