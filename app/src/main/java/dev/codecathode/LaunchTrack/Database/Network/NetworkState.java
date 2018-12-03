package dev.codecathode.LaunchTrack.Database.Network;

import androidx.annotation.NonNull;

public class NetworkState {


    public enum Status {
        LOADING,
        SUCCESS,
        FAILED
    }

    @NonNull
    private final Status status;

    private final String msg;

    public static final NetworkState LOADED;
    public static final NetworkState LOADING;
    public static final NetworkState FAILED;

    public NetworkState(@NonNull Status status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    static {
        LOADED = new NetworkState(Status.SUCCESS, "Success");
        LOADING = new NetworkState(Status.LOADING, "Running");
        FAILED = new NetworkState(Status.FAILED, "FAILED");
    }

    @NonNull
    public Status getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
