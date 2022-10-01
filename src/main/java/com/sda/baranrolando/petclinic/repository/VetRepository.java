package com.sda.baranrolando.petclinic.repository;

import com.sda.baranrolando.petclinic.model.Vet;

public interface VetRepository {
    void createVet(
            String firstName,
            String lastName,
            String address,
            String speciality
    );
}
