package ch.mapirium.server.publicidservice.service;

import ch.mapirium.server.publicidservice.model.PublicIdEntity;
import ch.mapirium.server.publicidservice.repo.PublicIdRepository;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * Enthält die Logik für die öffentlichen Schlüssel
 */
@Component
public class PublicIdService {

    /** Unser sicherer Zufallsgenerator */
    private final SecureRandom random = new SecureRandom();

    /** Liste der erlaubten Zeichen */
    private final char[] allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    @Autowired
    private PublicIdRepository publicIdRepository;

    public PublicIdEntity createNewPublicId(String prefix, int length) {
        // Schlüssel erstellen
        String key = generatePublicKey(prefix, length);

        // Entität erstellen
        PublicIdEntity entity = new PublicIdEntity();
        entity.setPublicId(key);

        // Schlüssel speichern
        PublicIdEntity result = publicIdRepository.save(entity);
        return result;
    }

    protected String generatePublicKey(String prefix, int length) {
        // Zufälliger String in der gewünschten Länge erstellen
        String randomKey = RandomStringUtils.random(length, 0, allowedChars.length, true, true, allowedChars, random);

        // Wenn vorhanden das Prefix hinzufügen
        if(prefix != null){
            randomKey = prefix + randomKey;
        }
        return randomKey;
    }
}
