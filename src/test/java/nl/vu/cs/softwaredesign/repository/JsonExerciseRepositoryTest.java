package nl.vu.cs.softwaredesign.repository;

import nl.vu.cs.softwaredesign.domain.exercise.Exercise;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonExerciseRepositoryTest {

    @Test
    void testGetAllLoadsExercisesCorrectly() throws IOException {
        // creat a temporary dummy JSON file
        File tempFile = File.createTempFile("test_exercises", ".json");
        tempFile.deleteOnExit();
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("[{\"name\": \"Push Up\", \"difficulty\": \"BEGINNER\", \"muscleGroups\": [\"CHEST\"], \"equipmentNeeded\": [\"BODYWEIGHT\"]}]");
        }

        ExerciseRepository repo = new JsonExerciseRepository(tempFile.getAbsolutePath());
        List<Exercise> exercises = repo.getAll();

        assertNotNull(exercises);
        assertEquals(1, exercises.size());
    }
}