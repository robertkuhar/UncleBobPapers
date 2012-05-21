package org.rekdev.pasth.javamodel;

public class SKU {
    private Product product;

    public SKU( Product product ) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct( Product product ) {
        this.product = product;
    }
}
