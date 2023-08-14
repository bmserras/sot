package org.bmserras.sot.zabbix.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

public class Service {

    public Service() {
    }

    public Event getLatestData(int hostId) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");

        ObjectMapper objectMapper = new ObjectMapper();

        GetLatestValueInput getLatestValueInput = new GetLatestValueInput();
        getLatestValueInput.setJsonrpc("2.0");
        getLatestValueInput.setMethod("history.get");

        Params params = new Params();
        params.setLimit(3);
        params.setSortorder("DESC");
        params.setSortfield("clock");
        params.setHostids(hostId);

        getLatestValueInput.setParams(params);
        getLatestValueInput.setId(1);
        getLatestValueInput.setAuth("9be244ebbbc4a773bb8fab8c806d0205");

        String bodyRaw = objectMapper.writeValueAsString(getLatestValueInput);
        //System.out.println(bodyRaw);
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
        event.setHostId(hostId);
        return event;
    }

    public int getValueFromLatestData(Event event, int itemId) {
        return Integer.parseInt(event.getResult().stream()
                .filter(result -> result.getItemid().equals("" + itemId)).findFirst().get().getValue());
    }

}
