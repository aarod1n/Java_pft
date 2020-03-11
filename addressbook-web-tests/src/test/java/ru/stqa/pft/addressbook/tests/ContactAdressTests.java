package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAdressTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    //Проверяем наличие контакта
    if (!app.contact().isThereAContact()) {
      app.goTo().newContact();
      app.contact()
              .creation(new ContactData()
                              .withFirstName("FistName").withEMail("qwe@mail.ru")
                              .withLastName("LastName").withAddress("qwer, asdf 4, 123").withMobilePhone("123345234"),
                      true);
    }
  }

  @Test
  public void ContactAdressTest(){
    app.goTo().homePage();
    ContactData contactFromTable = app.contact().all().iterator().next();
    ContactData contactFromEditForm = app.contact().getInfoForEditForm(contactFromTable.getId());
    assertThat(contactFromTable.getAddress(), equalTo(contactFromEditForm.getAddress()));
  }
}
