package ch.mapirium.server.publicidservice.rest;

import ch.mapirium.server.publicidservice.exception.NotFoundException;
import ch.mapirium.server.publicidservice.model.PublicIdEntity;
import ch.mapirium.server.publicidservice.repo.PublicIdRepository;
import ch.mapirium.server.publicidservice.rest.model.PublicIdMapper;
import ch.mapirium.server.publicidservice.rest.model.PublicIdResource;
import ch.mapirium.server.publicidservice.service.PublicIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * REST-Schnittstelle für die öffentlichen Schlüssel
 */
@RestController
@RequestMapping(path = "/publicId")
public class PublicIdRestController {

    @Autowired
    private PublicIdService publicIdService;

    @Autowired
    private PublicIdRepository publicIdRepository;

    @Autowired
    private PublicIdMapper mapper;

    @RequestMapping(path = "/{prefix}", method = RequestMethod.POST)
    public PublicIdResource createNew(@PathVariable("prefix") String prefix){
        // Schlüssel anlegen
        PublicIdEntity newPublicId = publicIdService.createNewPublicId(prefix, 8);

        // Mappen
        PublicIdResource resource = mapper.fromEntity(newPublicId);
        return resource;
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public PublicIdResource getPublicId(@PathVariable("id") String id){
        // Eintrag suchen
        PublicIdEntity publicId = publicIdRepository.findByPublicId(id);

        // Entweder einen Fehler werfen oder das Resultat mappen und zurückgeben
        if (publicId == null) {
            throw new NotFoundException("Keine ID mit dem Schlüssel " + id + "gefunden");
        } else {
            PublicIdResource result = mapper.fromEntity(publicId);
            return result;
        }
    }
}
