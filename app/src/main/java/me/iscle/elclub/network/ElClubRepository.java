package me.iscle.elclub.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ElClubRepository {
    private static final String BASE_URL = "http://192.168.1.33:3000/api/";

    private final ElClubService service;

    private ElClubRepository() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ElClubService.class);
    }

    public ElClubService getService() {
        return service;
    }

    private static ElClubRepository instance;

    public static ElClubRepository getInstance() {
        if (instance == null) {
            synchronized (ElClubRepository.class) {
                if (instance == null) {
                    instance = new ElClubRepository();
                }
            }
        }

        return instance;
    }
}
