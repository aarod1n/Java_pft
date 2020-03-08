package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    app.goTo().homePage();
    //Создаем множество контактов изначально
    Contacts before = app.contact().all();
    File photo = new File("src/test/res/1.jpg");

    ContactData contact = new ContactData()
            .withGroup("test1").withFirstName("FistName").withEMail("qwe@mail.ru")
            .withLastName("LastName").withAddress("qwer, asdf 4, 123").withMobilePhone("123345234").withPhoto(photo);

    app.goTo().newContact();
    app.contact().creation(contact, true);
    app.goTo().homePage();
    //Быстрая проверка
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    //Создаем множество контактов после добавления
    Contacts after = app.contact().all();
    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    assertThat(after, equalTo(before.withAddet(contact)));
  }
}
