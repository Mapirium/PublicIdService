package ch.mapirium.server.publicidservice.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by surech on 04.05.16.
 */
@RestController
@RequestMapping(path = "/publicId")
public class PublicIdRestController {

    @RequestMapping(path = "/ping/{value}", method = RequestMethod.GET)
    public String ping(){
        return "Hallo Welt";
    }
}
