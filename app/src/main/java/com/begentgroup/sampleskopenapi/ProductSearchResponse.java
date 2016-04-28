package com.begentgroup.sampleskopenapi;

import org.json.JSONObject;

/**
 * Created by dongja94 on 2016-04-28.
 */
public class ProductSearchResponse {
    public Request request;
    public Products products;
    public ProductSearchResponse(JSONObject jobject) {
        if (jobject == null) return;
        JSONObject jrequest = jobject.optJSONObject("Request");
        JSONObject jproducts = jobject.optJSONObject("Products");
        request = new Request(jrequest);
        products = new Products(jproducts);
    }
}
