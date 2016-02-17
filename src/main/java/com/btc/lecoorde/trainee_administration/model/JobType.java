package com.btc.lecoorde.trainee_administration.model;

/**
 * Created by DESIMON on 05.10.2015.
 */

public enum JobType {

    DUALER_STUDENT_ANWENDUNGSENTWICKLUNG(0),
    DUALER_STUDENT_BWL(1),
    DUALER_STUDENT_SYSTEMINTEGRATION(2),
    FACHINFORMATIKER_ANWENDUNGSENTWICKLUNG(3),
    FACHINFORMATIKER_SYSTEMINTEGRATION(4),
    IT_SYSTEMKAUFMANN(5);

    private final int ordinal;

    JobType(int ordinal) {
        this.ordinal = ordinal;
    }

    public int getOrdinal() {
        return this.ordinal;
    }

    public int compareToJob(JobType jobtype) {
        if (this.ordinal > jobtype.getOrdinal()) {
            return 1;
        } else if (this.ordinal == jobtype.getOrdinal()) {
            return 0;
        } else return -1;
    }

    public static String getJobName(JobType jobType) {
        switch (jobType) {
            case DUALER_STUDENT_ANWENDUNGSENTWICKLUNG:
                return "Dualer_Student_Anwendungsentwicklung";
            case DUALER_STUDENT_BWL:
                return "Dualer_Student_BWL";
            case DUALER_STUDENT_SYSTEMINTEGRATION:
                return "Dualer_Student_Systemintegration";
            case FACHINFORMATIKER_ANWENDUNGSENTWICKLUNG:
                return "Fachinformatiker_Anwendungsentwicklung";
            case FACHINFORMATIKER_SYSTEMINTEGRATION:
                return "Fachinformatiker_Systemintegration";
            case IT_SYSTEMKAUFMANN:
                return "IT-Systemkaufmann";
        }
        return null;
    }
}


