package ru.nsu.vostrikov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * GradeBook test class.
 */
class GradeBookTest {
    @Test
    void testAddDuplicateSemester() {
        GradeBook gradeBook = new GradeBook("Nikita Vostrikov", false, 0);
        Semester semester = new Semester(1);
        gradeBook.addSemester(semester);
        assertThrows(IllegalArgumentException.class, () -> gradeBook.addSemester(new Semester(1)));
    }

    @Test
    void testAddTooManySemesters() {
        GradeBook gradeBook = new GradeBook("Nikita Vostrikov", false, 0);
        for (int i = 1; i <= 8; i++) {
            gradeBook.addSemester(new Semester(i));
        }
        assertThrows(IllegalArgumentException.class, () -> gradeBook.addSemester(new Semester(9)));
    }

    @Test
    void testCalculateAverageGrade() {
        Semester semester = new Semester(1);
        semester.addGrade(new Grade("Imperative Programming", WorkType.DIFF_PASS, 5));
        semester.addGrade(new Grade("Math", WorkType.EXAM, 4));
        semester.addGrade(new Grade("History", WorkType.PASS, 1)); // Не учитывается
        GradeBook gradeBook = new GradeBook("Nikita Vostrikov", false, 2);
        gradeBook.addSemester(semester);

        assertEquals(4.5, gradeBook.calculateAverageGrade());
    }

    @Test
    void testCanGetRedDiplomaWithoutVkr() {
        Semester semester1 = new Semester(1);
        semester1.addGrade(new Grade("Declarative Programming", WorkType.EXAM, 4));
        semester1.addGrade(new Grade("Discrete Math", WorkType.DIFF_PASS, 4));
        GradeBook gradeBook = new GradeBook("Nikita Vostrikov", false, 12);
        gradeBook.addSemester(semester1);
        assertTrue(gradeBook.canGetRedDiploma()); // все еще можно получить

        Semester semester2 = new Semester(2);
        semester2.addGrade(new Grade("Declarative Programming", WorkType.EXAM, 3));
        gradeBook.addSemester(semester2);
        assertFalse(gradeBook.canGetRedDiploma()); // есть 3
    }

    @Test
    void testCanGetRedDiplomaWithVkr() {
        GradeBook gradeBook = new GradeBook("Nikita Vostrikov", false, 1);
        for (int i = 1; i < 8; ++i) {
            gradeBook.addSemester(new Semester(i));
        }
        Semester semester8 = new Semester(8);
        semester8.addGrade(new Grade("Diploma", WorkType.VKR_DEFENSE, 5));
        semester8.addGrade(new Grade("Programming", WorkType.EXAM, 5));
        gradeBook.addSemester(semester8);
        assertTrue(gradeBook.canGetRedDiploma());
    }

    @Test
    void testCanGetRedDiplomaWithoutVkrDefence() {
        GradeBook gradeBook = new GradeBook("Nikita Vostrikov", false, 1);
        for (int i = 1; i < 8; ++i) {
            gradeBook.addSemester(new Semester(i));
        }
        Semester semester8 = new Semester(8);
        semester8.addGrade(new Grade("C++", WorkType.EXAM, 5));
        gradeBook.addSemester(semester8);
        assertFalse(gradeBook.canGetRedDiploma());
    }

    @Test
    void testCanTransferToBudgetOnBudget() {
        Semester semester1 = new Semester(1);
        semester1.addGrade(new Grade("Math", WorkType.EXAM, 5));
        GradeBook gradeBook = new GradeBook("Nikita Vostrikov", false, 2);
        gradeBook.addSemester(semester1);

        Semester semester2 = new Semester(2);
        semester2.addGrade(new Grade("Programming", WorkType.EXAM, 5));
        gradeBook.addSemester(semester2);

        assertFalse(gradeBook.canTransferToBudget()); // уже на бюджете
    }

    @Test
    void testCanTransferToBudget() {
        Semester semester1 = new Semester(1);
        semester1.addGrade(new Grade("Math", WorkType.EXAM, 5));
        GradeBook gradeBook = new GradeBook("Nikita Vostrikov", true, 4);
        gradeBook.addSemester(semester1);
        assertFalse(gradeBook.canTransferToBudget()); // еще не вырос для бюджета

        Semester semester2 = new Semester(2);
        semester2.addGrade(new Grade("Programming", WorkType.EXAM, 5));
        semester2.addGrade(new Grade("History", WorkType.DIFF_PASS, 3)); // кристально безралично
        gradeBook.addSemester(semester2);
        assertTrue(gradeBook.canTransferToBudget());

        Semester semester3 = new Semester(3);
        semester3.addGrade(new Grade("OS", WorkType.EXAM, 3)); // не безралично
        gradeBook.addSemester(semester3);
        assertFalse(gradeBook.canTransferToBudget());
    }

    @Test
    void testCanGetIncreasedScholarship() {
        Semester semester1 = new Semester(1);
        semester1.addGrade(new Grade("Math", WorkType.EXAM, 5));
        semester1.addGrade(new Grade("Math", WorkType.ASSIGNMENT, 3)); // абсолютно безразлично
        GradeBook gradeBook = new GradeBook("Nikita Vostrikov", false, 2);
        gradeBook.addSemester(semester1);
        assertTrue(gradeBook.canGetIncreasedScholarship());

        Semester semester2 = new Semester(2);
        semester2.addGrade(new Grade("Programing", WorkType.DIFF_PASS, 4));
        semester2.addGrade(new Grade("Math", WorkType.EXAM, 5));
        gradeBook.addSemester(semester2);
        assertFalse(gradeBook.canGetIncreasedScholarship());
    }

}