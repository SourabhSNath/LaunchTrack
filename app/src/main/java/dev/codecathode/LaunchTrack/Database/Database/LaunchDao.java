package dev.codecathode.launchlist.Database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import dev.codecathode.launchlist.Model.Launch;

import static androidx.room.OnConflictStrategy.REPLACE;

public interface LaunchDao {

    @Insert(onConflict = REPLACE)
    void save (List<Launch> launches);

    @Query("SELECT * FROM launch")
    LiveData<List<Launch>> getAll();

    @Query("DELETE from launch")
    int deleteAll();
}
