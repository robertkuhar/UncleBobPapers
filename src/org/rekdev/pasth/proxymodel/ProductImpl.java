package org.rekdev.pasth.proxymodel;

public class ProductImpl implements Product {
    private String sku;
    private String name;
    private int price;

    public ProductImpl( String sku, String name, int price ) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    @Override
    public String getSku() throws Exception {
        return sku;
    }

    public void setSku( String sku ) throws Exception {
        this.sku = sku;
    }

    @Override
    public String getName() throws Exception {
        return name;
    }

    @Override
    public void setName( String name ) throws Exception {
        this.name = name;
    }

    @Override
    public int getPrice() throws Exception {
        return price;
    }

    @Override
    public void setPrice( int price ) throws Exception {
        this.price = price;
    }

}
