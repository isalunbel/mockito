package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.netology.entity.Country;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;
import sun.applet.Main;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

class MessageSenderTest extends Main {
    @Mock
    private GeoService geoService;

    @Mock
    private LocalizationService localizationService;

    @InjectMocks
    private MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSend_RussianIP_SendsRussianMessage() {

        when(geoService.byIp("172.123.12.19"))
                .thenReturn(new GeoServiceImpl("Moscow", Country.RUSSIA, "Lenina", 15));

        when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать!");


        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
        messageSender.send(headers);

        
        verify(messageSender).send("Добро пожаловать!");
    }

    @Test
    void testSend_USIP_SendsEnglishMessage() {

        when(geoService.byIp("192.168.0.1"))
                .thenReturn(new GeoServiceImpl("New York", Country.USA, "Broadway", 10));


        when(localizationService.locale(Country.USA))
                .thenReturn("Welcome!");


        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "192.168.0.1");
        messageSender.send(headers);


        verify(messageSender).send("Welcome!");
    }
}
