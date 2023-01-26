package foo.bar;

import java.util.List;

import java.util.Map;


public class App {

  public static void main(String[] args) throws InterruptedException {
    try (ConfigReloader configReloader = new ConfigReloader("/config/config_develop.json")) {
      while (true) {
        Map<String, Object> config = configReloader.getConfig();
        ((List<Object>) config.get("dataset_codes")).stream().map(String::valueOf).forEach(System.out::println);
        System.out.println(config.toString());
        System.out.println(config.get("dataset_codes"));
      }
    }
  }
}
