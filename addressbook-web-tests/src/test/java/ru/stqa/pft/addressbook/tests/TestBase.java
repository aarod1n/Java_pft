package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

  public void verifyGroupsListInUI(){
    if(Boolean.getBoolean("verifyUI")) {
      Groups groupsFromDB = app.db().groups();
      Groups groupsFromUI = app.group().all();
      assertThat(groupsFromUI, equalTo(groupsFromDB.stream()
              .map((g) -> new GroupData().withId(g.getId()).withName(g.getName())).collect(Collectors.toSet())));
    }
  }

  public void verifyContactsListInUI(){
    if(Boolean.getBoolean("verifyUI")) {
      Contacts contactsFromDB = app.db().contacts();
      Contacts contactsFromUI = app.contact().all();

      assertThat(contactsFromUI, equalTo(contactsFromDB.stream()
              .map((g) -> new ContactData().withId(g.getId())
                      .withLastName(g.getLastName()).withFirstName(g.getFirstName())).collect(Collectors.toSet())));
    }
  }
}
