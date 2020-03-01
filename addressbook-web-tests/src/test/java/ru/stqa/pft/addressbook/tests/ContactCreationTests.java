package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    app.getNavigationHelper().goToHomePage();
    //Создаем список контактов изначально
    List<ContactData> before = app.getContactHelper().getContactList();

    ContactData contact = new ContactData("test1", "FistName", "qwe@mail.ru", "LastName", "qwer, asdf 4, 123", "123345234");
    app.getNavigationHelper().goToNewContact();

    app.getContactHelper().contactCreation(contact, true);
    app.getNavigationHelper().goToHomePage();

    //Создаем список контактов после добавления
    List<ContactData> after = app.getContactHelper().getContactList();

    //Создаем переменную компаратор, которая умеет сравнивать объекты нашего класса
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());

    before.add(contact);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
