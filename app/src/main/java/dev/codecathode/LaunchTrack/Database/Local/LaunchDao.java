package dev.codecathode.LaunchTrack.Database.Local;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import dev.codecathode.LaunchTrack.Database.Model.Launch;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface LaunchDao {

    @Insert(onConflict = REPLACE)
    void saveAll(List<Launch> launches);

    @Query("SELECT * FROM launches WHERE tbddate = 0 Order BY isostart")
    LiveData<List<Launch>> getAll();

    @Query("SELECT * FROM launches Order BY isostart")
    DataSource.Factory<Integer, Launch> getAllLaunches();


    @Query("SELECT * FROM launches WHERE tbddate = 0 Order BY isostart")
    DataSource.Factory<Integer, Launch> getConfirmedDateLaunches();

    @Query("SELECT * FROM launches WHERE tbdtime = 0 Order BY isostart")
    DataSource.Factory<Integer, Launch> getConfirmedLaunces();

    @Query("DELETE FROM launches")
    int deleteAll();


//    @Query("SELECT * FROM launches")
//    Launch getNoteByID(int id);
}
