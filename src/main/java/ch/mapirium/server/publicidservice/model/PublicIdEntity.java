package ch.mapirium.server.publicidservice.model;

import ch.mapirium.server.common.jpa.model.CreateTimeTrackEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Repräsentiert einen öffentlichen Schlüssel
 */
@Entity
@Table(name = "publicids")
public class PublicIdEntity extends CreateTimeTrackEntity {

    @Column(name = "PUBLIC_ID", nullable = false, unique = true)
    public String publicId;

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }
}
