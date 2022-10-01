package com.sda.baranrolando.petclinic.controller;

import com.sda.baranrolando.petclinic.model.Vet;
import com.sda.baranrolando.petclinic.service.VetService;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class VetController {
    private final VetService vetService;
    private final Scanner scanner = new Scanner(System.in);

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    public void createVet() {
        System.out.print("Please insert vet first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Please insert vet last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Please insert vet address: ");
        String address = scanner.nextLine();
        System.out.print("Please insert vet speciality: ");
        String speciality = scanner.nextLine();
        try {
            vetService.createVet(firstName, lastName, address, speciality);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid data: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Internal server error: " + e.getMessage());
        }
    }

    public void showAllVets(){
        System.out.println("Vet list: ");
        for(Vet vet : vetService.getAllVets()){
            System.out.println(vet.getId() + " " + vet.getFirstName() + " " + vet.getLastName());
        }
    }

    public void showVetById(){
        try{
            System.out.print("Please insert the id of the vet: ");
            int chooseId = scanner.nextInt();
            Optional<Vet> optionalVet = vetService.findById(chooseId);
            if(optionalVet.isPresent()){
                System.out.println("Vet result: ");
                System.out.println(optionalVet.get());
            } else {
                System.out.println("Vet not found!");
            }
        }catch(InputMismatchException e){
            System.out.println("!INVALID ID FORMAT!");
        }catch(Exception e){
            System.out.println("Internal server error!" + e.getMessage());
        }
    }
}
