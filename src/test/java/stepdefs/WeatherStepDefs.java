package stepdefs;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Id;
import model.Main;
import model.Weather;
import model.WeatherResponse;
import org.hamcrest.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import requesters.WeatherRequester;

import javax.swing.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class WeatherStepDefs {
    private long cityId;
    private WeatherResponse response;

    @Given("city ID: {long}")
    public void set_city_id(long cityId) {
        this.cityId = cityId;
    }

    @When("we are requesting weather data")
    public void request_weather() throws JsonProcessingException {
        WeatherRequester requester = new WeatherRequester();
        response = requester.getWeatherData(cityId);

    }

    @Then("coordinates are:")
    public void check_coords(Map<String, Double> coords) {
        Assertions.assertEquals(coords.get("lon"), response.getCoord().getLon(), "Incorrect Coord Lon");
        Assertions.assertEquals(coords.get("lat"), response.getCoord().getLat(), "Incorrect Coord Lat");
    }

    @Then("weathers are:")
    public void check_Weather(DataTable dataTable) {
        List<Map<String, String>> weathers = dataTable.asMaps();


        Assertions.assertEquals(weathers.size(), response.getWeathers().size(), "Wrong");

        for (int i = 0; i < weathers.size(); i++) {
            Map<String, String> expectedWeather = weathers.get(i);
            Weather actualWeathers = response.getWeathers().get(i);

            Assertions.assertEquals(Long.parseLong(expectedWeather.get("id")), actualWeathers.getId(), "");
            Assertions.assertEquals(expectedWeather.get("main"), actualWeathers.getMain(), "");
            Assertions.assertEquals(expectedWeather.get("description"), actualWeathers.getDescription(), "");
            Assertions.assertEquals(expectedWeather.get("icon"), actualWeathers.getIcon(), "");
        }
    }

    @Then("base is {string}")
    public void baseIs(String base) {
        Assertions.assertEquals(base, response.getBase(), "Wrong base");
    }

    @Then("visibility are:")
    public void visibility_are(Map<String, String> visibility) {
        Assertions.assertEquals(visibility.get("visibility"), response.getVisibility(), "wrong visibility ");
    }

    @Then("wind are:")
    public void check_wind(Map<String, String> wind) {
        Assertions.assertEquals(Double.parseDouble(wind.get("speed")), response.getWind().getSpeed(), "Wrong speed");
        Assertions.assertEquals(Integer.parseInt(wind.get("deg")), response.getWind().getDeg(), "Wrong deg");
    }


    @Then("main are:")
    public void check_main(Map<String, Double> main) {
        Assertions.assertEquals(main.get("temp"), response.getMain().getTemp(), "Incorrect main temp");
        Assertions.assertEquals(main.get("pressure"), response.getMain().getPressure(), "Incorrect main pressure");
        Assertions.assertEquals(main.get("humidity"), response.getMain().getHumidity(), "Incorrect main humidity");
        Assertions.assertEquals(main.get("temp_min"), response.getMain().getTemp_min(), "Incorrect main temp_min");
        Assertions.assertEquals(main.get("temp_max"), response.getMain().getTemp_max(), "Incorrect main temp_max");
    }

    @Then("id are:")
    public void check_id(Map<String,String> id) {
        Assertions.assertEquals(id.get("id"), response.getId(), "Wrong id");
    }

    @Then("sis are:")
    public void check_sys(Map<String, String> params) {

        Assertions.assertEquals(Integer.parseInt(params.get("type")), response.getSys().getType(), "wrong type");
        Assertions.assertEquals(Long.parseLong(params.get("id")), response.getSys().getId(), "wrong iid");
        Assertions.assertEquals(Double.parseDouble(params.get("message")), response.getSys().getMessage(), "wrong message");
        Assertions.assertEquals(params.get("country"), response.getSys().getCountry(), "wrong country");
        Assertions.assertEquals(Long.parseLong(params.get("sunrise")), response.getSys().getSunrise(), "wrong sunrise");
        Assertions.assertEquals(Long.parseLong(params.get("sunset")), response.getSys().getSunset(), "wrong sunset");

        LocalDate date = Instant.ofEpochMilli(response.getSys().getSunrise()).atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(date);

        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(response.getSys().getSunrise()), ZoneId.systemDefault());
        System.out.println(dateTime);

        System.out.println(LocalDateTime.now().plusDays(6).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.GERMAN));
    }

    @Then("clouds are:")
    public void clouds_check(Map<String, Integer> clouds) {
        Assertions.assertEquals(clouds.get("all"), response.getClouds().getAll(), "Wrong all");
    }

    @Then("dt are:")
    public void dt_are(Map<String, String> dt) {
        Assertions.assertEquals(dt.get("dt"), response.getDt(), "wrong vy ");
    }

    @Then("name are:")
    public void name_check(Map<String, String> name) {
        Assertions.assertEquals(name.get("name"), response.getName(), "Wrong name");
    }

    @Then("cod are:")
    public void cod_check(Map<String, String> cod) {
        Assertions.assertEquals(cod.get("cod"), response.getCod(), "Wrong cod");
    }
}
