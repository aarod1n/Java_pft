package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    ContactData contact = new ContactData(null, "FistNameEdit", "qweEdit@mail.ru", "LastNameEdit", "qwerEdit, asdfEdit 4, 123", "123345234Edit");
    app.getNavigationHelper().goToHomePage();

    //Проверяем наличие контакта
    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().goToNewContact();
      app.getContactHelper()
              .contactCreation(new ContactData("test1", "FistName", "qwe@mail.ru", "LastName", "qwer, asdf 4, 123", "123345234"), true);
    }

    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().contactModification(contact,0, false);
    app.getNavigationHelper().goToHomePage();
  }
}
