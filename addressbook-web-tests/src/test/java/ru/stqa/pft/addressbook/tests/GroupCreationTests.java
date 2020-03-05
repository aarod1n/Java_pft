package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();

    GroupData group = new GroupData().withName("test1").withFooter("test1").withHeader("test1");
    app.group().create(group);
    app.goTo().groupPage();
    Groups after = app.group().all();

    //Тут лямбда выражением получаем максимальный id группы
    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    //before.add(group);

    //Быстрое сравнение
    assertThat(after.size(), equalTo(before.size() + 1));

    //Сравнение множеств
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    //Сравнение множеств v2
    assertThat(after, equalTo(before.withAddet(group)));
  }

}
