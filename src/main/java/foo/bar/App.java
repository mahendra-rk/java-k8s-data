package foo.bar;

import java.util.Date;
import java.util.Objects;

import org.apache.commons.configuration2.Configuration;

public class App {

  public static void main(String[] args) throws InterruptedException {
    try (ConfigReloader configReloader = new ConfigReloader("/config/config_develop.json")) {
      while (true) {
        Configuration config = configReloader.getConfig();
        Object name = config.getProperty("current_env");
        int sleepInterval = (int) config.getProperty("sleepIntervalMillis");
        System.out.println(String.format("Hello %s, it is %s", name, new Date()));
        Thread.sleep(sleepInterval);
      }
    }
  }
}
