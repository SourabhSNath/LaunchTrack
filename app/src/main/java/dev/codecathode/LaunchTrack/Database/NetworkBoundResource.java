package dev.codecathode.LaunchTrack.Database;

/**
 * Used to load the entire data from network call.
 * This is no more used in the app, instead replaced with LaunchBoundaryCallback for paging library
 */


//v= request type
public abstract class NetworkBoundResource<ResultType, RequestType> {

//    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();
//    private final AppExecutors appExecutors;
//
//    // Return to LiveData representing the data source
//    public final LiveData<Resource<ResultType>> getAsLiveData() {
//        return result;
//    }
//
//    @MainThread
//    protected NetworkBoundResource(AppExecutors appExecutors) {
//        this.appExecutors = appExecutors;
//
//        // Display data when loading, equivalent to placeHolder placeholder data
//        result.setValue(Resource.loading(null));
//
//        // Load local data from the database
//        LiveData<ResultType> dbSource = loadFromDb();
//
//        // Listen to local data
//        result.addSource(dbSource, data -> {
//
//            // Cancel the listen to local data first
//            // Re-listen to the local data, the reason it is canceled and then listened again is
//            // because the re-monitoring can quickly distribute the latest data
//
//            result.removeSource(dbSource);
//            // Determine whether you need to get data from the network
//            if (shouldFetch(data)) {
//                fetchFromNetwork(dbSource);
//            } else {
//                // Listen to local data
//                result.addSource(dbSource, newData -> {
//                    // final data
//                    result.setValue(Resource.success(newData));
//                });
//            }
//        });
//    }
//
//    private void fetchFromNetwork(final LiveData<ResultType> dbSource) {
//
//        // Send a request to get data from the network
//
//        createCall().enqueue(new Callback<RequestType>() {
//            @Override
//            public void onResponse(@NonNull Call<RequestType> call, @Nullable Response<RequestType> response) {
//                result.removeSource(dbSource);
//                if (response != null) {
//                    saveResultAndReInit(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<RequestType> call, @NonNull Throwable t) {
//                result.removeSource(dbSource);
//                result.addSource(dbSource, newData -> result.setValue(Resource.error(getCustomErrorMessage(t), newData)));
//            }
//        });
//    }
//
//    private String getCustomErrorMessage(Throwable error) {
//        if (error instanceof SocketTimeoutException) {
//            return App.app.getApplicationContext().getString(R.string.requestTimeOutError);
//        } else if (error instanceof MalformedJsonException) {
//            return App.app.getApplicationContext().getString(R.string.responseMalformedJson);
//        } else if (error instanceof IOException) {
//            return App.app.getApplicationContext().getString(R.string.networkError);
//        } else if (error instanceof HttpException) {
//            return ((HttpException) error).response().message();
//        } else {
//            return App.app.getApplicationContext().getString(R.string.unknownError);
//        }
//    }
//
//    @MainThread
//    private void saveResultAndReInit(RequestType response) {
//        appExecutors.diskIO().execute(() -> {
//            // Save to local
//            saveCallResult(response);
//            appExecutors.mainThread().execute(() -> {
//
//                // Listen to local data
//                result.addSource(loadFromDb(), resultType ->
//                        result.setValue(Resource.success(resultType)));
//            });
//        });
//    }
//
//
//    /**
//     * Cache data obtained on the network to the local
//     */
//    @WorkerThread
//    protected abstract void saveCallResult(RequestType item);
//
//    /**
//     * Do you need to get data from the network?
//     */
//    @MainThread
//    protected abstract boolean shouldFetch(ResultType data);
//
//
//    /**
//     * Get data from the database
//     */
//    @NonNull
//    @MainThread
//    protected abstract LiveData<ResultType> loadFromDb();
//
//    /**
//     * Get data from the web
//     * TODO Since there is a problem with returning liveData directly from retrofit, first use Call to mimic the conversion process in LiveDataCallAdapter
//     */
//    @NonNull
//    @MainThread
//    protected abstract Call<RequestType> createCall();


}