package services;

import dataaccess.CategoryDB;
import models.Category;
import java.util.List;

public class CategoryService {
    private CategoryDB categoryDB = new CategoryDB();
    
    public List<Category> getAll() throws Exception {
        List<Category> categories = this.categoryDB.getAll();
        return categories;
    }
    
    public void insert(Integer id, String name) throws Exception {
        Category category = new Category(id, name);
        this.categoryDB.insert(category);
    }
    
    public void update(Integer id, String name) throws Exception {
        Category category = new Category(id, name);
        this.categoryDB.update(category);
    }
}
