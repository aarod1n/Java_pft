package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private WebDriver wd;
  private String browser;
  private final Properties properties;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();

  }

  //Инициализация драйверов и переменных для запуска тестов
  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));


    //Выбираем какой браузер использовть в тестах
    if(browser.equals(BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.IE)){
      wd = new InternetExplorerDriver();
    }

    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseURL"));
   }

  public void stop() {
    wd.quit();
  }

  public void logOut() {
    wd.findElement(By.linkText("Logout")).click();
  }

  //Для закрытия диалогового окна (alert), которое появляется при удалении контакта, нужно использовать такую команду драйвера:
  public void alertAccept(){
    wd.switchTo().alert().accept();
  }

}
