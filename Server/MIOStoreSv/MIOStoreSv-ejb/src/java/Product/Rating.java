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
public class Rating {
    private int id;
    private int star;
    private int numberVote;

    public Rating() {
    }

    public Rating(int id, int star, int numberVote) {
        this.id = id;
        this.star = star;
        this.numberVote = numberVote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getNumberVote() {
        return numberVote;
    }

    public void setNumberVote(int numberVote) {
        this.numberVote = numberVote;
    }

   
   
}
