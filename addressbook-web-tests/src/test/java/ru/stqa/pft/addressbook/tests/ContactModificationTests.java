package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if(app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.goTo().newContact();
      app.contact().creation(new ContactData().withFirstName("FistName")
                      .withEMail("qwe@mail.ru").withLastName("LastName")
                      .withAddress("qwer, asdf 4, 123").withMobilePhone("123345234"),true);
    }
  }

  @Test
  public void testContactModification() {

    app.goTo().homePage();
    //Получили список контактов
    Contacts before = app.db().contacts();
    ContactData contactModification = before.iterator().next();

    ContactData contact = new ContactData()
            .withId(contactModification.getId()).withFirstName("FistNameEdit")
            .withEMail("qwe@mail.ru").withLastName("LastNameEdit").withAddress("qwer, asdf 4, 123").withMobilePhone("123345234");

    app.goTo().homePage();
    app.contact().contactModificationById(contact, contact.getId(), false);
    app.goTo().homePage();
    //Быстрая проверка
    assertThat(app.contact().count(), equalTo(before.size()));
    //Получили множество контактов после модификации
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withOut(contactModification).withAddet(contact)));
    verifyContactsListInUI();
  }
}
