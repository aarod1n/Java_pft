package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class GroupDeletionTests  extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    //Проверяем наличие группы
    if((app.group().all().size()) == 0){
      app.group().create(new GroupData().withName("test1").withFooter("test1").withHeader("test1"));
    }
  }

  @Test
  public void testGroupDeletion() {

    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().deletionById(deletedGroup);
    app.goTo().groupPage();
    Groups after = app.group().all();
    assertEquals(after.size(), before.size() - 1);
    //Сравнение множеств v2
    assertThat(after, equalTo(before.withOut(deletedGroup)));
  }
}
