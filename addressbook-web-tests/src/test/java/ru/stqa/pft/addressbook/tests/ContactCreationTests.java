package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    app.goTo().goToHomePage();
    //Создаем список контактов изначально
    List<ContactData> before = app.getContactHelper().getContactList();

    ContactData contact = new ContactData()
            .withGroup("test1").withFirstName("FistName").withEMail("qwe@mail.ru")
            .withLastName("LastName").withAddress("qwer, asdf 4, 123").withMobile("123345234");
    app.goTo().goToNewContact();

    app.getContactHelper().contactCreation(contact, true);
    app.goTo().goToHomePage();

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
