package nl.vu.cs.softwaredesign.repository;

import nl.vu.cs.softwaredesign.domain.exercise.Exercise;
import java.util.List;

public interface ExerciseRepository {
    List<Exercise> getAll();
}