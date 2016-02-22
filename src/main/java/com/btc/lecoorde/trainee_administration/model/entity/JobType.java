package com.btc.lecoorde.trainee_administration.model.entity;

/**
 * Created by DESIMON on 05.10.2015.
 */

public enum JobType {

    DUALER_STUDENT_ANWENDUNGSENTWICKLUNG("Dualer Student - Anwendungsentwicklung"),
    DUALER_STUDENT_BWL("Dualer Student - BWL"),
    DUALER_STUDENT_SYSTEMINTEGRATION("Dualer Student - Systemintegration"),
    FACHINFORMATIKER_ANWENDUNGSENTWICKLUNG("Fachinformatiker - Anwendungsentwicklung"),
    FACHINFORMATIKER_SYSTEMINTEGRATION("Fachinformatiker - Systemintegration"),
    IT_SYSTEMKAUFMANN("IT-Systemkaufmann");

    private final String jobName;

    JobType(String jobName) {
        this.jobName = jobName;
    }

    public String getJobName() {
        return this.jobName;
    }

//    public int compareToJob(JobType jobtype) {
//        if (this.ordinal > jobtype.getJobName()) {
//            return 1;
//        } else if (this.ordinal == jobtype.getJobName()) {
//            return 0;
//        } else return -1;
//    }

//    public static String getJobName(JobType jobType) {
//        switch (jobType) {
//            case DUALER_STUDENT_ANWENDUNGSENTWICKLUNG:
//                return "Dualer_Student_Anwendungsentwicklung";
//            case DUALER_STUDENT_BWL:
//                return "Dualer_Student_BWL";
//            case DUALER_STUDENT_SYSTEMINTEGRATION:
//                return "Dualer_Student_Systemintegration";
//            case FACHINFORMATIKER_ANWENDUNGSENTWICKLUNG:
//                return "Fachinformatiker_Anwendungsentwicklung";
//            case FACHINFORMATIKER_SYSTEMINTEGRATION:
//                return "Fachinformatiker_Systemintegration";
//            case IT_SYSTEMKAUFMANN:
//                return "IT-Systemkaufmann";
//        }
//        return null;
//    }
}


