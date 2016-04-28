package com.begentgroup.sampleskopenapi;

import org.json.JSONObject;

/**
 * Created by dongja94 on 2016-04-28.
 */
public class SearchResult {
    public ProductSearchResponse productSearchResponse;
    public SearchResult(JSONObject jobject) {
        if (jobject == null) return;
        JSONObject jpsr = jobject.optJSONObject("ProductSearchResponse");
        productSearchResponse = new ProductSearchResponse(jpsr);
    }
}
