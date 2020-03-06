package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

  public String mergeEmail(ContactData contact){
    return Arrays.asList(contact.getEMail(), contact.getEMail2(), contact.getEMail3()).
            stream().filter(s->!s.equals(""))
            .collect(Collectors.joining("\n"));

  }

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    //Проверяем наличие контакта
    if (!app.contact().isThereAContact()) {
      app.goTo().newContact();
      app.contact()
              .creation(new ContactData()
                              .withGroup("test1").withFirstName("FistName").withEMail("qwe@mail.ru")
                              .withLastName("LastName").withAddress("qwer, asdf 4, 123").withMobilePhone("123345234"),
                      true);
    }
  }

  @Test
  public void contactEmailTest(){
    app.goTo().homePage();
    ContactData contactFromTable = app.contact().all().iterator().next();
    ContactData contactFromEditForm = app.contact().getInfoForEditForm(contactFromTable.getId());
    assertThat(contactFromTable.getAllEmail(), equalTo(mergeEmail(contactFromEditForm)));
  }
}
