package ch.mapirium.server.publicidservice.repo;

import ch.mapirium.server.publicidservice.model.PublicIdEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Datenbank-Zugriff auf die öffentlichen Schlüssel
 */
public interface PublicIdRepository extends CrudRepository<PublicIdEntity, Long> {
}
