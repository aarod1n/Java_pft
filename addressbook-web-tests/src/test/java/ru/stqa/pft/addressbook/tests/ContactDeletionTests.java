package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion(){
    app.goTo().goToHomePage();

    //Создаем список контактов изначально
    List<ContactData> before = app.getContactHelper().getContactList();

    //Проверяем наличие контакта
    if (!app.getContactHelper().isThereAContact()) {
      app.goTo().goToNewContact();
      app.getContactHelper()
              .contactCreation(new ContactData("test1", "FistName", "qwe@mail.ru", "LastName", "qwer, asdf 4, 123", "123345234"), true);
    }

    app.goTo().goToHomePage();
    app.getContactHelper().contactDeletion(before.size()-1);
    app.alertAccept();
    app.goTo().goToHomePage();

    //Создаем список контактов после удаления
    List<ContactData> after = app.getContactHelper().getContactList();

    //Создаем переменную компаратор, которая умеет сравнивать объекты нашего класса
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());

    before.remove(before.size()-1);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}
