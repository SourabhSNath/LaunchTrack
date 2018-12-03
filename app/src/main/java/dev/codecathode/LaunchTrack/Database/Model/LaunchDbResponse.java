package dev.codecathode.LaunchTrack.Database.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LaunchDbResponse {

    @SerializedName("launches")
    @Expose
    private List<Launch> launches = null;
    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("offset")
    @Expose
    private int offset;
    @SerializedName("count")
    @Expose
    private int count;

    public List<Launch> getLaunches() {
        return launches;
    }

    public void setLaunches(List<Launch> launches) {
        this.launches = launches;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public int getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}