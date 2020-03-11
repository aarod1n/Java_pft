package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

public class ContactAddToGroupTests extends TestBase {

  ContactData newContact = null;
  GroupData newGroup = null;
  GroupData validGroup = null;
  ContactData validContact = null;
  double rand;

  @BeforeMethod
  public void ensurePreconditions(){

    //Нет групп, создаем
    if(app.db().groups().size() == 0){
      app.goTo().groupPage();
      rand = Math.random()*3;
      newGroup = new GroupData().withName("test1" + rand).withFooter("test1").withHeader("test1");
      app.group().create(newGroup);
      Groups groupsRefresh = app.db().groups();
      newGroup.withId(groupsRefresh.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    }
    //Нет контакта, создаем
    if(app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.goTo().newContact();
      newContact = new ContactData().withFirstName("FistName").withEMail("qwe@mail.ru").withLastName("LastName")
              .withAddress("qwer, asdf 4, 123").withMobilePhone("123345234");
      app.contact().creation(newContact,true);
      Contacts contactRefresh = app.db().contacts();
      newContact.withId(contactRefresh.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    }

    //Обновляем множества и пробегаем в поиске валидной группы для добавления в нее контакта
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    for(ContactData contact : contacts){
      for(GroupData group : groups){
        if(groups.stream().filter(g -> g.getName().equals(group.getName())).collect(Collectors.toList()).size() < 2){
          if(!(contact.getGroups().contains(group)))
            validGroup = group;
            validContact = contact;
          }
        }
    }
    if(validGroup == null){
      rand = Math.random()*3;
      validGroup = new GroupData().withName("test1" + rand).withFooter("test1").withHeader("test1");
      app.goTo().homePage();
      app.goTo().groupPage();
      app.group().create(validGroup);
      Groups groupsRefresh = app.db().groups();
      validGroup.withId(groupsRefresh.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    }
    if(validContact == null){
      validContact = contacts.iterator().next();
    }
  }

  @Test
  public void ContactAddToGroup() {
    app.goTo().homePage();
    if(newContact != null){
      if(newGroup != null){
        app.contact().addToGroups(newContact, newGroup);
      }else {
        app.contact().addToGroups(newContact, validGroup);
      }
     }else {
      app.contact().addToGroups(validContact, validGroup);
    }
  }
}
