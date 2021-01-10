package main.persistence;

import com.google.gson.Gson;
import main.model.Class;

import java.io.FileReader;
import java.io.IOException;

public class JsonReader {

    public JsonReader() {}

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
