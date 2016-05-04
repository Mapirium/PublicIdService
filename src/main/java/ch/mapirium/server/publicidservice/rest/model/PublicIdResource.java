package ch.mapirium.server.publicidservice.rest.model;

import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

/**
 * Created by surech on 04.05.16.
 */
public class PublicIdResource extends ResourceSupport {

    private String publicId;

    private Date createdAt;


}
