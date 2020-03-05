package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
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
  public void testContactDeletion(){

    app.goTo().homePage();
    //Создаем множество контактов изначально
    Contacts before = app.contact().all();
    //Получаем рандомно контак для удаления
    ContactData contactDeletion = before.iterator().next();
    app.contact().contactDeletionById(contactDeletion.getId());
    app.alertAccept();
    app.goTo().homePage();
    //Создаем множество контактов после удаления
    Contacts after = app.contact().all();
    before.remove(contactDeletion);
    assertEquals(after.size(), before.size());
    //Сравнение множеств v2
    assertThat(after, equalTo(before.withOut(contactDeletion)));
  }
}
