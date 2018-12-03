package dev.codecathode.LaunchTrack.Database.Local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import dev.codecathode.LaunchTrack.Database.Model.Launch;

@Database(entities = {Launch.class}, version = 1, exportSchema = false)
@TypeConverters({ConvertersRoom.class})
public abstract class AppDatabase extends RoomDatabase {


    public abstract LaunchDao launchDao();


}
