package com.begentgroup.sampleskopenapi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    EditText keywordView;
    ListView listView;
    ArrayAdapter<Product> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        keywordView = (EditText)findViewById(R.id.edit_keyword);
        listView = (ListView)findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);

        Button btn = (Button)findViewById(R.id.btn_search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = keywordView.getText().toString();
                if (!TextUtils.isEmpty(keyword)) {
                    new MySearchTask().execute(keyword);
                }
            }
        });
    }

    private static final String SEARCH_URL = "http://apis.skplanetx.com/11st/common/products?searchKeyword=%s&version=1";
    class MySearchTask extends AsyncTask<String, Integer, SearchResult> {
        @Override
        protected SearchResult doInBackground(String... params) {
            String keyword = params[0];
            try {
                String urlText = String.format(SEARCH_URL, URLEncoder.encode(keyword,"utf-8"));
                URL url = new URL(urlText);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestProperty("Accept","application/json");
                conn.setRequestProperty("appKey","458a10f5-c07e-34b5-b2bd-4a891e024c2a");
                int code = conn.getResponseCode();
                if (code >= 200 && code < 300) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while((line = br.readLine()) != null) {
                        sb.append(line).append("\n\r");
                    }
                    String s = sb.toString();
                    JSONObject jobject = new JSONObject(s);
                    SearchResult sr = new SearchResult(jobject);
                    return sr;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(SearchResult s) {
            super.onPostExecute(s);
            if (s != null) {
//                Toast.makeText(MainActivity.this, "result : " + s, Toast.LENGTH_SHORT).show();
                for (Product p : s.productSearchResponse.products.product) {
                    mAdapter.add(p);
                }
            } else {
                Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
