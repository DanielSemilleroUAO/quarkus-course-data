package org.daniel.quickstart;

import io.smallrye.common.constraint.NotNull;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/dev")
public class GreetingResource {

    // @Inject
    // EntityManager em;

    @Inject
    DeveloperRepository developerRepository;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDev(Developer dev) {
        //em.persist(dev);
        dev.persist();
        return Response.created(URI.create("/dev/" + dev.id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Developer> getAllDevs() {
        return Developer.findAll().list();
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Developer findByName(@NotNull @PathParam("name") String name) {
        return Developer.find("name", name).firstResult();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{name}/{age}")
    public Developer findByNameAge(@NotNull @PathParam("name") String name, @PathParam("age") Integer age) {
        return Developer.find("name = ?1 and age = ?2", name, age).firstResult();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Developer getDev(@PathParam("id") Long id) {
        return Developer.findById(id);
    }
}