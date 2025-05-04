package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            Database.getConnection().createStatement().execute("DELETE FROM countries");
            Database.getConnection().createStatement().execute("DELETE FROM continents");
        } catch (Exception ignored) {}

        ContinentDAO continentDAO = new ContinentDAO();
        CountryDAO countryDAO = new CountryDAO();

        continentDAO.create("Europe");
        continentDAO.create("Asia");
        continentDAO.create("Africa");
        continentDAO.create("North America");
        continentDAO.create("South America");

        int europeId = continentDAO.findIdByName("Europe");
        int asiaId = continentDAO.findIdByName("Asia");
        int africaId = continentDAO.findIdByName("Africa");
        int naId = continentDAO.findIdByName("North America");
        int saId = continentDAO.findIdByName("South America");

        countryDAO.create("Romania", "RO", europeId);
        countryDAO.create("Germany", "DE", europeId);
        countryDAO.create("China", "CN", asiaId);
        countryDAO.create("Japan", "JP", asiaId);
        countryDAO.create("Egypt", "EG", africaId);
        countryDAO.create("Canada", "CA", naId);
        countryDAO.create("Brazil", "BR", saId);


        System.out.println(countryDAO.findByName("Romania"));
        System.out.println(countryDAO.findByName("Germany"));
        System.out.println(countryDAO.findByName("China"));
        System.out.println(countryDAO.findByName("Brazil"));
        System.out.println(continentDAO.findByName("Europe"));
        System.out.println(continentDAO.findByName("Asia"));
        System.out.println(continentDAO.findByName("Africa"));
        System.out.println(continentDAO.findByName("North America"));
        System.out.println(continentDAO.findByName("South America"));


        Database.closeConnection();
    }
}
