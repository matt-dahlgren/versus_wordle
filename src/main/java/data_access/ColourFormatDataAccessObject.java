package data_access;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ColourFormatDataAccessObject implements ColourFormatAccessInterface {

    @Override
    public List<List<Integer>> getNumbers() {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();

        String url = "http://colormind.io/api/";

        String input = "{\"model\": \"default\"}"; // input to grab random colour palette.

        RequestBody requestBody = RequestBody.create(input, MediaType.parse("application/json"));

        final Request request = new Request.Builder().url(url).post(requestBody)
                .addHeader("Content-Type", "application/json").build();

        try (Response response = client.newCall(request).execute()) {

            if (response.isSuccessful()) {

                if (response.body() == null) {

                    throw new RuntimeException("Response from color.io is null");
                }

                String responseBodyString  = response.body().string();
                final JSONObject responseBody = new JSONObject(responseBodyString);
                final JSONArray data = responseBody.getJSONArray("result");

                List<List<Integer>> result = new ArrayList<>();

                for (int i = 0; i < data.length(); i++) {

                    JSONArray innerList = data.getJSONArray(i);
                    List<Integer> intList = new ArrayList<>();
                    for (int j = 0; j < innerList.length(); j++) {

                        intList.add(innerList.getInt(j));
                    }
                    result.add(intList);
                }

                return result;
            } else {

                throw new RuntimeException("Failed to grab an array of integer lists from color.io");
            }

        } catch (final IOException | JSONException e) {

            throw new RuntimeException(e);
        }
    }
}
