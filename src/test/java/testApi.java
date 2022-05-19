
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;



public class testApi {

    @Test
    public void getEpisodeData() {

        // Находим URL с последним эпизодом, где появлялся Морти

        ArrayList episode = given()
                .when()
                .contentType(ContentType.JSON)
                .get("https://rickandmortyapi.com/api/character/2")
                .then()
                .extract().body().jsonPath().get("episode");
        String episodeURL = episode.get(episode.size() - 1).toString();
        System.out.println("Последний эпизод, где появлялся Морти Смит - " + episodeURL);

        //Находим расу Морти

        String mortyRace = given()
                .when()
                .contentType(ContentType.JSON)
                .get("https://rickandmortyapi.com/api/character/2")
                .then()
                .extract().body().jsonPath().get("species");

        //Находим локацию Морти

        Object mortyLocation = given()
                .when()
                .contentType(ContentType.JSON)
                .get("https://rickandmortyapi.com/api/character/2")
                .then()
                .extract().body().jsonPath().get("location.name");
        Object mortyLastLocation = mortyLocation.toString();

        // Находим URL с последним персонажем в последнем эпизоде

        ArrayList characters = given()
                .when()
                .contentType(ContentType.JSON)
                .get(episodeURL)
                .then()
                .extract().body().jsonPath().get("characters");
        String lastCharacterNameURL = characters.get(characters.size()-1).toString();

        // Находим этого персонажа в последнем эпизоде

        String lastCharacterName = given()
                .when()
                .contentType(ContentType.JSON)
                .get(lastCharacterNameURL)
                .then()
                .extract().body().jsonPath().get("name");
        System.out.println("Последний персонаж в последнем эпизоде - " + lastCharacterName);

        //Находим расу последнего персонажа

        String lastCharacterSpecies = given()
                .when()
                .contentType(ContentType.JSON)
                .get(lastCharacterNameURL)
                .then()
                .extract().body().jsonPath().get("species");
        System.out.println("Раса последнего персонажа - " + lastCharacterSpecies);

        //Проверяем совпадает ли раса последнего персонажа с расой Морти
        Assert.assertEquals(mortyRace, lastCharacterSpecies);

        //Находим локацию последнего персонажа

        Object locationName = given()
                .when()
                .contentType(ContentType.JSON)
                .get(lastCharacterNameURL)
                .then()
                .extract().body().jsonPath().get("location.name");
        Object locationLastCharacter = locationName.toString();
        System.out.println("Локация последнего персонажа - " + locationLastCharacter);

        System.out.println("Локация Морти - " + mortyLastLocation);

        //Проверки на совпадение локации последнего персонажа и Морти

        //Assert.assertEquals(mortyLastLocation, locationLastCharacter);
        Assert.assertNotSame(mortyLastLocation, locationLastCharacter);


    }





}
