package com.begentgroup.sampleskopenapi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dongja94 on 2016-04-28.
 */
public class Products {
    public int totalCount;
    public ArrayList<Product> product = new ArrayList<Product>();
    public Products(JSONObject jobject) {
        if (jobject == null) return;
        totalCount = jobject.optInt("TotalCount");
        JSONArray jproductarray = jobject.optJSONArray("Product");
        if (jproductarray == null) return;
        for (int i = 0 ; i < jproductarray.length(); i++) {
            JSONObject jproduct = jproductarray.optJSONObject(i);
            product.add(new Product(jproduct));
        }
    }
}
