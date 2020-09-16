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
public class Promotion {
    private int id;
    private String promotionName;
    private Double pricePromotion;

    public Promotion() {
    }

    public Promotion(int id, String promotionName, Double pricePromotion) {
        this.id = id;
        this.promotionName = promotionName;
        this.pricePromotion = pricePromotion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public Double getPricePromotion() {
        return pricePromotion;
    }

    public void setPricePromotion(Double pricePromotion) {
        this.pricePromotion = pricePromotion;
    }

    

    

  
 
    
}
