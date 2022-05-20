import org.junit.jupiter.api.Test;

public class TheTest extends testApi {


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
