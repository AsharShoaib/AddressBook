package com.example.asharshoaib.addressbook;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by asharshoaib on 2016-10-17.
 */

public class OkHttpApiCall {

    public static String GET(OkHttpClient client, HttpUrl url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static class RequestBuilder {

        public static HttpUrl buildURL() {
            return new HttpUrl.Builder()
                    .scheme("https")
                    .host("api.randomuser.me")
                    .addPathSegment("")
                    .addQueryParameter("results", "50")
                    .build();
        }
    }
}
