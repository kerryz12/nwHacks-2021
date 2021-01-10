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
import java.util.Iterator;
import java.util.Scanner;

public class JsonWriter {

    public JsonWriter() {}

    public void writeClassListToJson (ClassList classList) {
        int classNumber = 0;

        /*while (iter.hasNext() && classNumber < 12) {
            json = writeObjectToJson(iter, "class" + classNumber);
            classNumber++;
        }*/

        for (Class c : classList.getClasslist()) {
            if (classNumber > 10) {
                return;
            }

            writeObjectToJson(c, "class" + classNumber);
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
