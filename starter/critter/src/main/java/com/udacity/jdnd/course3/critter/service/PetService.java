package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PetService {
    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Pet getPetById(Long petId) {
        return petRepository.getOne(petId);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public List<Pet> getPetsByCustomerId(long customerId) {
        return petRepository.findPetByCustomerId(customerId);
    }

    public Pet savePet(Pet pet, Long customerId) {
        Customer customer = customerRepository.getOne(customerId);
        pet.setCustomer(customer);
        pet = petRepository.save(pet);

        List<Pet> pets = new ArrayList<>();
        pets.add(pet);
        customer.setPets(pets);

        customerRepository.save(customer);
        return pet;
    }
}
