package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    //Проверяем наличие группы
    if(app.group().all().size() == 0){
      app.group().create(new GroupData().withName("test1").withFooter("test1").withHeader("test1"));
    }
  }

  @Test
  public void testGroupModification() {

    Groups before = app.group().all();
    GroupData groupModification = before.iterator().next();
    GroupData group = new GroupData()
            .withId(groupModification.getId()).withName("test1231231").withFooter("test123131").withHeader("test12312311");
    app.goTo().groupPage();
    app.group().modificationById(group);
    app.goTo().groupPage();
    Groups after = app.group().all();
    before.remove(groupModification);
    before.add(group);
    Assert.assertEquals(after.size(), before.size());

    //Сравнение множеств
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    //Сравнение множеств v2
    assertThat(after, CoreMatchers.equalTo(before.withOut(groupModification).withAddet(group)));
  }
}
