package com.sda.baranrolando.petclinic;

import com.sda.baranrolando.petclinic.controller.PetController;
import com.sda.baranrolando.petclinic.controller.VetController;
import com.sda.baranrolando.petclinic.repository.PetRepositoryImpl;
import com.sda.baranrolando.petclinic.repository.VetRepositoryImpl;
import com.sda.baranrolando.petclinic.service.PetServiceImpl;
import com.sda.baranrolando.petclinic.service.VetServiceImpl;
import com.sda.baranrolando.petclinic.utils.SessionManager;
import com.sda.baranrolando.petclinic.utils.UserOption;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        SessionManager.getSessionFactory();
        VetController vetController = new VetController(new VetServiceImpl(new VetRepositoryImpl()));
        PetController petController = new PetController(new PetServiceImpl(new PetRepositoryImpl()));
        UserOption userOption;
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                UserOption.displayAllOptions();
                System.out.print("Please select an option: ");
                int numericOption = Integer.parseInt(scanner.nextLine().trim());
                userOption = UserOption.findByNumericOption(numericOption);
            } catch (NumberFormatException e) {
                userOption = UserOption.UNKNOWN;
            }

            switch (userOption) {
                case ADD_VET:
                    vetController.createVet();
                    break;
                case VIEW_ALL_VETS:
                    vetController.showAllVets();
                    break;
                case VIEW_VET_BY_ID:
                    vetController.showVetById();
                    break;
                case UPDATE_VET_BY_ID:
                    vetController.updateVetById();
                    break;
                case DELETE_VET_BY_ID:
                    vetController.deleteVetById();
                    break;
                case ADD_PET:
                    petController.createPet();
                    break;
                case VIEW_ALL_PETS:
                    petController.showAllPets();
                    break;
                case VIEW_PET_BY_ID:
                    petController.showPetById();
                    break;
                case UPDATE_PET_BY_ID:
                    petController.updatePetById();
                    break;
                case DELETE_PET_BY_ID:
                    petController.deletePetById();
                    break;
                case UNKNOWN:
                    System.err.println("!INVALID OPTION SELECTED!");
                    break;
                case EXIT:
                    System.out.println("Good-bye, have a nice day.");
                    break;
            }
        } while (userOption != UserOption.EXIT);
        SessionManager.shutDown();
    }
}