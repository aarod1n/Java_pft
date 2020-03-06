package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;



public class ContactPhoneTest extends TestBase {

  public String mergePhone(ContactData contact){
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()).
            stream().filter(s->!s.equals(""))
            .map(ContactPhoneTest::cleaned)
            .collect(Collectors.joining("\n"));

  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-()]","");
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
  public void phoneTest(){
    app.goTo().homePage();
    ContactData contactFromTable = app.contact().all().iterator().next();
    ContactData contactFromEditForm = app.contact().getInfoForEditForm(contactFromTable.getId());
    assertThat(contactFromTable.getAllPhones(), equalTo(mergePhone(contactFromEditForm)));

  }
}
