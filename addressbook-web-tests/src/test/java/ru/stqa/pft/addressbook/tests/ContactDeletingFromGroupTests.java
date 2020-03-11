package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletingFromGroupTests extends TestBase {

  ContactData validContact = null;
  GroupData validGroup = null;

  @BeforeMethod
  public void ensurePreconditions(){
    //Нет групп, создаем
    if(app.db().groups().size() == 0){
      app.goTo().groupPage();
      validGroup = new GroupData().withName("test1").withFooter("test1").withHeader("test1");
      app.group().create(validGroup);
    }
    //Нет контакта, создаем
    if(app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.goTo().newContact();
      validContact = new ContactData().withFirstName("FistName").withEMail("qwe@mail.ru").withLastName("LastName")
              .withAddress("qwer, asdf 4, 123").withMobilePhone("123345234");
      app.contact().creation(validContact,true);
    }
  }

  @Test
  public void ContactDeletingFromGroup() {
    app.goTo().homePage();
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();



  }



}
