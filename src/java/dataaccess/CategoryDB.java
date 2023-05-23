package dataaccess;

import models.Category;
import java.util.ArrayList;
import javax.persistence.EntityTransaction;
import java.util.List;
import javax.persistence.EntityManager;

public class CategoryDB {
    public List<Category> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Category> categories = new ArrayList<>();
        categories = em.createNamedQuery("Category.findAll", Category.class).getResultList();

        return categories;
    }
    
    public void insert(Category category) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(category);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }     
    }
    
    public void update(Category category) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(category);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        } 
    }
    
}
