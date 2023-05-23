package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Item;
import models.User;


public class ItemDB {
    public List<Item> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Item> items = em.createNamedQuery("Item.findAll", Item.class).getResultList();
        return items;
    }
    public List<Item> getByOwner(User owner) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Item> items = em.createNamedQuery("Item.findByOwner", Item.class).setParameter("owner", owner).getResultList();
        return items;
    }

    public Item get(Integer id) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Item item = em.find(Item.class, id);
        return item;
    }

    public void insert(Item item) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(item);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }     
    }

    public void update(Item item) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(item);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        } 
    }

    public void delete(Item item) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(em.merge(item));
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        } 
    }
}
