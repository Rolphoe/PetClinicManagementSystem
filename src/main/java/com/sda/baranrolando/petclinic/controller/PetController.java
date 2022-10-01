package com.sda.baranrolando.petclinic.controller;

import com.sda.baranrolando.petclinic.model.Pet;
import com.sda.baranrolando.petclinic.service.PetService;

import java.text.ParseException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class PetController {
    private final PetService petService;
    private final Scanner scanner = new Scanner(System.in);

    public PetController(PetService petService) {
        this.petService = petService;
    }

    public void createPet() throws ParseException {
        System.out.print("Please insert pet owner name: ");
        String ownerName = scanner.nextLine();
        System.out.print("Please insert pet race: ");
        String race = scanner.nextLine();
        System.out.print("Please insert pet date of birth (year/month/day): ");
        String dateString = scanner.next();
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        Date birthDate = formatter.parse(dateString);
        System.out.print("Is the pet vaccinated true/false: ");
        boolean isVaccinated = scanner.nextBoolean();
        try {
            petService.createPet(ownerName, race, birthDate, isVaccinated);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid data: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Internal server error: " + e.getMessage());
        }
    }

    public void showAllPets() {
        System.out.println("Pet list: ");
        for (Pet pet : petService.getAllPets()) {
            System.out.println(pet.getId() + " " + pet.getOwnerName() + " " + pet.getRace());
        }
    }

    public void showPetById() {
        try {
            System.out.print("Please insert the id of the pet: ");
            int chooseId = scanner.nextInt();
            Optional<Pet> optionalPet = petService.findById(chooseId);
            if (optionalPet.isPresent()) {
                System.out.println("Pet result: " + optionalPet.get());
            } else {
                System.out.println("Pet not found!");
            }
        } catch (InputMismatchException e) {
            System.err.println("!INVALID ID FORMAT!");
        } catch (Exception e) {
            System.err.println("Internal server error!" + e.getMessage());
        }
    }

    public void updatePetById() {
        try {
            System.out.print("Please insert the id: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Please insert pet owner name: ");
            String ownerName = scanner.nextLine();
            System.out.print("Please insert pet race: ");
            String race = scanner.nextLine();
            System.out.print("Please insert pet date of birth (year/month/day): ");
            String dateString = scanner.next();
            DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

            Date birthDate = formatter.parse(dateString);
            System.out.print("Is the pet vaccinated true/false: ");
            boolean isVaccinated = scanner.nextBoolean();

            petService.updatePetById(id, ownerName, race, birthDate, isVaccinated);
        } catch (NumberFormatException e) {
            System.err.println("!INVALID ID FORMAT!");
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid data: " + e.getMessage());
        } catch(ParseException e) {
            System.err.println("Invalid date format!" + e.getMessage());
        }catch (Exception e) {
            System.err.println("Internal server error: " + e.getMessage());
        }
    }

    public void deletePetById() {
        try {
            System.out.println("Please insert the id: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            petService.deletePetById(id);
        } catch (NumberFormatException e) {
            System.err.println("!INVALID ID FORMAT!");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Internal server error: " + e.getMessage());
        }
    }
}
