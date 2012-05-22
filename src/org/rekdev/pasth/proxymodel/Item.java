package org.rekdev.pasth.proxymodel;

public interface Item {
    public Order getOrder();

    public void setOrder( Order order );

    public Product getProduct();

    public void setProduct( Product product );

    public int getQty();

    public void setQty( int qty );
}
