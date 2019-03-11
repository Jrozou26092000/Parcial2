/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.ArrayList;

/**
 *
 * @author jrozou
 */
public class Vet {
    private ArrayList<Pet> pets;

    public Vet() {
        this.pets = new ArrayList<>();
    }
    
    public boolean removePets(Pet p){
        return this.pets.remove(p);
    }
    
    public void showPetsByType(){
        
    }
    public void showPets(){
        
    }
    
}
