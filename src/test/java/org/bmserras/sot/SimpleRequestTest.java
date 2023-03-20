package org.bmserras.sot;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.asynchttpclient.Param;
import org.bmserras.sot.api.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class SimpleRequestTest {

    @Test
    public void http() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"jsonrpc\": \"2.0\",\r\n    \"method\": \"apiinfo.version\",\r\n    \"params\": {},\r\n    \"id\": 1\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8990/api_jsonrpc.php")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    @Test
    public void httpWithObjects() throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");

        GetVersionInput getVersionInput = new GetVersionInput();
        getVersionInput.setJsonrpc("2.0");
        getVersionInput.setMethod("apiinfo.version");
        getVersionInput.setId(1);

        RequestBody body = RequestBody.create(getVersionInput.toString(),
                mediaType);

        Request request = new Request.Builder()
                .url("http://localhost:8990/api_jsonrpc.php")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = response.body().string();
        Version version = objectMapper.readValue(json, Version.class);
        System.out.println(version.toString());
    }

    @Test
    public void httpWithObjectsBetter() throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");

        ObjectMapper objectMapper = new ObjectMapper();

        GetVersionInput getVersionInput = new GetVersionInput();
        getVersionInput.setJsonrpc("2.0");
        getVersionInput.setMethod("apiinfo.version");
        getVersionInput.setParams(new ArrayList<>());
        getVersionInput.setId(1);

        String bodyRaw = objectMapper.writeValueAsString(getVersionInput);
        System.out.println(bodyRaw);
        RequestBody body = RequestBody.create(bodyRaw,
                mediaType);

        Request request = new Request.Builder()
                .url("http://localhost:8990/api_jsonrpc.php")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();

        String json = response.body().string();
        Version version = objectMapper.readValue(json, Version.class);
        System.out.println(version.toString());
    }

    @Test
    public void httpWithEvents() throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");

        ObjectMapper objectMapper = new ObjectMapper();

        GetLatestValueInput getLatestValueInput = new GetLatestValueInput();
        getLatestValueInput.setJsonrpc("2.0");
        getLatestValueInput.setMethod("history.get");

        Params params = new Params();
        params.setLimit(1);
        params.setSortorder("DESC");
        params.setSortfield("clock");
        params.setHostids(10558);

        getLatestValueInput.setParams(params);
        getLatestValueInput.setId(1);
        getLatestValueInput.setAuth("9be244ebbbc4a773bb8fab8c806d0205");

        String bodyRaw = objectMapper.writeValueAsString(getLatestValueInput);
        System.out.println(bodyRaw);
        RequestBody body = RequestBody.create(bodyRaw,
                mediaType);

        Request request = new Request.Builder()
                .url("http://localhost:8990/api_jsonrpc.php")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();

        String json = response.body().string();
        Event event = objectMapper.readValue(json, Event.class);
        System.out.println(event.toString());
    }

}
