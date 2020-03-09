package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if(app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withFooter("test1").withHeader("test1"));
    }
  }

  @Test
  public void testGroupModification() {
    app.goTo().groupPage();
    Groups before = app.db().groups();
    GroupData groupModification = before.iterator().next();
    GroupData group = new GroupData()
            .withId(groupModification.getId()).withName("test1231231").withFooter("test123131").withHeader("test12312311");
    app.goTo().groupPage();
    app.group().modificationById(group);
    app.goTo().groupPage();
    //Быстрое сравнение
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.db().groups();
    //Сравнение множеств v2
    assertThat(after, equalTo(before.withOut(groupModification).withAddet(group)));
  }
}
