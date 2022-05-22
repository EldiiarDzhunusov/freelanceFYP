package com.uca.freelance.DataAccessLayer.models;

public enum ApplicationStatus {
    PENDING,
    ACCEPTED,
    REJECTED;

    public String toString(){
        switch(this) {
            case PENDING:
                return "PENDING";

            case ACCEPTED:
                return "ACCEPTED";

            case REJECTED:
                return "REJECTED";

            default:
                return null;
        }
    }
}
