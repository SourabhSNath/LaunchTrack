package dev.codecathode.LaunchTrack.Database.Network;

import dev.codecathode.LaunchTrack.Database.Model.LaunchDbResponse;
import dev.codecathode.LaunchTrack.Database.Model.MissionResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetLaunchDataService {

    @GET("launch?mode=verbose")
    Call<LaunchDbResponse> getLaunchResult(
            @Query("startdate") String start,
            @Query("limit") int limit,
            @Query("offset") int offset);

//    @GET("launch?desc")
//    Call<LaunchDbResponse> getPreviousLaunches(
//            @Query("startDate")String start,
//            @Query("limit") int limit);

    @GET("mission/{id}")
    Call<MissionResponse> getPayloads(@Path("id") Integer missionId);
}
