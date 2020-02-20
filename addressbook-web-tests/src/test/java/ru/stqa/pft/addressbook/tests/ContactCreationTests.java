package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase
{

  @Test
  public void testContactCreation()
  {
      ContactData contact = new ContactData("FistName", "qwe@mail.ru", "LastName", "qwer, asdf 4, 123", "123345234");

      app.getNavigationHelper().goToNewContact();
      app.getContactHelper().fillContactForm(contact);
      app.getContactHelper().submitContactCreation();
      app.getNavigationHelper().goToHomePage();
  }

}
