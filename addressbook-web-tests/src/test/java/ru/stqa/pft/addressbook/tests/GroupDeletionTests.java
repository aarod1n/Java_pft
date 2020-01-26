package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests  extends TestBase{

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroupToDelet();
    app.getGroupHelper().deletSelectedGroup();
    app.getNavigationHelper().goToGroupPage();
  }
}
