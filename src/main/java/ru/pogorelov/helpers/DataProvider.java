package ru.pogorelov.helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author  Погорелов Денис
 * Класс отвечающий за задание аргументов для тестиорования
 * */
public class DataProvider {

    /**
    * Метод для задания аргумнтов для тестирования
     * titleMarket переменной задаем название странице с которой сравниваем
     * charter переменная для выбора раздела общего
     * product переменная для выбора искомого подраздела
     * manufacturersList задаем список производителей
    */
    public static Stream<Arguments> provider(){
        String titleMarket = "Яндекс Маркет";
        String chapter = "Электроника";
        String product = "Смартфоны";
        List<String> manufacturersList = new ArrayList<String>();
        manufacturersList.add("Apple");
        return Stream.of(
                Arguments.of(titleMarket, chapter, product, manufacturersList)
        );
    }
}
