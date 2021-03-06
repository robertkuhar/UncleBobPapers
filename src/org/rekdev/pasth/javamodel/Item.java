package org.rekdev.pasth.javamodel;

public class Item {
    private Order order;
    private Product product;
    private int qty;

    public Item( Order order, Product product, int qty ) {
        this.order = order;
        this.product = product;
        this.qty = qty;;
    }
    
    public Order getOrder() {
        return order;
    }

    public void setOrder( Order order ) {
        this.order = order;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public void setProduct( Product product ) {
        this.product = product;
    }
    
    public int getQty() {
        return qty;
    }

    public void setQty( int qty ) {
        this.qty = qty;
    }
}
