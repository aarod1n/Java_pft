package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {
  @Test
  public void testGroupModification() {
    app.getNavigationHelper().goToGroupPage();
    int before = app.getGroupHelper().getGroupCount();

    //Проверяем наличие группы
    if(!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().groupCreate(new GroupData("test1", "test1", "test1"));
    }

    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().groupModification(new GroupData("Modification1", "Modification2", "Modification3"), 2);
    app.getNavigationHelper().goToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before);
  }


}
