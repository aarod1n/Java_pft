package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectContact(1);
    app.getContactHelper().deleteSelectContact();
    app.alertAccept();
    app.getNavigationHelper().goToHomePage();
  }
}
