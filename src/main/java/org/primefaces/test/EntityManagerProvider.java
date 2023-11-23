package org.primefaces.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProvider {

    private static List<String> CITIES = Arrays.asList(
            "New York", "Los Angeles", "Chicago", "Houston", "Phoenix",
            "Philadelphia", "San Antonio", "San Diego", "Dallas", "San Jose",
            "Austin", "Jacksonville", "San Francisco", "Columbus", "Indianapolis",
            "Fort Worth", "Charlotte", "Seattle", "Denver", "El Paso",
            "Detroit", "Washington", "Boston", "Memphis", "Nashville",
            "Portland", "Oklahoma City", "Las Vegas", "Baltimore", "Louisville",
            "Milwaukee", "Albuquerque", "Tucson", "Fresno", "Sacramento",
            "Kansas City", "Long Beach", "Mesa", "Atlanta", "Colorado Springs",
            "Virginia Beach", "Raleigh", "Omaha", "Miami", "Oakland",
            "Minneapolis", "Tulsa", "Wichita", "New Orleans", "Arlington",
            "Cleveland", "Bakersfield", "Tampa", "Aurora", "Honolulu",
            "Anaheim", "Santa Ana", "St. Louis", "Riverside", "Corpus Christi",
            "Pittsburgh", "Lexington", "Anchorage", "Stockton", "Cincinnati",
            "St. Paul", "Toledo", "Newark", "Greensboro", "Plano",
            "Henderson", "Lincoln", "Buffalo", "Fort Wayne", "Jersey City",
            "Chula Vista", "Orlando", "St. Petersburg", "Norfolk", "Chandler",
            "Laredo", "Madison", "Durham", "Lubbock", "Winston-Salem",
            "Garland", "Glendale", "Hialeah", "Reno", "Baton Rouge",
            "Irvine", "Chesapeake", "Irving", "Scottsdale", "North Las Vegas",
            "Gilbert", "Wichita Falls", "St. Andrews", "Perth", "Sydney"
    );

    private EntityManagerFactory emf;

    @PostConstruct
    public void init() {
        emf = Persistence.createEntityManagerFactory("ExampleDatasource");
        insertData();
    }

    @Produces
    @RequestScoped
    public EntityManager produce() {
        return emf.createEntityManager();
    }

    public void disposes(@Disposes EntityManager em) {
        em.close();
    }

    private void insertData() {
        EntityManager em = produce();
        em.getTransaction().begin();

        long id = 1L;
        for (String city: CITIES) {
            em.persist(TestJpa.builder().id(id).stringCol(city).build());
            id++;
        }


        em.getTransaction().commit();
    }
}
