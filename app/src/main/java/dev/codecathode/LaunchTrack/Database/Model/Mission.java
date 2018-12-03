package dev.codecathode.LaunchTrack.Database.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Mission {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("agencies")
    @Expose
    private Object agencies;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("typeName")
    @Expose
    private String typeName;
    @SerializedName("launch")
    @Expose
    private Launch launch;
    @SerializedName("infoURL")
    @Expose
    private String infoURL;
    @SerializedName("wikiURL")
    @Expose
    private String wikiURL;
    @SerializedName("events")
    @Expose
    private Object events;
    @SerializedName("infoURLs")
    @Expose
    private List<Object> infoURLs = null;
    @SerializedName("changed")
    @Expose
    private String changed;
    @SerializedName("payloads")
    @Expose
    private List<Payload> payloads = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getAgencies() {
        return agencies;
    }

    public void setAgencies(Object agencies) {
        this.agencies = agencies;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Launch getLaunch() {
        return launch;
    }

    public void setLaunch(Launch launch) {
        this.launch = launch;
    }

    public String getInfoURL() {
        return infoURL;
    }

    public void setInfoURL(String infoURL) {
        this.infoURL = infoURL;
    }

    public String getWikiURL() {
        return wikiURL;
    }

    public void setWikiURL(String wikiURL) {
        this.wikiURL = wikiURL;
    }

    public Object getEvents() {
        return events;
    }

    public void setEvents(Object events) {
        this.events = events;
    }

    public List<Object> getInfoURLs() {
        return infoURLs;
    }

    public void setInfoURLs(List<Object> infoURLs) {
        this.infoURLs = infoURLs;
    }

    public String getChanged() {
        return changed;
    }

    public void setChanged(String changed) {
        this.changed = changed;
    }

    public List<Payload> getPayloads() {
        return payloads;
    }

    public void setPayloads(List<Payload> payloads) {
        this.payloads = payloads;
    }

}