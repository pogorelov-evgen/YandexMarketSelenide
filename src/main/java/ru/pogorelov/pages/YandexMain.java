package ru.pogorelov.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.*;

/**
 * @author  Погорелов Денис
 * Класс отвечающий за главную страницу Яндекс Маркета
 * */
public class YandexMain {
    /**
     * Переход в раздел каталог
     */
    @Step("Нажатие на кнопку каталог")
    public YandexMain openCatalog() {
        $x("//span[text()='Каталог']/..").should(enabled).click();
        return this;
    }
    /**
     * Наведение курсора на раздел электроники
     */
    @Step("Наведение курсора на раздел {chapter}")
    public YandexMain indicateChapter(String chapter) {
        $x("//li/a/span[text()='"+chapter+"']").should(enabled).hover();
        return this;
    }
    /**
     * Нажатие на раздел смартфоны
     */
    @Step("Нажатие на нужный подраздел {product}")
    public YandexSearch openСhapter(String product) {
        $x("//li//a[text()='"+product+"']").should(enabled).click();
        return page(YandexSearch.class);
    }
}
