package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {


    @Test
  public void testGroupCreation() throws Exception {
      app.getNavigationHelper().goToGroupPage();
      int before = app.getGroupHelper().getGroupCount();
      app.getGroupHelper().groupCreate(new GroupData("test1", "test1", "test1"));
      app.getNavigationHelper().goToGroupPage();
      int after = app.getGroupHelper().getGroupCount();
      Assert.assertEquals(after, before + 1);
  }

}
