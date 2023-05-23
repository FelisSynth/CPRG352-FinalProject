package services;

import dataaccess.RoleDB;
import models.Role;
import java.util.List;

public class RoleService {
    private RoleDB roleDB = new RoleDB();
    
    public List<Role> getAll() throws Exception {
        List<Role> roles = this.roleDB.getAll();
        return roles;
    }
    public Role get(Integer id) throws Exception {
        Role role = this.roleDB.get(id);
        return role;
    }
}
