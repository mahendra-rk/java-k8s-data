package foo.bar;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.json.JSONObject;
import org.json.JSONArray;
import java.util.Map;

import org.apache.commons.configuration2.Configuration;

public class App {

  public static void main(String[] args) throws InterruptedException {
    try (ConfigReloader configReloader = new ConfigReloader("/config/config_develop.json")) {
      while (true) {
        Map<String, Object> config = configReloader.getConfig();
//        Object dcs = config.getProperty("dataset_codes");
//        List<String> vals = config.get("dataset_codes");
        ((List<Object>) config.get("dataset_codes")).stream().map(String::valueOf).forEach(System.out::println);
        System.out.println(config.toString());
        System.out.println(config.get("dataset_codes"));
//        JSONArray json = new JSONArray(config);
//        for(int i=0; i<json.length(); i++){
//          JSONObject j = json.getJSONObject(i);
//          System.out.println(j + "\n------");
//        }
      }
    }
  }
}
