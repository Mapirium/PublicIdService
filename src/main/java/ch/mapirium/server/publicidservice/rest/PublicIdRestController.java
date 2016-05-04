package ch.mapirium.server.publicidservice.rest;

import ch.mapirium.server.publicidservice.model.PublicIdEntity;
import ch.mapirium.server.publicidservice.rest.model.PublicIdMapper;
import ch.mapirium.server.publicidservice.rest.model.PublicIdResource;
import ch.mapirium.server.publicidservice.service.PublicIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST-Schnittstelle für die öffentlichen Schlüssel
 */
@RestController
@RequestMapping(path = "/publicId")
public class PublicIdRestController {

    @Autowired
    private PublicIdService publicIdService;

    @Autowired
    private PublicIdMapper mapper;

    @RequestMapping(path = "/ping/{value}", method = RequestMethod.GET)
    public String ping(@PathVariable("value") String value){
        return "Hallo Welt, " + value;
    }

    @RequestMapping(path = "/{prefix}", method = RequestMethod.POST)
    public PublicIdResource createNew(@PathVariable("prefix") String prefix){
        // Schlüssel anlegen
        PublicIdEntity newPublicId = publicIdService.createNewPublicId(prefix, 8);

        // Mappen
        PublicIdResource resource = mapper.fromEntity(newPublicId);
        return resource;
    }
}
