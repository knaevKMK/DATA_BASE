import entities.Town;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Locale;

public class HomeWork {

    public static void EX_2(EntityManager entityManager) {
        List<Town> towns = entityManager
                .createQuery("SELECT t FROM Town t WHERE length(t .name)<=5", Town.class)
                .getResultList();

        entityManager.getTransaction().begin();
        towns.forEach(entityManager::detach);
        towns.forEach(t -> t.setName(t.getName().toLowerCase()));
        towns.forEach(entityManager::merge);
        entityManager.getTransaction().commit();
    }
}
