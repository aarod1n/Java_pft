package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    //Проверяем наличие контакта
    if (!app.contact().isThereAContact()) {
      app.goTo().newContact();
      app.contact()
              .creation(new ContactData()
                              .withGroup("test1").withFirstName("FistName").withEMail("qwe@mail.ru")
                              .withLastName("LastName").withAddress("qwer, asdf 4, 123").withMobile("123345234"),
                      true);
    }
  }

  @Test
  public void testContactModification() {

    app.goTo().homePage();
    //Получили список контактов
    Contacts before = app.contact().all();
    ContactData contactModification = before.iterator().next();

    ContactData contact = new ContactData()
            .withId(contactModification.getId()).withGroup("test1").withFirstName("FistNameEdit")
            .withEMail("qwe@mail.ru").withLastName("LastNameEdit").withAddress("qwer, asdf 4, 123").withMobile("123345234");

    app.goTo().homePage();
    app.contact().contactModificationById(contact, contact.getId(), false);
    app.goTo().homePage();
    //Получили множество контактов после модификации
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withOut(contactModification).withAddet(contact)));
  }
}
