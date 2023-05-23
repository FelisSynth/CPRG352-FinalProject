package services;

import dataaccess.ItemDB;
import models.Item;
import java.util.List;
import models.Category;
import models.User;

public class ItemService {
    private ItemDB itemDB = new ItemDB();
    
    public Item get(Integer id) throws Exception {
        Item item = this.itemDB.get(id);
        return item;
    }
    
    public List<Item> getAll() throws Exception {
        List<Item> items = this.itemDB.getAll();
        return items;
    }
    
    public List<Item> getByOwner(User owner) throws Exception {
        List<Item> items = this.itemDB.getByOwner(owner);
        return items;
    }
    
    public void insert(Integer id, Category category, String name, double price, User owner) throws Exception {
        Item item = new Item(id, name, price);
        item.setCategory(category);
        item.setOwner(owner);
        this.itemDB.insert(item);
    }
    
    public void update(Integer id, Category category, String name, double price, User owner) throws Exception {
        Item item = new Item(id, name, price);
        item.setCategory(category);
        item.setOwner(owner);
        this.itemDB.update(item);
    }
    
    public void delete(Integer id) throws Exception {
        Item item = new Item();
        item = itemDB.get(id);
        this.itemDB.delete(item);
    }
    
}
