package dev.codecathode.LaunchTrack.Application;

import android.app.Application;
import android.content.Context;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.jaredrummler.cyanea.Cyanea;
import com.squareup.leakcanary.LeakCanary;

import androidx.room.Room;
import dev.codecathode.LaunchTrack.Database.Local.AppDatabase;

public class App extends Application {

    public static App app;


    private static AppDatabase appDatabase;
    private static final String DATABASE_NAME = "LaunchDatabase.db";
    private static volatile AppDatabase instance;
    private static final Object LOCK = new Object();


    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
//        LeakCanary.install(this);

        AndroidThreeTen.init(this);

        Cyanea.init(this, getResources());

        app = this;

    }

    public static AppDatabase appDatabase() {
        if (appDatabase == null) {
            appDatabase = getInstance(app.getApplicationContext());
        }
        return appDatabase;
    }


    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext()
                             , AppDatabase.class
                             , DATABASE_NAME)
                             .fallbackToDestructiveMigration()
                             .build();
                }
            }
        }
        return instance;
    }
    public static AppExecutors getAppExecutors() {
        return AppExecutors.getInstance();
    }



}
