package ru.stqa.pft.addressbook.tests;


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

  ContactData newContact = null;
  GroupData newGroup = null;
  double rand;

  @BeforeMethod
  public void ensurePreconditions() {

    //Нет групп, создаем
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      rand = Math.random() * 3;
      newGroup = new GroupData().withName("test1" + rand).withFooter("test1").withHeader("test1");
      app.group().create(newGroup);
      Groups groupsRefresh = app.db().groups();
      newGroup.withId(groupsRefresh.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    }
    //Нет контакта, создаем
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.goTo().newContact();
      newContact = new ContactData().withFirstName("FistName").withEMail("qwe@mail.ru").withLastName("LastName")
              .withAddress("qwer, asdf 4, 123").withMobilePhone("123345234");
      app.contact().creation(newContact, true);
      Contacts contactRefresh = app.db().contacts();
      newContact.withId(contactRefresh.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    }
    //Обновляем множества пробегаем в поиске валидной группы для добавления в нее контакта
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    for (ContactData contact : contacts) {
      for (GroupData group : groups) {
        if (groups.stream().filter(g -> g.getName().equals(group.getName())).collect(Collectors.toList()).size() < 2) {
          if ((contact.getGroups().contains(group))) {
            newGroup = group;
            newContact = contact;
            return;
          }
        }
      }
    }
    if ((newContact == null && newGroup == null)) {
      rand = Math.random() * 3;
      newGroup = new GroupData().withName("test1" + rand).withFooter("test1").withHeader("test1");
      app.goTo().groupPage();
      app.group().create(newGroup);
      Groups groupsRefresh = app.db().groups();
      newGroup.withId(groupsRefresh.stream().mapToInt((g) -> g.getId()).max().getAsInt());
      newContact = contacts.iterator().next();
    }
    app.goTo().homePage();
    app.contact().addToGroups(newContact, newGroup);
  }

  @Test
  public void ContactDeletingFromGroup() {
    app.goTo().homePage();
    app.contact().deleteFromGroups(newContact, newGroup);
    //Послк добавления получили актуальное множество
    Contacts cs = app.db().contacts();
    //Достаем из множества нужный контакт и проверяем его вхождение в нашу группу
    ContactData contactForAssert = app.contact().getContact(cs, newContact.getId());
    assertThat(contactForAssert.getGroups(), not(hasItem(newGroup)));
  }
}
