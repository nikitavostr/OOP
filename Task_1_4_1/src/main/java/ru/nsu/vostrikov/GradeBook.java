package ru.nsu.vostrikov;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Grade book class.
 */
public class GradeBook {
    private final String studentName;
    private final boolean isPaidEducation;
    private final List<Semester> semesters;

    /**
     * Constructor.
     */
    public GradeBook(String studentName, boolean isPaidEducation) {
        this.studentName = studentName;
        this.isPaidEducation = isPaidEducation;
        this.semesters = new ArrayList<>();
    }

    /**
     * Add semester.
     */
    public void addSemester(Semester semester) {
        if (semesters.size() >= 8) {
            throw new IllegalStateException("Нельзя добавить более 8 семестров.");
        }
        if (semesters.stream().anyMatch(s -> s.getSemesterNumber() == semester.getSemesterNumber())) {
            throw new IllegalArgumentException("Семестр с номером " + semester.getSemesterNumber() + " уже существует.");
        }
        if (semesters.size() + 1 != semester.getSemesterNumber()) {
            throw new IllegalArgumentException("Нельзя пропустить семестр");
        }
        semesters.add(semester);
    }

    /**
     * Calculate average grade.
     */
    public double calculateAverageGrade() {
        return semesters.stream()
                .flatMap(semester -> semester.getGrades().stream())
                .filter(grade -> grade.getType() == WorkType.EXAM || grade.getType() == WorkType.DIFF_PASS)
                .mapToInt(Grade::getValue)
                .average()
                .orElse(0.0);
    }

    /**
     * Check ability to transfer to budget.
     */
    public boolean canTransferToBudget() {
        if (!isPaidEducation || semesters.size() < 2) return false;

        List<Semester> lastTwoSemesters = semesters.subList(Math.max(0, semesters.size() - 2), semesters.size());
        return lastTwoSemesters.stream()
                .flatMap(semester -> semester.getGrades().stream())
                .filter(grade -> grade.getType() == WorkType.EXAM)
                .noneMatch(grade -> grade.getValue() == 3);
    }

    /**
     * Check ability to get red diploma.
     */
    public boolean canGetRedDiploma() {
        Map<String, Grade> latestGrades = semesters.stream()
                .flatMap(semester -> semester.getGrades().stream())
                .filter(grade -> grade.getType() == WorkType.EXAM || grade.getType() == WorkType.DIFF_PASS)
                .collect(Collectors.toMap(
                        Grade::getSubject,
                        grade -> grade,
                        (existing, replacement) -> replacement
                ));

        long excellentGradesCount = latestGrades.values().stream()
                .filter(grade -> grade.getValue() == 5)
                .count();

        boolean hasSatisfactoryGrades = latestGrades.values().stream()
                .anyMatch(grade -> grade.getValue() == 3);

        boolean atLeast75PercentExcellent = !latestGrades.isEmpty() &&
                excellentGradesCount >= latestGrades.size() * 0.75;

        boolean qualificationWorkExcellent = semesters.stream()
                .flatMap(semester -> semester.getGrades().stream())
                .filter(grade -> grade.getType() == WorkType.VKR_DEFENSE)
                .allMatch(grade -> grade.getValue() == 5);

        boolean notAllSemesters = semesters.size() < 8;

        return !hasSatisfactoryGrades && atLeast75PercentExcellent && (qualificationWorkExcellent || notAllSemesters);
    }

    /**
     * check ability to get increased scholarship.
     */
    public boolean canGetIncreasedScholarship() {
        if (semesters.isEmpty()) return false;

        Semester currentSemester = semesters.get(semesters.size() - 1);
        return currentSemester.getGrades().stream()
                .filter(grade -> grade.getType() == WorkType.EXAM || grade.getType() == WorkType.DIFF_PASS)
                .allMatch(grade -> grade.getValue() == 5);
    }

    /**
     * Get semesters.
     */
    public List<Semester> getSemesters() {
        return semesters;
    }
}