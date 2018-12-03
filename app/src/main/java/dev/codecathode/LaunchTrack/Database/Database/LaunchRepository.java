package dev.codecathode.launchlist.Database;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;

public class LaunchRepository {

    private static LaunchRepository launchRepositoryInstance;

    private Executor executor = Executors.newSingleThreadExecutor();

}
