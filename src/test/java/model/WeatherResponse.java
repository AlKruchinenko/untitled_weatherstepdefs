package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    private Coord coord;
    private String base;
    private Sys sys;
    private Clouds clouds;
    private String id;
    private String name;
    private String cod;
    private String dt;

    private String visibility;
    private Wind wind;
    @JsonProperty("main")
    private Main main;


    @JsonProperty("weather")
    private List<Weather> weathers;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }
}