/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.UserAccount;
import persistence.Property;

/**
 *
 * @author jeffreybettles
 */
@Named(value = "createPropertyBean")
@RequestScoped
public class CreatePropertyBean {
    private double rentalFee;
    private String propertyType;
    private String propertyDescription;
    private String owner;
    @PersistenceContext(unitName = "OPRPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    private String status;

    public void setRentalFee(double rentalFee) {
        this.rentalFee = rentalFee;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getRentalFee() {
        return rentalFee;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public String getOwner() {
        return owner;
    }

    public String getStatus() {
        return status;
    }

    /**
     * Creates a new instance of CreaterPropertyBean
     */
    public CreatePropertyBean() {
    }
    
    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
    
    public void addProperty() {
        try {
            Property prop = new Property();
            prop.setOwner(owner);
            prop.setRentalFee(rentalFee);
            prop.setPropertyDescription(propertyDescription);
            prop.setPropertyType(propertyType);
            persist(prop);
            status="New Property Created Successfully";
        } catch (RuntimeException ex ) {
            Logger.getLogger(CreatePropertyBean.class.getName()).log(Level.SEVERE, null, ex);
            status="Error While Creating Property Account";
        }
    }
}
