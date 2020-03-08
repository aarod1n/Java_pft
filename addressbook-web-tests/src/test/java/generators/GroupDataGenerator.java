package generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

//Генерируем данные с помощью библиотеки JCommander
public class GroupDataGenerator {

  @Parameter(names = "-c", description = "Group count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    GroupDataGenerator generator = new GroupDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();

  }

  private void run() throws IOException {
    List<GroupData> groups = generatGroup(count);
    if(format.equals("csv")){
      saveAsCSV(groups, new File(file));
    } else if (format.equals("xml")){
      saveAsXML(groups, new File(file));
    } else if (format.equals("json")){
      saveAsJSON(groups, new File(file));
    } else {
      System.out.println("Unknown format" + format);
    }

  }

  private List<GroupData> generatGroup(int count) {
    List<GroupData> groups = new ArrayList<GroupData>();
    for (int i = 0; i < count; i++) {
      groups.add(new GroupData().withName(String.format("Name %s", i)).withHeader(String.format("Header %s", i)).withFooter(String.format("Footer %s", i)));
    }
    return groups;
  }

  private void saveAsCSV(List<GroupData> groups, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (GroupData group : groups) {
      writer.write(String.format("%s; %s; %s\n", group.getName(), group.getHeader(), group.getFooter()));
    }
    writer.close();
  }

  private void saveAsXML(List<GroupData> groups, File file) throws IOException{
    XStream xStream = new XStream();
    //xStream.alias("group", GroupData.class);

    //Обрабатывать все аннотации в классе GroupData
    xStream.processAnnotations(GroupData.class);
    String xml = xStream.toXML(groups);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private void saveAsJSON(List<GroupData> groups, File file) throws IOException{
    ////excludeFieldsWithoutExposeAnnotation() обрабатывать все аннотации в классе GroupData
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(groups);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }
}
