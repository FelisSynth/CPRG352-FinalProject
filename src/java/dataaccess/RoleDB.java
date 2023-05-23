package dataaccess;

import models.Role;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

public class RoleDB {
    public List<Role> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Role> roles = new ArrayList<>();
        roles = em.createNamedQuery("Role.findAll", Role.class).getResultList();

        return roles;
    }
    public Role get(Integer id) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Role role = em.find(Role.class, id);
        return role;
    }

}
