package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {

    app.getNavigationHelper().goToHomePage();
    //Проверяем наличие контакта
    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().goToNewContact();
      app.getContactHelper()
              .contactCreation(new ContactData("test1", "FistName", "qwe@mail.ru", "LastName", "qwer, asdf 4, 123", "123345234"), true);
    }
    //Получили список контактов
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData(before.get(0).getId(),null, "FistNameEdit", "qweEdit@mail.ru", "LastNameEdit", "qwerEdit, asdfEdit 4, 123", "123345234Edit");

    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().contactModification(contact, 0, false);
    app.getNavigationHelper().goToHomePage();

    //Получили список контактов после модификации
    List<ContactData> after = app.getContactHelper().getContactList();
    before.remove(0);
    before.add(contact);


    //Создаем переменную компаратор, которая умеет сравнивать объекты нашего класса
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());

    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
