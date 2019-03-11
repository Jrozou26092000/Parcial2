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
public class Person {
    private String name;
    private String id;
    private ArrayList<Pet> mascotas;

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
        this.mascotas = new ArrayList<>();
    }
    
    public void showPets(){
        /*for (int i = 0; i < this.mascotas.size(); i++) {
            
        }*/
    }
    
    public boolean addPet(Pet p){
        return this.mascotas.add(p);
    }
    public boolean removePet(Pet p){
        return this.mascotas.remove(p);
    }
    public ArrayList<Pet> getMascotas() {
        return mascotas;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
    
    
    
}
