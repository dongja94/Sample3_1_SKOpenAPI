package com.begentgroup.sampleskopenapi;

import org.json.JSONObject;

/**
 * Created by dongja94 on 2016-04-28.
 */
public class Product {
    public int productCode;
    public String productName;
    public int productPrice;
    public String productImage;

    public Product(JSONObject jobject) {
        if (jobject == null) return;
        productCode = jobject.optInt("ProductCode");
        productName = jobject.optString("ProductName");
        productPrice = jobject.optInt("ProductPrice");
        productImage = jobject.optString("ProductImage");
    }

    @Override
    public String toString() {
        return productName;
    }
}
