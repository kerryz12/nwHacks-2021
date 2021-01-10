package main.persistence;

import com.google.gson.Gson;
import main.model.Class;
import main.model.ClassList;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonWriter {
    private static final int LIMIT = 32;

    public JsonWriter() {}

    public void writeUsernameToJson (String username) {
        try (FileWriter writer = new FileWriter("data/username.json")) {
            writer.write(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

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

    public void writeObjectToJson (Object object, String filename) {
        Gson gson = new Gson();

        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(object, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }
}
