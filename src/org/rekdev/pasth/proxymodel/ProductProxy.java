package org.rekdev.pasth.proxymodel;

public class ProductProxy implements Product {
    private String sku;

    public ProductProxy( String sku ) {
        this.sku = sku;
    }

    @Override
    public String getSku() throws Exception {
        return sku;
    }

    public void setSku( String sku ) throws Exception {
        // Should it even be legal to set sku? Its the PK, after all.
        // TODO: error check pd == null and do something not so stupid
        ProductData pd = DB.getProductData( sku );
        this.sku = sku;
        DB.store( pd );
    }

    @Override
    public String getName() throws Exception {
        ProductData pd = DB.getProductData( sku );
        // TODO: error check pd == null and do something not so stupid
        return pd.name;
    }

    @Override
    public void setName( String name ) throws Exception {
        ProductData pd = DB.getProductData( sku );
        // TODO: error check pd == null and do something not so stupid
        pd.name = name;
        DB.store( pd );
    }

    @Override
    public int getPrice() throws Exception {
        ProductData pd = DB.getProductData( sku );
        // TODO: error check pd == null and do something not so stupid
        return pd.price;
    }

    @Override
    public void setPrice( int price ) throws Exception {
        ProductData pd = DB.getProductData( sku );
        // TODO: error check pd == null and do something not so stupid
        pd.price = price;
        DB.store( pd );
    }

}
