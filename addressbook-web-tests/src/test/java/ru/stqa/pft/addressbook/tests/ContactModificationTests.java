package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    ContactData contact = new ContactData(null,"FistNameEdit", "qweEdit@mail.ru", "LastNameEdit", "qwerEdit, asdfEdit 4, 123", "123345234Edit");

    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectContactForEdit(1);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactUpdate();
    app.getNavigationHelper().goToHomePage();
  }
}
