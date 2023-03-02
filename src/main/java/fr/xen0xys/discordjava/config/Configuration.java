package fr.xen0xys.discordjava.config;

import org.bspfsystems.yamlconfiguration.file.YamlConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@SuppressWarnings({"unused", "ResultOfMethodCallIgnored"})
public class Configuration {

    private final String configName;
    private final YamlConfiguration configuration;
    private final File configFile;

    public Configuration(File dataFolder, String configName){
        this.configName = configName;
        this.configFile = new File(dataFolder, configName);
        if(!this.configFile.exists()) {
            this.configFile.getParentFile().mkdirs();
            this.saveDefault();
        }
        this.configuration = YamlConfiguration.loadConfiguration(this.configFile);
    }

    public void save(){
        try {
            this.configuration.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDefault(){
        try {
            InputStream configInputStream = getClass().getClassLoader().getResourceAsStream(this.configName);
            try (FileOutputStream outputStream = new FileOutputStream(this.configFile)) {
                Objects.requireNonNull(configInputStream).transferTo(outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public YamlConfiguration getConfiguration(){
        return this.configuration;
    }

    public String getToken(){
        return this.configuration.getString("Bot.Token");
    }

}
