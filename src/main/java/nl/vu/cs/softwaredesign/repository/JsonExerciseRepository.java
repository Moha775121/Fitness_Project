package nl.vu.cs.softwaredesign.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nl.vu.cs.softwaredesign.domain.exercise.Exercise;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class JsonExerciseRepository implements ExerciseRepository {
    private final String filePath;

    public JsonExerciseRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Exercise> getAll() {
        try (Reader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Exercise>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.err.println("Failed to read JSON file: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}