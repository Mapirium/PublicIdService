package ch.mapirium.server.publicidservice.rest.model;

import ch.mapirium.server.publicidservice.model.PublicIdEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper zwischen der JPA und REST-Entität
 */
@Component
public class PublicIdMapper {
    public PublicIdResource fromEntity(PublicIdEntity entity) {
        PublicIdResource result = new PublicIdResource();
        result.setPublicId(entity.getPublicId());
        result.setCreatedAt(entity.getCreatedAt());
        return result;
    }
}
