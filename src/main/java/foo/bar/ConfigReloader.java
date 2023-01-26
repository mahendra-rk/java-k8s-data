package foo.bar;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.JSONConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.ConfigurationConverter;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.reloading.PeriodicReloadingTrigger;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class ConfigReloader implements AutoCloseable {

  private final PeriodicReloadingTrigger trigger;
  private final ReloadingFileBasedConfigurationBuilder<FileBasedConfiguration> builder;

  public ConfigReloader(String configFilePath) {
    Parameters params = new Parameters();
    File propertiesFile = new File(configFilePath);
    builder = new ReloadingFileBasedConfigurationBuilder<FileBasedConfiguration>(
        JSONConfiguration.class)
        .configure(params.fileBased().setFile(propertiesFile));
    trigger = new PeriodicReloadingTrigger(
        builder.getReloadingController(),
        null, 1, TimeUnit.SECONDS);
    trigger.start();
  }

  public Map<String, Object> getConfig() {
    try {
      return ConfigurationConverter.getMap(builder.getConfiguration()).entrySet().stream()
              .collect(toMap(
                      e -> e.getKey().toString(), Map.Entry::getValue));
    } catch (ConfigurationException cex) {
      throw new RuntimeException(cex);
    }
  }

  @Override
  public void close() {
    trigger.stop();
  }
}
