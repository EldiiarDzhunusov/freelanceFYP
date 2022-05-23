package com.uca.freelance.DataAccessLayer.models;

public enum JobStatus {
    PENDING ,
    STARTED,
    FINISHED;

    public String toString(){
        switch(this) {
            case PENDING:
                return "PENDING";

            case STARTED:
                return "STARTED";

            case FINISHED:
                return "FINISHED";

            default:
                return null;
        }
    }
}
