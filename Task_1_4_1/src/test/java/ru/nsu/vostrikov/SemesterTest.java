package ru.nsu.vostrikov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Semester test class.
 */
class SemesterTest {

    @Test
    void testValidSemesterCreation() {
        Semester semester = new Semester(1);
        assertEquals(1, semester.getSemesterNumber());
        assertTrue(semester.getGrades().isEmpty());
    }

    @Test
    void testInvalidSemesterNumberBelowRange() {
        assertThrows(IllegalArgumentException.class, () -> new Semester(0));
    }

    @Test
    void testInvalidSemesterNumberAboveRange() {
        assertThrows(IllegalArgumentException.class, () -> new Semester(9));
    }

    @Test
    void testAddGradeSuccessfully() {
        Semester semester = new Semester(1);
        Grade grade = new Grade("Math", WorkType.EXAM, 5);
        semester.addGrade(grade);
        assertEquals(1, semester.getGrades().size());
        assertEquals(grade, semester.getGrades().get(0));
    }

    @Test
    void testAddDiplomaDefenseInWrongSemester() {
        Semester semester = new Semester(7);
        Grade diplomaGrade = new Grade("Diploma", WorkType.VKR_DEFENSE, 5);
        assertThrows(IllegalArgumentException.class, () -> semester.addGrade(diplomaGrade));
    }

    @Test
    void testAddDuplicateGrade() {
        Semester semester = new Semester(1);
        Grade grade1 = new Grade("Math", WorkType.EXAM, 5);
        semester.addGrade(grade1);

        Grade grade2 = new Grade("Math", WorkType.EXAM, 4);
        assertThrows(IllegalArgumentException.class, () -> semester.addGrade(grade2));
    }
}