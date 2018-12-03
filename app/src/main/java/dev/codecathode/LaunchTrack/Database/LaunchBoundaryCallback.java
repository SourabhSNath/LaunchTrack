package dev.codecathode.LaunchTrack.Database;

import android.util.Log;

import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;
import dev.codecathode.LaunchTrack.Application.App;
import dev.codecathode.LaunchTrack.Application.AppExecutors;
import dev.codecathode.LaunchTrack.Database.Local.LaunchDao;
import dev.codecathode.LaunchTrack.Database.Model.Launch;
import dev.codecathode.LaunchTrack.Database.Model.LaunchDbResponse;
import dev.codecathode.LaunchTrack.Database.Network.GetLaunchDataService;
import dev.codecathode.LaunchTrack.Database.Network.NetworkState;
import dev.codecathode.LaunchTrack.Database.Network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Using Architecture Component's Paging Library to load data on scroll only. Saves data since only the data required is loaded
 * on scroll.
 * Everything downloaded is saved until we check for new data from the server. (Not implemented yet, manually erase storage to check
 * for new data)
 * <p>
 * This class is then used in MainViewModel and then in listFragment
 * <p>
 * Todo: Delete data and look for new data
 */

public class LaunchBoundaryCallback extends PagedList.BoundaryCallback<Launch> {
    private GetLaunchDataService getLaunchDataService;
    private AppExecutors appExecutors;

    private MutableLiveData<NetworkState> networkState;
    private MutableLiveData<NetworkState> initialLoad;


    private int lastOffset = 0;
    private boolean isLoading = false;

    private LaunchDao launchDao;

    private ZoneId zonedId = ZoneId.systemDefault();
    private LocalDate today = LocalDate.now(zonedId);
//    private LocalDate yesterday = today.minusDays(1);

    public LaunchBoundaryCallback() {
        this.getLaunchDataService = RetrofitInstance.getLaunchDataService();
        this.appExecutors = App.getAppExecutors();
        this.launchDao = App.appDatabase().launchDao();

        this.initialLoad = new MutableLiveData<>();
        this.networkState = new MutableLiveData<>();
    }



    public MutableLiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    public MutableLiveData<NetworkState> getInitalLoad() {
        return initialLoad;
    }

    @Override
    public void onZeroItemsLoaded() {

        initialLoad.postValue(NetworkState.LOADING);
        networkState.postValue(NetworkState.LOADING);

//        Log.d("BUB", "onZeroItemsLoaded: called");
        appExecutors.networkIO().execute(() -> getLaunchDataService.getLaunchResult(today.toString(), 20, 0)
                .enqueue(new Callback<LaunchDbResponse>() {
                    @Override
                    public void onResponse(Call<LaunchDbResponse> call, Response<LaunchDbResponse> response) {
                        if (response.isSuccessful()) {
                            appExecutors.diskIO().execute(() -> {
                                if (response.body() != null) {
                                    launchDao.saveAll(response.body().getLaunches());
                                }
                            });

                            isLoading = false;

                            networkState.postValue(NetworkState.LOADED);
                            initialLoad.postValue(NetworkState.LOADED);
                        } else {
                            initialLoad.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                            networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));

                        }
                    }

                    @Override
                    public void onFailure(Call<LaunchDbResponse> call, Throwable t) {

                        String errorMessage = t.getMessage();
                        initialLoad.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
                        networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));

                    }
                }));
    }


    @Override
    public void onItemAtEndLoaded(@NonNull Launch itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);

        if (isLoading) return;
        isLoading = true;
//        Log.d("BUB", "onItemAtEndLoaded: Items");

        networkState.postValue(NetworkState.LOADING);

        appExecutors.networkIO().execute(() -> {

//            Log.d("TAG", "onItemAtEndLoaded: " + lastOffset);
            getLaunchDataService.getLaunchResult(today.toString(), 20, lastOffset)
                    .enqueue(new Callback<LaunchDbResponse>() {
                        @Override
                        public void onResponse(Call<LaunchDbResponse> call, Response<LaunchDbResponse> response) {
                            if (response.isSuccessful()) {
                                appExecutors.diskIO().execute(() -> {
                                    if (response.body() != null) {
                                        launchDao.saveAll(response.body().getLaunches());
                                    }
                                });

                                lastOffset = lastOffset + 20;
                                isLoading = false;

                                networkState.postValue(NetworkState.LOADED);

                            } else {

                                networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                            }
                        }

                        @Override
                        public void onFailure(Call<LaunchDbResponse> call, Throwable t) {
                            isLoading = false;
                            String errorMessage = t.getMessage();
                            networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
                        }
                    });
        });

    }


}


