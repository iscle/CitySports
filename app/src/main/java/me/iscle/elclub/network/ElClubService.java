package me.iscle.elclub.network;

import java.util.List;
import java.util.Map;

import me.iscle.elclub.model.Jornada;
import me.iscle.elclub.model.Partit;
import me.iscle.elclub.model.Player;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ElClubService {

    @GET("match")
    Call<List<Partit>> getMatches();

    @GET("match/{id}")
    Call<Partit> getMatch(@Path("id") int matchId);

    @GET("player")
    Call<Player[]> getPlayersForMatch(@Query("match_id") int matchId);

    @POST("match/{id}")
    @FormUrlEncoded
    Call<Void> addMatchPlayer(@Path("id") int matchId, @Field("player_id") int player_id);

    @POST("match")
    Call<Partit> addMatch(@Body Map<String, Object> body);

    @POST("player")
    Call<Partit> addPlayer(@Body Map<String, Object> body);

    @GET("team/{id}/players")
    Call<List<Player>> getTeamPlayers(@Path("id") int teamId);

    @GET("team/{id}/torneig-regular/jornades")
    Call<List<Jornada>> getTeamTorneigRegularJornades(@Path("id") int teamId);

}
