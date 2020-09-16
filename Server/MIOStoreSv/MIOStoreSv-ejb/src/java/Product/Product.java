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
public class Product {
    private int id;
    private String name;
    private Double priceOrigin;
    private Categorie categories;
    private Image image;
    private Producer producer;
    private Rating rating;
    private Promotion promotion;

    public Product() {
    }

    public Product(int id, String name, Double PriceOrigin, Categorie categories, Image image, Producer producer, Rating rating, Promotion promotion) {
        this.id = id;
        this.name = name;
        this.priceOrigin = PriceOrigin;
        this.categories = categories;
        this.image = image;
        this.producer = producer;
        this.rating = rating;
        this.promotion = promotion;
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

    public Double getPriceOrigin() {
        return priceOrigin;
    }

    public void setPriceOrigin(Double PriceOrigin) {
        this.priceOrigin = PriceOrigin;
    }

    public Categorie getCategories() {
        return categories;
    }

    public void setCategories(Categorie categories) {
        this.categories = categories;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

   
    
    
}
