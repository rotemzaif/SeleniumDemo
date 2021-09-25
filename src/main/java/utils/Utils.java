package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Utils class as an assistance class which includes methods to read/write data from files
 * */
public class Utils {

    private static FileInputStream fis;

    /**
     * this method reads a property from a config.properties file and return its value
     * @param - a String key property name
     * @return - property value
     * */
    public static String readProperty(String key){
        String value = "";
        try {
            fis = new FileInputStream(".\\src\\main\\resources\\data\\configuration.properties");
            Properties prop = new Properties();
            prop.load(fis); // load a properties file
            value = prop.getProperty(key); // get the property value
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String getDownLoadDir(){
        String userHomePath = System.getProperty("user.home");
        String downloadPath = userHomePath + "\\Downloads";
        return downloadPath;
    }

    public static boolean isFileExist(String fileName){
        String filePath =  getDownLoadDir() + "\\" + fileName;
        File file = new File(filePath);
        return file.exists();
    }

    public static boolean deleteFile(String fileName){
        String filePath =  getDownLoadDir() + "\\" + fileName;
        File file = new File(filePath);
        return file.delete();
    }
}
