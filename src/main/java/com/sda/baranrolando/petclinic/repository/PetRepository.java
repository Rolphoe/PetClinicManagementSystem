package com.sda.baranrolando.petclinic.repository;

import com.sda.baranrolando.petclinic.model.Pet;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PetRepository {
    void createPet(
            String ownerName,
            String race,
            Date birthDate,
            boolean isVaccinated
    );

    List<Pet> getAllPets();

    Optional<Pet> findbyId(int id);

    void updatePetById(
            int id,
            String ownerName,
            String race,
            Date birthDate,
            boolean isVaccinated
    );

    void deletePetById(int id);
}
