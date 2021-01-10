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
        //Iterator<Class> iter = classList.getClasslist().iterator();
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

    public String writeObjectToJson (Object object, String filename) {
        Gson gson = new Gson();
        try {
            gson.toJson(object, new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filename;
    }

    /*private void createFile(int num, String json) {
        File newJson = new File("class" + num);
        try {
            FileWriter myWriter = new FileWriter("class" + num);
            myWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }*/
}
