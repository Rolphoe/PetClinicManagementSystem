package com.sda.baranrolando.petclinic;

import com.sda.baranrolando.petclinic.controller.VetController;
import com.sda.baranrolando.petclinic.repository.VetRepositoryImpl;
import com.sda.baranrolando.petclinic.service.VetServiceImpl;
import com.sda.baranrolando.petclinic.utils.SessionManager;

public class Main {
    public static void main(String[] args) {
        VetController vetController = new VetController(new VetServiceImpl(new VetRepositoryImpl()));
        vetController.createVet();
    }
}