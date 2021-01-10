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

    public void writeClassListToJson (ClassList classList) {
        Iterator<Class> iter = classList.getClasslist().iterator();
        int classNumber = 0;
        String json;

        while (iter.hasNext()) {
            json = writeObjectToJson(iter);
            createFile(classNumber, json);
            classNumber++;
        }

        return;
    }

    public Class readClassFromJson (String filename) {
        Class classObj = null;

        /*File newJson = new File(filename);
        try {
            Scanner myReader = new Scanner(newJson);
            while (myReader.hasNextLine()) {
                args = args + myReader.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        try {
            Gson gson = new Gson();
            classObj = gson.fromJson(new FileReader(filename), Class.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return classObj;
    }

    private String writeObjectToJson (Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(object);

        return json;
    }

    private void createFile(int num, String json) {
        File newJson = new File("class" + num);
        try {
            FileWriter myWriter = new FileWriter("class" + num);
            myWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }
}
