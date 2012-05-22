package org.rekdev.pasth.proxymodel;

public interface Product {
    public String getSku() throws Exception;

    public void setSku( String sku ) throws Exception;

    public String getName() throws Exception;

    public void setName( String name ) throws Exception;

    public int getPrice() throws Exception;

    public void setPrice( int price ) throws Exception;
}
