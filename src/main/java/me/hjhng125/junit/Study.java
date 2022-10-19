package me.hjhng125.junit;

public class Study {

    private StudyStatus status = StudyStatus.DRAFT;

    private Integer limit;

    public Study(Integer limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit은 0보다 커야한다.");
        }
        this.limit = limit;
    }

    public StudyStatus status() {
        return this.status;
    }

    public Integer limit() {
        return this.limit;
    }
}
