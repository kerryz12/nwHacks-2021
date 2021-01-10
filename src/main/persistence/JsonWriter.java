package main.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import main.model.Class;
import main.model.ClassList;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;

public class JsonWriter {
    private static final int LIMIT = 32;

    public JsonWriter() {}

    public void writeClassListToJson (ClassList classList) {
        int classNumber = 0;

        for (Class c : classList.getClasslist()) {
            if (classNumber > LIMIT) {
                return;
            }

            try {
                Files.createDirectories(Paths.get("data"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            writeObjectToJson(c, "data/class" + classNumber + ".json");
            classNumber++;
        }

        return;
    }

    public String writeObjectToJson (Object object, String filename) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(object, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filename;
    }
}
