package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {
  @Test
  public void testGroupModification() {
    app.getNavigationHelper().goToGroupPage();


    //Проверяем наличие группы
    if(!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().groupCreate(new GroupData("test1", "test1", "test1"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();

    GroupData group = new GroupData(before.get(before.size() - 1).getId(),"Modification1", "Modification2", "Modification3");

    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().groupModification(group, before.size() - 1);
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();

    before.remove(before.size() - 1);
    before.add(group);

    //Создаем переменную компаратор, которая умеет сравнивать объекты нашего класса
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(after.size(), before.size());

    //Сравнение множеств
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    //Сравнение списков
    Assert.assertEquals(before, after);
  }
}
