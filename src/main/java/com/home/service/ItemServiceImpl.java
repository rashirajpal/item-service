package com.home.service;

import com.home.dto.Item;
import org.apache.cxf.helpers.FileUtils;
import org.apache.cxf.tools.util.FileWriterUtil;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;

/**
 * @author ashar61
 */
public class ItemServiceImpl implements ItemService {

    private final File directory = new File(System.getProperty("java.io.tmpdir"));

    @Override
    public Item get(String itemId) throws ServiceException {
        File itemFile = new File(directory.getAbsolutePath() + File.separator + itemId);
        if (itemFile.exists()) {
            String content = FileUtils.getStringFromFile(itemFile);
            try {
                return JacksonUtil.toObject(content, Item.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throwException("Item doesn't exists", Response.Status.NOT_FOUND);
        }
        return null;
    }


    public static void throwException(String message, Response.Status status) throws ServiceException {
        Response.ResponseBuilder builder = Response.status(status);
        try {
            builder.entity(JacksonUtil.toJson(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new WebApplicationException(builder.build());
    }


    @Override
    public Item create(Item item) throws ServiceException {


        File itemFile = new File(directory.getAbsolutePath() + File.separator + item.getItemId());
        if (itemFile.exists()) {
            throwException("Item already exists. Please update instead", Response.Status.BAD_REQUEST);
        } else {
            try {
                FileWriterUtil.getWriter(itemFile).write(JacksonUtil.toJson(item));
            } catch (IOException e) {
                throwException("Exception occurred in writing the item", Response.Status.INTERNAL_SERVER_ERROR);
            }
        }
        return item;
    }

    @Override
    public Item update(Item item) throws ServiceException {
        File itemFile = new File(directory.getAbsolutePath() + File.separator + item.getItemId());
        if (itemFile.exists()) {
            try {
                FileWriterUtil.getWriter(itemFile).write(JacksonUtil.toJson(item));
            } catch (IOException e) {
                throw new RuntimeException("Exception occurred in writing the item");
            }

        } else {
            throwException("Item doesn't exists. Please create instead", Response.Status.INTERNAL_SERVER_ERROR);
        }
        return item;
    }

    @Override
    public boolean delete(String itemId) throws ServiceException {
        File itemFile = new File(directory.getAbsolutePath() + File.separator + itemId);
        if (itemFile.exists()) {
            FileUtils.delete(itemFile);
            return true;
        } else {
            throwException("Item doesn't exists.", Response.Status.NOT_FOUND);
        }
        return false;
    }
}
