package ru.nsu.vostrikov;

/**
 * Grade class.
 */
public class Grade {
    private final String subject;
    private final WorkType type;
    private final int value;

    /**
     * Constructor.
     */
    public Grade(String subject, WorkType type, int value) {
        checkGrade(type, value);
        this.subject = subject;
        this.type = type;
        this.value = value;
    }

    /**
     * Get subject.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Get type.
     */
    public WorkType getType() {
        return type;
    }

    /**
     * Get value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Check validity of grade.
     */
    private void checkGrade(WorkType type, int value) {
        if (type == WorkType.PASS && value != GradeConstansts.PASS) {
            throw new IllegalArgumentException("Зачет может быть только 'Зачет'");
        } else if (type != WorkType.PASS
                && (value < GradeConstansts.SATISFACTORY
                || value > GradeConstansts.EXCELLENT)) {
            throw new IllegalArgumentException("Оценка может быть либо 3, либо 4, либо 5");
        }
    }
}
