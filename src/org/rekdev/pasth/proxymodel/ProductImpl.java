package org.rekdev.pasth.proxymodel;


public class ProductImpl implements Product {
    private String name;
    private int price;

    public ProductImpl( String name, int price ) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName( String name ) {
        this.name = name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void setPrice( int price ) {
        this.price = price;
    }

}
