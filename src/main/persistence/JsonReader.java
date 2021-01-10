package main.persistence;

import com.google.gson.Gson;
import main.model.Class;
import main.model.ClassList;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonReader {

    public JsonReader() {}

    public ClassList readClassListFromJson () {
        File folder = new File("data");
        File[] listOfFiles = folder.listFiles();
        ClassList result = new ClassList();

        for (int i = 0; i < listOfFiles.length; ++i) {
            if (listOfFiles[i].isFile()) {
                result.addClass(readClassFromJson("class" + i));
            }
        }
        return result;
    }

    public Class readClassFromJson (String filename) {
        Class classObj = null;

        try {
            Gson gson = new Gson();
            classObj = gson.fromJson(new FileReader(filename), Class.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classObj;
    }

}