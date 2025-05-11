package org.example;

import entities.Continent;
import entities.Country;
import repository.ContinentRepository;
import repository.CountryRepository;
import util.PersistenceUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CountryRepository countryRepo = new CountryRepository();
        ContinentRepository continentRepo = new ContinentRepository();

        Continent Australia = new Continent();
        Australia.setName("Australia");
        continentRepo.create(Australia);


        Country greece = new Country();
        greece.setName("Greece");
        greece.setCode("Gr");
        greece.setContinent("Australia");
        countryRepo.create(greece);

        Country foundById = countryRepo.findById(greece.getId());
        System.out.println("Found by ID: " + foundById);

        List<Country> foundByName = countryRepo.findByName("Greec");
        System.out.println("Found by name:");
        foundByName.forEach(System.out::println);

        PersistenceUtil.close();
    }
}
