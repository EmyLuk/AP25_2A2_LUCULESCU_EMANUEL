package repository;

import entities.Continent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.PersistenceUtil;

import java.util.List;

public class ContinentRepository {
    private final EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();

    public void create(Continent continent) {
        if (findByName(continent.getName()).isEmpty()) {
            em.getTransaction().begin();
            em.persist(continent);
            em.getTransaction().commit();
        } else {
            System.out.println("Continent already exists: " + continent.getName());
        }
    }



    public List<Continent> findByName(String name) {
        TypedQuery<Continent> query = em.createNamedQuery("Continent.findByName", Continent.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }
}
