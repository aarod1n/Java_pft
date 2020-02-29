package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().goToHomePage();

    //Проверяем наличие контакта
    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().goToNewContact();
      app.getContactHelper()
              .contactCreation(new ContactData("test1", "FistName", "qwe@mail.ru", "LastName", "qwer, asdf 4, 123", "123345234"), true);
    }

    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().contactDeletion(1);
    app.alertAccept();
    app.getNavigationHelper().goToHomePage();
  }
}
