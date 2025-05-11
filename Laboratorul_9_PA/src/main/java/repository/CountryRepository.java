package repository;

import entities.Country;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.PersistenceUtil;

import java.util.List;

public class CountryRepository {
    private final EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();

    public void create(Country country) {
        em.getTransaction().begin();
        em.persist(country);
        em.getTransaction().commit();
    }

    public Country findById(int id) {
        return em.find(Country.class, id);
    }

    public List<Country> findByName(String name) {
        TypedQuery<Country> query = em.createNamedQuery("Country.findByName", Country.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }
}
