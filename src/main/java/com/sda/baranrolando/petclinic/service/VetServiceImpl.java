package com.sda.baranrolando.petclinic.service;

import com.sda.baranrolando.petclinic.model.Vet;
import com.sda.baranrolando.petclinic.repository.VetRepository;

import java.util.List;

public class VetServiceImpl implements VetService {
    private final VetRepository vetRepository;

    public VetServiceImpl(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public void createVet(String firstName, String lastName, String address, String speciality) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("first name is invalid!");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("last name is invalid!");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("address is invalid!");
        }
        if (speciality == null || speciality.isBlank()) {
            throw new IllegalArgumentException("speciality is invalid!");
        }
        vetRepository.createVet(firstName, lastName, address, speciality);
    }

    @Override
    public List<Vet> getAllVets() {
        return vetRepository.getAllVets();
    }

}
