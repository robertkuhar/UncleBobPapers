package org.rekdev.pasth.javamodel;

public class Customer {
    private String name;
    private String address;
    private String billingInfo;
    
    public Customer( String name, String address, String billingInfo ) {
        this.name = name;
        this.address = address;
        this.billingInfo = billingInfo;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress( String address ) {
        this.address = address;
    }

    public String getBillingInfo() {
        return billingInfo;
    }

    public void setBillingInfo( String billingInfo ) {
        this.billingInfo = billingInfo;
    }

}
