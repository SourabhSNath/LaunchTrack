package dev.codecathode.LaunchTrack.Database.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Launches")
public class Launch {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("windowstart")
    @Expose
    private String windowstart;
    @SerializedName("windowend")
    @Expose
    private String windowend;
    @SerializedName("net")
    @Expose
    private String net;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("inhold")
    @Expose
    private int inhold;
    @SerializedName("tbdtime")
    @Expose
    private int tbdtime;
    @SerializedName("vidURLs")
    @Expose
    private List<String> vidURLs = null;
    @SerializedName("vidURL")
    @Expose
    private Object vidURL;
    @SerializedName("infoURLs")
    @Expose
    private List<Object> infoURLs = null;
    @SerializedName("infoURL")
    @Expose
    private Object infoURL;
    @SerializedName("tbddate")
    @Expose
    private int tbddate;
    @SerializedName("probability")
    @Expose
    private int probability;

    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("rocket")
    @Expose
    private Rocket rocket;
    @SerializedName("missions")
    @Expose
    private List<Mission> missions = new ArrayList<>();

    @SerializedName("lsp")
    @Expose
    private Lsp lsp;

    @SerializedName("isostart")
    @Expose
    private Date isostart;

    @Ignore
    @SerializedName("isoend")
    @Expose
    private String isoend;

    @Ignore
    @SerializedName("isonet")
    @Expose
    private String isonet;


//    @SerializedName("holdreason")
//    @Expose
//    private Object holdreason;
//    @SerializedName("failreason")
//    @Expose
//    private Object failreason;
//
//    @SerializedName("hashtag")
//    @Expose
//    private Object hashtag;


    public Date getIsostart() {
        return isostart;
    }

    public void setIsostart(Date isostart) {
        this.isostart = isostart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWindowstart() {
        return windowstart;
    }

    public void setWindowstart(String windowstart) {
        this.windowstart = windowstart;
    }

    public String getWindowend() {
        return windowend;
    }

    public void setWindowend(String windowend) {
        this.windowend = windowend;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }


    public String getIsoend() {
        return isoend;
    }

    public void setIsoend(String isoend) {
        this.isoend = isoend;
    }

    public String getIsonet() {
        return isonet;
    }

    public void setIsonet(String isonet) {
        this.isonet = isonet;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getInhold() {
        return inhold;
    }

    public void setInhold(int inhold) {
        this.inhold = inhold;
    }

    public int getTbdtime() {
        return tbdtime;
    }

    public void setTbdtime(int tbdtime) {
        this.tbdtime = tbdtime;
    }

    public List<String> getVidURLs() {
        return vidURLs;
    }

    public void setVidURLs(List<String> vidURLs) {
        this.vidURLs = vidURLs;
    }

    public Object getVidURL() {
        return vidURL;
    }

    public void setVidURL(Object vidURL) {
        this.vidURL = vidURL;
    }

    public List<Object> getInfoURLs() {
        return infoURLs;
    }

    public void setInfoURLs(List<Object> infoURLs) {
        this.infoURLs = infoURLs;
    }

    public Object getInfoURL() {
        return infoURL;
    }

    public void setInfoURL(Object infoURL) {
        this.infoURL = infoURL;
    }


    public int getTbddate() {
        return tbddate;
    }

    public void setTbddate(int tbddate) {
        this.tbddate = tbddate;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    public Lsp getLsp() {
        return lsp;
    }

    public void setLsp(Lsp lsp) {
        this.lsp = lsp;
    }

    @Ignore
    public Launch() {
    }

    public Launch(int id, String name, String windowstart, String windowend, String net, int status, int inhold, int tbdtime, List<String> vidURLs, Object vidURL, List<Object> infoURLs, Object infoURL, int tbddate, int probability, Location location, Rocket rocket, List<Mission> missions, Date isostart, Lsp lsp) {
        this.id = id;
        this.name = name;
        this.windowstart = windowstart;
        this.windowend = windowend;
        this.net = net;

        this.status = status;
        this.inhold = inhold;
        this.tbdtime = tbdtime;
        this.vidURLs = vidURLs;
        this.vidURL = vidURL;
        this.infoURLs = infoURLs;
        this.infoURL = infoURL;

        this.tbddate = tbddate;
        this.probability = probability;

        this.location = location;
        this.rocket = rocket;
        this.missions = missions;
        this.isostart = isostart;

        this.lsp = lsp;
    }

    public static final DiffUtil.ItemCallback<Launch> DIFF_CALLBACK = new DiffUtil.ItemCallback<Launch>() {
        @Override
        public boolean areItemsTheSame(@NonNull Launch oldItem, @NonNull Launch newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Launch oldItem, @NonNull Launch newItem) {
            return oldItem.equals(newItem);
        }
    };

}