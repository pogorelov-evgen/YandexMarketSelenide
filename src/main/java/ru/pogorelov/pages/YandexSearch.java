package ru.pogorelov.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.pogorelov.helpers.Assertions;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


/**
 * @author  Погорелов Денис
 * Класс отвечающий за поисковую страницу Яндекс Маркета
 * */
public class YandexSearch {
    /**
     * Проверка соответсвия страницы
     * @param name название с которым сравниваем найденную страницу
     */
    @Step("Страница соотвествует заданным условиям: {name}")
    public YandexSearch validateChapter(String name) {
        Assertions.assertTrue(title().contains(name),"Название страницы не содержит "+name);
        return this;
    }
    /**
     * Выбор производителей
     * @param manufacturersList названия производителей которые выбираем
     * */
    @Step("Выбор производителя")
    public YandexSearch selectManufacture(List<String> manufacturersList) {
        for (String manufacturer :manufacturersList){
            $x("//span[text()='"+manufacturer+"']/../span/span").click();
        }
        return this;
    }
    /**
     * Проверка выдаваемых значений условиям поиска по всем страницам
     * */
    @Step("Проверка выдаваемых значений условиям поиска по всем страницам")
    public YandexSearch checkingFilterProduct(List<String> manufacturersList) {
        ArrayList<String> titlesSearchList = (ArrayList<String>) addProductAllPage();
        System.out.println("Count Iphones"+titlesSearchList.size());
        for(String title:titlesSearchList){
            boolean productIsPresent = false;
            for (String manufacturer:manufacturersList){
                if (title.contains(manufacturer)) productIsPresent = true;
            }
            if (!productIsPresent) org.junit.jupiter.api.Assertions.fail(
                    "Найденные товары не соотвествуют поисковым требованиям. Ошибка на товаре "+title);
        }
        Assertions.assertTrue(true,"Проверка выданных названий на всех страницах заданным условиям");
        return this;
    }
    /**
     * Cохранение всех выдаваемых результатов на всех страницах
     * */
    @Step("Сохранение всех продуктов на всех страницах")
    private List<String> addProductAllPage() {
        ElementsCollection allPages = null;

        $x("//div[@data-auto='SerpStatic-loader']//div[contains(@class,'position_center')]//span[@role='progressbar']").should(hidden);

        SelenideElement buttonShowMore = $x("//button[@data-auto = 'pager-more']/span");
        for(int i = 2; i<50;i++) {
            if (!$x("//div[@data-current-page]/div").isDisplayed()) {
                allPages = $$x("//div[@data-auto-themename='listDetailed']//h3[@data-auto='snippet-title']");
                break;
            } else {
                buttonShowMore.scrollTo();
                if (buttonShowMore.isDisplayed()) {
                    buttonShowMore.click();
                }
                $x("//div[@data-current-page]//span[@data-auto='spinner']").should(visible);
                $x("//div[@data-current-page]//span[@data-auto='spinner']").should(hidden);
            }
        }
        Assertions.assertTrue(allPages.isEmpty(),"Не удалось записать названия товаров в список");
        return allPages.texts();

    }
}
