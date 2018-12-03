package dev.codecathode.LaunchTrack.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import dev.codecathode.LaunchTrack.Application.App;
import dev.codecathode.LaunchTrack.Database.LaunchBoundaryCallback;
import dev.codecathode.LaunchTrack.Database.Local.LaunchDao;
import dev.codecathode.LaunchTrack.Database.Model.Launch;
import dev.codecathode.LaunchTrack.Database.Model.MissionResponse;
import dev.codecathode.LaunchTrack.Database.Network.GetLaunchDataService;
import dev.codecathode.LaunchTrack.Database.Network.NetworkState;
import dev.codecathode.LaunchTrack.Database.Network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 *
 * The data should actually come to viewModel from the Repository class, which I have not used here.
 * Todo: Get data from repository instead of directly implementing it here in the viewModel
 *
 * That is both launchPagedList and MissionResponse should originate from Repository Class, and viewModel should just supply
 * it to the fragments.
 *
 *
 * communication between Fragments where I am passing through 'getSelected()' doesn't need repository class.
 *
 */


public class MainViewModel extends ViewModel {

    public LiveData<PagedList<Launch>> launchPagedList;
    private LaunchDao launchDao;

    private MutableLiveData<MissionResponse> missionResponse = new MutableLiveData<>();
    private MutableLiveData<NetworkState> networkStateMutableLiveData = new MutableLiveData<>();


    //    For communication between fragments through ViewModel
    private MutableLiveData<Launch> selected = new MutableLiveData<>();

    //TODO: Not implemented, filter between confirmed launches
    private MutableLiveData<Integer> selectedOption = new MutableLiveData<>();
    private LiveData<Integer> selectedInt;


    private LaunchBoundaryCallback launchBoundaryCallback = new LaunchBoundaryCallback();


    public MainViewModel() {
        launchDao = App.appDatabase().launchDao();
        initPaging(launchDao);


    }

    //Paging Library related code
    private void initPaging(LaunchDao launchDao) {

        PagedList.Config.Builder config = new PagedList.Config.Builder()
                .setPageSize(20)
                .setInitialLoadSizeHint(20)
                .setEnablePlaceholders(true);


        launchPagedList = new LivePagedListBuilder<>(launchDao.getAllLaunches(), config.build())
                .setBoundaryCallback(launchBoundaryCallback)
                .build();


    }


    //    To load the payloads a separate network call needs to be done since payload details isn't available in the first api link
    public LiveData<MissionResponse> getMission(int missionId) {
        loadMission(missionId);
        return missionResponse;

    }

    public LiveData<NetworkState> getNetworkStateMutableLiveData() {
        return networkStateMutableLiveData;
    }

    private void loadMission(int missionId) {

        networkStateMutableLiveData.setValue(NetworkState.LOADING);

        GetLaunchDataService getLaunchDataService = RetrofitInstance.getLaunchDataService();
        getLaunchDataService.getPayloads(missionId).enqueue(new Callback<MissionResponse>() {
            @Override
            public void onResponse(Call<MissionResponse> call, Response<MissionResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        missionResponse.setValue(response.body());

                        networkStateMutableLiveData.setValue(NetworkState.LOADED);
                    }
                } else {
                    networkStateMutableLiveData.setValue(NetworkState.FAILED);
                }

            }

            @Override
            public void onFailure(Call<MissionResponse> call, Throwable t) {

                networkStateMutableLiveData.setValue(NetworkState.FAILED);
            }
        });


    }


    //get the selected launch from Launch List Fragment
    public void select(Launch launch) {
        selected.setValue(launch);
    }

    //To show the selected in Details Fragment
    public LiveData<Launch> getSelected() {
        return selected;
    }


    public void refreshLaunch() {

        //Paging library automatically calls new data if the database is deleted, so it would get new data automatically
        //BUG: If all data is loaded and then page is refresh, it will not call new next launches on Scroll, the app needs to be
        //closed and re opened.

        App.getAppExecutors().diskIO().execute(() -> launchDao.deleteAll());
    }


}



