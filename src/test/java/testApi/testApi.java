package testApi;

import io.qameta.allure.Step;
import io.qameta.allure.attachment.http.HttpResponseAttachment;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;

import static io.restassured.RestAssured.*;


public class testApi {




    private final String baseURL = "https://rickandmortyapi.com/api/";

    public String characterId;
    public String lastEpisodeUrl;
    public String mortyRace;
    public String mortyLocation;
    public String lastCharacterNameUrl;
    public String lastCharacterName;
    public String lastCharacterRace;
    public String lastCharacterLocation;

    @Step("Находим id персонажа")

    public void getCharacter(String id) {
        Response getCharacter = given()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .baseUri(baseURL)
                .when()
                .get("/character/" + id)
                .then()
                .extract()
                .response();
        characterId = new JSONObject(getCharacter.getBody().asString()).get("id").toString();
    }

    @Step("Находим URL с последним эпизодом, где появлялся Морти")

    public void getEpisodeUrl() {

        ArrayList episode = given()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .baseUri(baseURL)
                .when()
                .contentType(ContentType.JSON)
                .get("/character/" + characterId)
                .then()
                .extract().body().jsonPath().get("episode");
        lastEpisodeUrl = episode.get(episode.size() - 1).toString();
        System.out.println("Последний эпизод, где появлялся Морти Смит - " + lastEpisodeUrl);
    }

    @Step("Находим расу Морти")

    public void getMortyRace() {

        String getRace = given()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .baseUri(baseURL)
                .when()
                .contentType(ContentType.JSON)
                .get("/character/" + characterId)
                .then()
                .extract().body().jsonPath().get("species").toString();
        this.mortyRace = getRace;
        System.out.println("Раса Морти - " + this.mortyRace);
    }

    @Step("Находим локацию Морти")

    public void getMortyLocation() {
        String getMortyLoc = given()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .baseUri(baseURL)
                .when()
                .contentType(ContentType.JSON)
                .get("/character/" + characterId)
                .then()
                .extract().body().jsonPath().get("location.name").toString();
        this.mortyLocation = getMortyLoc;
        System.out.println("Локация Морти - " + this.mortyLocation);

    }

    @Step("Находим URL с последним персонажем в последнем эпизоде")

    public void getLastCharUrl() {
        ArrayList characters = given()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .when()
                .contentType(ContentType.JSON)
                .get(lastEpisodeUrl)
                .then()
                .extract().body().jsonPath().get("characters");
        lastCharacterNameUrl = characters.get(characters.size() - 1).toString();

    }

    @Step("Находим этого персонажа в последнем эпизоде")

    public void getLastCharacter() {

        String getLastCharacterName = given()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .when()
                .contentType(ContentType.JSON)
                .get(lastCharacterNameUrl)
                .then()
                .extract().body().jsonPath().get("name").toString();
        this.lastCharacterName = getLastCharacterName;

        System.out.println("Последний персонаж в последнем эпизоде - " + this.lastCharacterName);

    }

    @Step("Находим расу последнего персонажа")

    public void getLastCharacterRace() {

        String getLastCharRace = given()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .when()
                .contentType(ContentType.JSON)
                .get(lastCharacterNameUrl)
                .then()
                .extract().body().jsonPath().get("species").toString();
        this.lastCharacterRace = getLastCharRace;

        System.out.println("Раса последнего персонажа "+ lastCharacterName + " - "+ this.lastCharacterRace);

    }

    @Step("Находим локацию последнего персонажа")

    public void getLastCharacterLocation() {

        Object getLastCharLocation = given()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .when()
                .contentType(ContentType.JSON)
                .get(lastCharacterNameUrl)
                .then()
                .extract().body().jsonPath().get("location.name").toString();
        this.lastCharacterLocation = getLastCharLocation.toString();

        System.out.println("Локация последнего персонажа "+ lastCharacterName + " - "+ this.lastCharacterLocation);

    }

    @Step("Проверка совпадает ли раса последнего персонажа с расой Морти")

    public void raceAssert() {

        Assertions.assertEquals(mortyRace, lastCharacterRace, "не совпадает");
    }

    @Step("Проверка на совпадение локации последнего персонажа и Морти")

    public void locationAssert() {

        Assertions.assertEquals(mortyLocation, lastCharacterLocation, "не совпадает");
    }

  }


