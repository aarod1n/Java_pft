package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {
  @Test
  public void testGroupModification() {
    app.getNavigationHelper().goToGroupPage();

    //Проверяем наличие группы
    if(!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().groupCreate(new GroupData("test1", "test1", "test1"));
    }

    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().groupModification(new GroupData("Modification1", "Modification2", "Modification3"));
    app.getNavigationHelper().goToGroupPage();
  }


}
