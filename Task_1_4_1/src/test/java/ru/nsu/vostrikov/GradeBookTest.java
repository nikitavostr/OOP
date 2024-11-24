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
    void testAddSemesterSuccessfully() {
        GradeBook gradeBook = new GradeBook("Nikita Vostrikov", false);
        Semester semester = new Semester(1);
        gradeBook.addSemester(semester);
        assertEquals(1, gradeBook.getSemesters().size());
    }

    @Test
    void testAddDuplicateSemester() {
        GradeBook gradeBook = new GradeBook("Nikita Vostrikov", false);
        Semester semester = new Semester(1);
        gradeBook.addSemester(semester);
        assertThrows(IllegalArgumentException.class, () -> gradeBook.addSemester(new Semester(1)));
    }

    @Test
    void testAddTooManySemesters() {
        GradeBook gradeBook = new GradeBook("Nikita Vostrikov", false);
        for (int i = 1; i <= 8; i++) {
            gradeBook.addSemester(new Semester(i));
        }
        assertThrows(IllegalArgumentException.class, () -> gradeBook.addSemester(new Semester(9)));
    }

    @Test
    void testCalculateAverageGrade() {
        GradeBook gradeBook = new GradeBook("Nikita Vostrikov", false);
        Semester semester = new Semester(1);
        semester.addGrade(new Grade("Imperative Programming", WorkType.DIFF_PASS, 5));
        semester.addGrade(new Grade("Math", WorkType.EXAM, 4));
        semester.addGrade(new Grade("History", WorkType.PASS, 1)); // Не учитывается
        gradeBook.addSemester(semester);

        assertEquals(4.5, gradeBook.calculateAverageGrade());
    }

    @Test
    void testCanGetRedDiplomaWithoutVKR() {
        GradeBook gradeBook = new GradeBook("Nikita Vostrikov", false);

        Semester semester1 = new Semester(1);
        semester1.addGrade(new Grade("Declarative Programming", WorkType.EXAM, 5));
        semester1.addGrade(new Grade("Discrete Math", WorkType.DIFF_PASS, 5));
        semester1.addGrade(new Grade("Imperative Programming", WorkType.DIFF_PASS, 5));
        gradeBook.addSemester(semester1);

        Semester semester2 = new Semester(2);
        semester2.addGrade(new Grade("OS", WorkType.DIFF_PASS, 4));
        gradeBook.addSemester(semester2);
        assertTrue(gradeBook.canGetRedDiploma());

        Semester semester3 = new Semester(3);
        semester3.addGrade(new Grade("English", WorkType.TEST, 3));
        gradeBook.addSemester(semester3);
        assertTrue(gradeBook.canGetRedDiploma()); // только экзамены и диф зачеты имеют влияние

        Semester semester4 = new Semester(4);
        semester4.addGrade(new Grade("PAC", WorkType.EXAM, 4));
        semester4.addGrade(new Grade("Physics", WorkType.EXAM, 5));
        semester4.addGrade(new Grade("Math", WorkType.EXAM, 5));
        gradeBook.addSemester(semester4);
        assertFalse(gradeBook.canGetRedDiploma()); // менее 75% оценок отлично

        Semester semester5 = new Semester(5);
        semester5.addGrade(new Grade("Economy", WorkType.EXAM, 5));
        semester5.addGrade(new Grade("Kotlin", WorkType.EXAM, 5));
        semester5.addGrade(new Grade("AI and ML", WorkType.EXAM, 5));
        semester5.addGrade(new Grade("Python", WorkType.EXAM, 5));
        semester5.addGrade(new Grade("Philosophy", WorkType.EXAM, 3));
        gradeBook.addSemester(semester5);
        assertFalse(gradeBook.canGetRedDiploma()); // 75% оценок отлично, но есть троебан

        for (int i = 6; i < 9; ++i) {
            gradeBook.addSemester(new Semester(i));
        }
        assertFalse(gradeBook.canGetRedDiploma()); // закончено 8 семестров, но нет защиты
    }

    @Test
    void testCanGetRedDiplomaWithVKR() {
        GradeBook gradeBook = new GradeBook("Nikita Vostrikov", false);
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
    void testCanTransferToBudgetOnBudget() {
        GradeBook gradeBook = new GradeBook("Nikita Vostrikov", false);
        Semester semester1 = new Semester(1);
        semester1.addGrade(new Grade("Math", WorkType.EXAM, 5));
        gradeBook.addSemester(semester1);

        Semester semester2 = new Semester(2);
        semester2.addGrade(new Grade("Programming", WorkType.EXAM, 5));
        gradeBook.addSemester(semester2);

        assertFalse(gradeBook.canTransferToBudget()); // уже на бюджете
    }

    @Test
    void testCanTransferToBudget() {
        GradeBook gradeBook = new GradeBook("Nikita Vostrikov", true);
        Semester semester1 = new Semester(1);
        semester1.addGrade(new Grade("Math", WorkType.EXAM, 5));
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
        GradeBook gradeBook = new GradeBook("Nikita Vostrikov", false);
        Semester semester1 = new Semester(1);
        semester1.addGrade(new Grade("Math", WorkType.EXAM, 5));
        semester1.addGrade(new Grade("Math", WorkType.ASSIGNMENT, 3)); // абсолютно безразлично
        gradeBook.addSemester(semester1);
        assertTrue(gradeBook.canGetIncreasedScholarship());

        Semester semester2 = new Semester(2);
        semester2.addGrade(new Grade("Programing", WorkType.DIFF_PASS, 4));
        semester2.addGrade(new Grade("Math", WorkType.EXAM, 5));
        gradeBook.addSemester(semester2);
        assertFalse(gradeBook.canGetIncreasedScholarship());
    }

}