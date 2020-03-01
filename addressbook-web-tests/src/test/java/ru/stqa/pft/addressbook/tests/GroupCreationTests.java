package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {


    @Test
  public void testGroupCreation() throws Exception {
      app.getNavigationHelper().goToGroupPage();
      List<GroupData> before = app.getGroupHelper().getGroupList();

      GroupData group = new GroupData("test1", "test1", "test1");
      app.getGroupHelper().groupCreate(group);
      app.getNavigationHelper().goToGroupPage();
      List<GroupData> after = app.getGroupHelper().getGroupList();

      /*
      int maxOld = 0;
      for(GroupData g : after){
        if(g.getId() > max)
          max = g.getId();
      }
      */

      //Тут лямбда выражением получаем максимальный id группы
      group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
      before.add(group);

      //Создаем переменную компаратор, которая умеет сравнивать объекты нашего класса
      Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);

      //Быстрое сравнение
      Assert.assertEquals(after.size(), before.size());
      //Сравнение множеств
      //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

      //Сравнение списков
      Assert.assertEquals(before, after);
  }

}
