package com.home.service;


import com.home.dto.Item;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author ashar61
 */
@Path("/item")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public interface ItemService {
    @GET
    @Path("/{itemId}")
    public Item get(@PathParam("itemId") String itemId) throws ServiceException;

    @POST
    public Item create(Item item) throws ServiceException;

    @PUT
    public Item update(Item item) throws ServiceException;

    @DELETE
    @Path("/{itemId}")
    public boolean delete(@PathParam("itemId") String itemId) throws ServiceException;
}
