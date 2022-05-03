package com.uca.freelance.DataAccessLayer.entities;


public enum Role {
    ADMIN,
    FREELANCER,
    EMPLOYER;

    public String toString(){
        switch(this) {
            case ADMIN:
                return "ADMIN";

            case FREELANCER:
                return "FREELANCER";

            case EMPLOYER:
                return "EMPLOYER";

            default:
                return null;
        }
    }
}
