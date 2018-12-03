package dev.codecathode.LaunchTrack.Database.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static volatile Retrofit retrofit;
    private static final String BASE_URL = "https://launchlibrary.net/1.4/";

    public synchronized static GetLaunchDataService getLaunchDataService() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyyMMdd'T'HHmmss'Z'")
                .create();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit.create(GetLaunchDataService.class);
    }
}
