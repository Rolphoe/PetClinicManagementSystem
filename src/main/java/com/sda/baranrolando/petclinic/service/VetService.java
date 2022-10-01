package com.sda.baranrolando.petclinic.service;

import com.sda.baranrolando.petclinic.model.Vet;

import java.util.List;

public interface VetService {
    void createVet(
            String firstName,
            String lastName,
            String address,
            String speciality
    );

    List<Vet> getAllVets();
}
