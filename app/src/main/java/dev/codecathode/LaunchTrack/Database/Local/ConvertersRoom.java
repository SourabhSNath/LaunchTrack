package dev.codecathode.LaunchTrack.Database.Local;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.room.TypeConverter;
import dev.codecathode.LaunchTrack.Database.Model.Location;
import dev.codecathode.LaunchTrack.Database.Model.Lsp;
import dev.codecathode.LaunchTrack.Database.Model.Mission;
import dev.codecathode.LaunchTrack.Database.Model.Rocket;

public class ConvertersRoom {

    @TypeConverter
    public static Date fromDate(Long timeStamp) {
        return timeStamp == null ? null : new Date(timeStamp);
    }

    @TypeConverter
    public static Long toTimeStamp(Date date) {
        return date == null ? null : date.getTime();
    }

////////////////////////////////////////////////////////////////////////////////////////////

    @TypeConverter
    public static List<Mission> fromMission(String value) {

        Type listType = new TypeToken<ArrayList<Mission>>() {
        }.getType();

        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String toMission(List<Mission> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    @TypeConverter
    public static Location fromLocation(String value) {
        Type type = new TypeToken<Location>() {
        }.getType();
        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    public static String toLocation(Location type) {
        return new Gson().toJson(type);
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    @TypeConverter
    public static Rocket fromRocket(String value) {
        Type type = new TypeToken<Rocket>() {
        }.getType();

        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    public static String toRocket(Rocket value) {
        return new Gson().toJson(value);
    }


    ////////////////////////////////////////////////////////////////////////////////////////

    @TypeConverter
    public static Object fromObject(String value) {
        Type type = new TypeToken<Object>() {
        }.getType();
        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    public static String toObject(Object type) {
        return new Gson().toJson(type);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    @TypeConverter
    public static List<Object> fromObjects(String value) {

        Type listType = new TypeToken<List<String>>() {
        }.getType();

        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String toObjects(List<Object> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    @TypeConverter
    public static List<String> fromString(String value) {

        Type listType = new TypeToken<List<String>>() {
        }.getType();

        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String toString(List<String> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    @TypeConverter
    public static Lsp fromLsp(String value) {
        Type type = new TypeToken<Lsp>() {
        }.getType();
        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    public static String toLsp(Lsp type) {
        return new Gson().toJson(type);
    }

}
