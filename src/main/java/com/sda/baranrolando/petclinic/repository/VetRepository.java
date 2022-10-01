package com.sda.baranrolando.petclinic.repository;

import com.sda.baranrolando.petclinic.model.Vet;

import java.util.List;
import java.util.Optional;

public interface VetRepository {
    void createVet(
            String firstName,
            String lastName,
            String address,
            String speciality
    );

    List<Vet> getAllVets();

    Optional<Vet> findById(int id);

    void updateVetById(
            int id,
            String firstName,
            String lastName,
            String address,
            String speciality
    );
}
