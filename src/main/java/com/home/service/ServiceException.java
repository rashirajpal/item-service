package com.home.service;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author ashar61
 */@XmlRootElement(name = "ServiceException")
   public class ServiceException extends Exception implements Serializable {

    private ServiceExceptionDetails faultDetails[];

    public ServiceException(ServiceExceptionDetails faultDetails[]) {
        this.faultDetails = faultDetails;
    }

    public ServiceException(String message, ServiceExceptionDetails faultDetails[]) {
        super(message);
        this.faultDetails = faultDetails;
    }

    public ServiceExceptionDetails[] getFaultDetails() {
        return faultDetails;
    }

}