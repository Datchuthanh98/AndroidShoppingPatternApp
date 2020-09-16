/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product;

/**
 *
 * @author admin
 */
public class Categorie {
    private int id;
    private String name;
    private  String parent;

    public Categorie() {
    }

    public Categorie(int id, String name, String Parent) {
        this.id = id;
        this.name = name;
        this.parent = Parent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String Parent) {
        this.parent = Parent;
    }
    
}
