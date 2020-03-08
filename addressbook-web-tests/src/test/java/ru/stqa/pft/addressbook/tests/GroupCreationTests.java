package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroupsFromXML() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/res/groups.xml")));
    String xml = "";
    String line = reader.readLine();
    while(line != null){
      xml += line;
      line = reader.readLine();
    }
    XStream xStream = new XStream();
    xStream.processAnnotations(GroupData.class);
    List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
    return groups.stream().map((g)-> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJSON() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/res/groups.json")));
    String json = "";
    String line = reader.readLine();
    while(line != null){
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());
    return groups.stream().map((g)-> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "validGroupsFromJSON")
  public void testGroupCreation(GroupData group) throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);
    app.goTo().groupPage();
    //Быстрое сравнение
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    //Тут лямбда выражением получаем максимальный id группы
    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    //Сравнение множеств v2
    assertThat(after, equalTo(before.withAddet(group)));
  }

}
