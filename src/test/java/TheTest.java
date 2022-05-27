import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testApi.testApi;
public class TheTest extends testApi {

@DisplayName("Api tests Rick&Morty")
    @Test
    public void characterTest() {
        getCharacter("2");
        getEpisodeUrl();
        getMortyRace();
        getMortyLocation();
        getLastCharUrl();
        getLastCharacter();
        getLastCharacterRace();
        getLastCharacterLocation();
        raceAssert();
        locationAssert();

    }

}
