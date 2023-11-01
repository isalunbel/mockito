package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;
import sun.applet.Main;

class LocalizationServiceImplTest extends Main {

    @Test
    void testLocale() {

        Country country = Country.RUSSIA;
        LocalizationServiceImpl localizationService = Mockito.spy(new LocalizationServiceImpl());

        Mockito.doReturn("Привет").when(localizationService).getMessage(country);


        String result = localizationService.locale(country);


        Assertions.assertEquals("Привет", result);
    }
}
