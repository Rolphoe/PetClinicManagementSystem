package com.sda.baranrolando.petclinic.service;

import com.sda.baranrolando.petclinic.model.Pet;
import com.sda.baranrolando.petclinic.repository.PetRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;


    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public void createPet(String ownerName, String race, Date birthDate, boolean isVaccinated) {
        if (ownerName == null || ownerName.isBlank()) {
            throw new IllegalArgumentException("owner name is invalid!");
        }
        if (race == null || race.isBlank()) {
            throw new IllegalArgumentException("race is invalid!");
        }
        if (birthDate == null) {
            throw new IllegalArgumentException("birth date is invalid!");
        }
        petRepository.createPet(ownerName, race, birthDate, isVaccinated);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.getAllPets();
    }

    @Override
    public Optional<Pet> findById(int id) {
        return petRepository.findbyId(id);
    }

    @Override
    public void updatePetById(int id, String ownerName, String race, Date birthDate, boolean isVaccinated) {
        if (id <= 0) {
            throw new IllegalArgumentException("id is invalid!");
        }
        if (ownerName == null || ownerName.isBlank()) {
            throw new IllegalArgumentException("owner name is invalid!");
        }
        if (race == null || race.isBlank()) {
            throw new IllegalArgumentException("race is invalid!");
        }
        if (birthDate == null) {
            throw new IllegalArgumentException("birth date is invalid!");
        }
        petRepository.updatePetById(id, ownerName, race, birthDate, isVaccinated);
    }

    @Override
    public void deletePetById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id is invalid!");
        }
        petRepository.deletePetById(id);
    }
}
