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
import java.util.logging.Level;
import java.util.logging.Logger;

// A repository implementation that reads exercises from a JSON file.
// This class uses the Gson library to parse the JSON data and convert it into a list of Exercise objects.
// The file path to the JSON file is provided through the constructor, allowing for flexibility in where the exercise data is stored.
public class JsonExerciseRepository implements ExerciseRepository {
    private static final Logger LOGGER = Logger.getLogger(JsonExerciseRepository.class.getName());
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
            LOGGER.log(Level.SEVERE, "Failed to read JSON file", e);
            return Collections.emptyList();
        }
    }
}