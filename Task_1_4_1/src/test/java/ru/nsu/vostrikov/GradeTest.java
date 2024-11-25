package ru.nsu.vostrikov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * GradeTest class.
 */
class GradeTest {

    @Test
    void testValidGradeCreation() {
        Grade grade = new Grade("Physics", WorkType.EXAM, 5);
        assertEquals("Physics", grade.getSubject());
        assertEquals(WorkType.EXAM, grade.getType());
        assertEquals(5, grade.getValue());
    }

    @Test
    void testInvalidGradeValueBelowRange() {
        assertThrows(IllegalArgumentException.class, () -> new Grade(
                "Math", WorkType.ASSIGNMENT, 0)
        );
    }

    @Test
    void testInvalidGradeValueAboveRange() {
        assertThrows(IllegalArgumentException.class, () -> new Grade(
                "Math", WorkType.EXAM, 6)
        );
    }

    @Test
    void testValidPassGrade() {
        Grade grade = new Grade("English", WorkType.PASS, 1);
        assertEquals(1, grade.getValue());
    }

    @Test
    void testInvalidPassGrade() {
        assertThrows(IllegalArgumentException.class, () -> new Grade(
                "English", WorkType.PASS, 5)
        );
    }
}