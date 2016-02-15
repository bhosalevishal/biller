/*
 * Created on 22.12.2004
 *
 */
package org.vb.biller.model;

import org.tikeswing.core.YModel;
import org.vb.biller.bean.Customer;

/**
 * Customer view model.
 * 
 * @author Tomi Tuomainen
 */
public class CustomerModel extends YModel {
    
    // the Customer JavaBean
    private Customer customer;
    
    // JavaBean getters and setters 

    public Customer getCustomer() {
        return customer;
    }
    /**
     * This method notifies CustomerView that customer has changed.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
        // notifying a view and a controller that field "customer" has changed:
        notifyObservers("customer");
    }
}
