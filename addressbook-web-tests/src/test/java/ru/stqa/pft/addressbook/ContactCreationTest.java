package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
      ContactData contact = new ContactData("FistName", "qwe@mail.ru", "LastName", "qwer, asdf 4, 123", "123345234");

      goToNewContact();
      fillContactForm(contact);
      submitContactCreation();
      goToHomePage();
  }

}
