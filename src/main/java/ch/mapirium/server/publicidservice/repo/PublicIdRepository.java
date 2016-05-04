package ch.mapirium.server.publicidservice.repo;

import ch.mapirium.server.publicidservice.model.PublicIdEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Datenbank-Zugriff auf die öffentlichen Schlüssel
 */
public interface PublicIdRepository extends CrudRepository<PublicIdEntity, Long> {

    @Query("SELECT p FROM PublicIdEntity p WHERE p.publicId = :publicId")
    public PublicIdEntity findByPublicId(@Param("publicId") String publicId);
}
