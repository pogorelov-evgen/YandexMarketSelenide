package ru.pogorelov.helpers;

import org.aeonbits.owner.Config;

/**
 * @author  Погорелов Денис
 * Класс отвечающий установление зависимостей переменных
 * */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
        "system:env",
        "file:src/main/resources/tests.properties"
})
public interface TestsProperties extends Config{

    /**
     * Устанавливаем переменную для адреса Яндекс Маркета
     */
    @Key("yandexMarket.url")
    String yandexMarket();

    /**
     * Устанавливаем переменную для веб драйвера
     */
    @Key("driver")
    String driver();

    /**
     * Устанавливаем переменную для хром драйвера
     */
    @Key("CHROME_DRIVER")
    String chromeDriver();

}
