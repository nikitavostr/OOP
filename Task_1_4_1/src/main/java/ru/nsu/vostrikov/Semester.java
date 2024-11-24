package ru.nsu.vostrikov;

import java.util.ArrayList;
import java.util.List;

/**
 * Semester class.
 */
public class Semester {
    private final int semesterNumber;
    private final List<Grade> grades;

    /**
     * Constructor.
     */
    public Semester(int semesterNumber) {
        if (semesterNumber < 1 || semesterNumber > 8) {
            throw new IllegalArgumentException("Номер семестра должен быть от 1 до 8.");
        }
        this.semesterNumber = semesterNumber;
        this.grades = new ArrayList<>();
    }

    /**
     * Add grade method.
     */
    public void addGrade(Grade grade) {
        if (grades.stream().anyMatch(g -> g.getSubject().equals(grade.getSubject())
                && (g.getType() == WorkType.EXAM || g.getType() == WorkType.DIFF_PASS || g.getType() == WorkType.PASS))
                && (grade.getType() == WorkType.EXAM || grade.getType() == WorkType.DIFF_PASS || grade.getType() == WorkType.PASS)) {
            throw new IllegalArgumentException("Для предмета " + grade.getSubject() + " уже есть оценка!");
        }
        if (grade.getType() == WorkType.VKR_DEFENSE && semesterNumber != 8) {
            throw new IllegalArgumentException("Защита диплома может быть только в 8 семестре.");
        }

        grades.add(grade);
    }

    /**
     * Get grades.
     */
    public List<Grade> getGrades() {
        return grades;
    }

    /**
     * Get semester number.
     */
    public int getSemesterNumber() {
        return semesterNumber;
    }
}
