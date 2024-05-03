import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.pogorelov.pages.YandexMain;
import ru.pogorelov.selenide.CustomAllureSelenide;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static ru.pogorelov.helpers.Properties.properties;

/**
 * @author  Погорелов Денис
 * Класс отвечающий за тестирование
 * */
public class YandexTestsSelenide {

    /**
     * Метод который бедут вызываться перед всеми тестовыми методами данного класса
     */
    @BeforeAll
    public static void setup(){
        SelenideLogger.addListener("AllureSelenide", new CustomAllureSelenide().screenshots(true).savePageSource(true));
    }

    /**
     * метод тестирования Яндекс маркета
     * @param title переменной задаем название странице с которой сравниваем
     * @param chapter переменная для выбора раздела общего
     * @param product переменная для выбора искомого подраздела
     * @param manufacturersList задаем список производителей
     * */
    @Feature("Проверка Яндекс Маркета")
    @DisplayName("Проверка результатов поиска ноутбуков")
    @ParameterizedTest(name="{displayName}: {arguments}")
    @MethodSource("ru.pogorelov.helpers.DataProvider#provider")
    public void testYandex(String title, String chapter, String product, List<String> manufacturersList) {
        open(properties.yandexMarket(), YandexMain.class)
                .openCatalog()
                .indicateChapter(chapter)
                .openСhapter(product)
                .validateChapter(product)
                .selectManufacture(manufacturersList)
                .checkingFilterProduct(manufacturersList);
    }

}
