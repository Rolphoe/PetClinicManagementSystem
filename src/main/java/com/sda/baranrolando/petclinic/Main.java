package com.sda.baranrolando.petclinic;

import com.sda.baranrolando.petclinic.utils.SessionManager;

public class Main {
    public static void main(String[] args) {
        SessionManager.getSessionFactory();
        SessionManager.shutDown();
    }
}