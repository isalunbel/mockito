package test;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import sun.applet.Main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GeoServiceTest extends Main {

    @Mock
    private GeoService geoService;

    @Test
    public void testByIp() {
        MockitoAnnotations.initMocks(this);

        String ip = "172.123.12.19";
        Location expectedLocation = new Location("Moscow", Country.RUSSIA, "Leninsky Prospekt", 15);

        when(geoService.byIp(ip)).thenReturn(expectedLocation);

        Location actualLocation = geoService.byIp(ip);

        assertEquals(expectedLocation, actualLocation);
    }
}
