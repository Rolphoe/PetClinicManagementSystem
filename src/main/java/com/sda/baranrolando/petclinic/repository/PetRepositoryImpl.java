package com.sda.baranrolando.petclinic.repository;

import com.sda.baranrolando.petclinic.model.Pet;
import com.sda.baranrolando.petclinic.utils.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PetRepositoryImpl implements PetRepository {
    @Override
    public void createPet(String ownerName, String race, Date birthDate, boolean isVaccinated) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Pet pet = new Pet();
            pet.setOwnerName(ownerName);
            pet.setRace(race);
            pet.setBirthDate(birthDate);
            pet.setVaccinated(isVaccinated);
            session.save(pet);
            transaction.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<Pet> getAllPets() {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            List<Pet> allPets = session.createQuery("FROM Pet", Pet.class).getResultList();
            return allPets;
        }
    }

    @Override
    public Optional<Pet> findbyId(int id) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            Pet pet = session.find(Pet.class, id);
            return Optional.ofNullable(pet);
        }
    }

    @Override
    public void updatePetById(int id, String ownerName, String race, Date birthDate, boolean isVaccinated) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            Pet pet = session.find(Pet.class, id);
            if (pet != null) {
                Transaction transaction = session.beginTransaction();
                try {
                    pet.setOwnerName(ownerName);
                    pet.setRace(race);
                    pet.setBirthDate(birthDate);
                    pet.setVaccinated(isVaccinated);
                    session.saveOrUpdate(pet);
                    transaction.commit();
                } catch (Exception e) {
                    transaction.rollback();
                    throw new IllegalStateException(e);
                }
            } else {
                throw new IllegalArgumentException("!PET ID NOT FOUND IN DATABASE!");
            }
        }
    }

    @Override
    public void deletePetById(int id) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            Pet pet = session.find(Pet.class, id);
            if (pet != null) {
                Transaction transaction = session.beginTransaction();
                try {
                    session.remove(pet);
                    transaction.commit();
                } catch (Exception e) {
                    transaction.rollback();
                    throw new IllegalStateException(e);
                }
            } else {
                throw new IllegalArgumentException("!PET ID NOT FOUND IN DATABASE!");
            }
        }
    }
}
