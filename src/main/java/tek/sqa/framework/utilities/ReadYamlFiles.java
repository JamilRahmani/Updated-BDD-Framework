package tek.sqa.framework.utilities;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class ReadYamlFiles {
    private static ReadYamlFiles readYamlFiles;
    private HashMap propertyMap;

    //Introduce Singleton for This Class.
    private ReadYamlFiles(String filePath) throws FileNotFoundException {
        FileInputStream fileInputStream = FileUtility.getFileInputStream(filePath);
        Yaml yaml = new Yaml();
        this.propertyMap = yaml.load(fileInputStream);
    }

    public static ReadYamlFiles getInstance(String filePath) throws FileNotFoundException {
        if (readYamlFiles == null)
            return new ReadYamlFiles(filePath);
        return readYamlFiles;
    }

    public HashMap getYamlProperty(String key) {
       return (HashMap) this.propertyMap.get(key);
    }


    public static void main(String[] args) throws FileNotFoundException {
        String filePath = System.getProperty("user.dir") + "/src/main/resources/env_config.yml";
       var instance = getInstance(filePath);
       var value = instance.getYamlProperty("ui.browser");
        System.out.println(value);
    }

}
