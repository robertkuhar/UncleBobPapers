package org.rekdev.pasth.proxymodel;

public interface Order {
    public String getCustomerId();

    public void setCustomerId( String customerId );

    public void addItem( Product p, int qty );

    public int total();
}
