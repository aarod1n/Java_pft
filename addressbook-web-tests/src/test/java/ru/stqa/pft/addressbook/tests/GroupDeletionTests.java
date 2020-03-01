package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupDeletionTests  extends TestBase{

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().goToGroupPage();

    //Проверяем наличие группы
    if(!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().groupCreate(new GroupData("test1", "test1", "test1"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();

    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().groupDeletion(before.size()- 1);
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();

    before.remove(before.size()-1);
    Assert.assertEquals(after.size(), before.size());

    //Создаем переменную компаратор, которая умеет сравнивать объекты нашего класса
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
  }
}
