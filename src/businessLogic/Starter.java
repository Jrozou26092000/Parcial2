/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import Data.Cat;
import Data.Dog;
import Data.Hamster;
import Data.Person;
import Data.Pet;
import Data.Vet;
import GUI.GUI;
import ProgramException.ProgramException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jdk.nashorn.internal.parser.Lexer;

/**
 *
 * @author jrozou
 */
public class Starter {
    private ArrayList<Person> dueños;

    public Starter() {
        this.dueños = new ArrayList<>();
    }

    public static void main(String[] args) {
        int opcion;
        GUI gui = new GUI();
        File file = new File("pets.txt");
        Vet v = new Vet();
        Starter s = new Starter();
        while(true){
            try{
                opcion = gui.MostrarMenu();
                switch(opcion){
                    case 1: 
                        s.Leer(v,file);    
                        break;
                    case 2: 
                        s.Guardar(file);
                        break;
                    case 3:
                        s.removerMacota();
                        s.Guardar(file);
                        break;
                    case 4: 
                        s.ListarTipo();
                        break;
                    case 5: 
                        s.Listar();
                        break;
                    case 6: 
                        s.Registrar();
                        break;
                    default:break;
                }
            }catch(ProgramException p){
                System.out.println(p.getMessage());
            }catch(FileNotFoundException f){
                System.out.println("No se encontró el archivo...");
            }
        }
                
    }
    
    public void Leer(Vet v,File file) throws FileNotFoundException{
        System.out.println("Leyendo...");
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine()) {
            String tipo = sc.next();
            if (tipo.equals("Dog")) {
                String id = sc.next();
                String nombre = sc.next();
                String hair = sc.next();
                String breed = sc.next();
                Pet p = new Dog(breed, id, nombre, hair);
                
                sc.nextLine();
                String name = sc.next();
                String identificacion = sc.next();
                Person dueño = new Person(name, identificacion);
                boolean eleccion = true;
                for (int i = 0; i < this.dueños.size(); i++) {
                    if ((this.dueños.get(i).getName().equals(name))&&
                            (this.dueños.get(i).getId().equals(identificacion))) {
                        this.dueños.get(i).addPet(p);
                        eleccion = false;
                    }
                }
                if (eleccion) {
                    dueño.addPet(p);
                    this.dueños.add(dueño);
                }
            }else if (tipo.equals("Cat")) {
                String id = sc.next();
                String nombre = sc.next();
                String hair = sc.next();
                boolean isHamster = sc.nextBoolean();
                Cat c = new Cat(isHamster, id, nombre, hair);
                
                sc.nextLine();
                String name = sc.next();
                String identificacion = sc.next();
                Person dueño = new Person(name, identificacion);
                boolean eleccion = true;
                for (int i = 0; i < this.dueños.size(); i++) {
                    if ((this.dueños.get(i).getName().equals(name))&&
                            (this.dueños.get(i).getId().equals(identificacion))) {
                        this.dueños.get(i).addPet(c);
                        eleccion = false;
                    }
                }
                if (eleccion) {
                    dueño.addPet(c);
                    this.dueños.add(dueño);
                }
            }else if (tipo.equals("Hamster")) {
                String id = sc.next();
                String nombre = sc.next();
                String hair = sc.next();
                double weight = sc.nextDouble();
                Hamster h = new Hamster(weight, id, nombre, hair);
                
                sc.nextLine();
                String name = sc.next();
                String identificacion = sc.next();
                Person dueño = new Person(name, identificacion);
                boolean eleccion = true;
                for (int i = 0; i < this.dueños.size(); i++) {
                    if ((this.dueños.get(i).getName().equals(name))&&
                            (this.dueños.get(i).getId().equals(identificacion))) {
                        this.dueños.get(i).addPet(h);
                        eleccion = false;
                    }
                }
                if (eleccion) {
                    dueño.addPet(h);
                    this.dueños.add(dueño);
                }
            }
        }
        sc.close();
    }
    
    public void Guardar(File file) throws FileNotFoundException{
        System.out.println("Guardando...");
        PrintWriter pw = new PrintWriter(file);
        for (int i = 0; i < this.dueños.size(); i++) {
            for (int j = 0; j < this.dueños.get(i).getMascotas().size(); j++) {
                if (this.dueños.get(i).getMascotas().get(j).getClass().getName().endsWith("Data.Dog")) {
                    //pw.println("-------------------");
                    Dog d = (Dog)this.dueños.get(i).getMascotas().get(j);
                    pw.println("Dog"+" "+d.getId()+" "+d.getName()+" "+d.getHairColor()+" "+d.getBreed());
                    pw.println(this.dueños.get(i).getName()+" "+this.dueños.get(i).getId());
                }if (this.dueños.get(i).getMascotas().get(j).getClass().getName().endsWith("Data.Cat")) {
                    //pw.println("-------------------");
                    Cat c = (Cat)this.dueños.get(i).getMascotas().get(j);
                    pw.println("Cat"+" "+c.getId()+" "+c.getName()+" "+c.getHairColor()+" "+c .isIsHunter());
                    pw.println(this.dueños.get(i).getName()+" "+this.dueños.get(i).getId());
                }if (this.dueños.get(i).getMascotas().get(j).getClass().getName().endsWith("Data.Hamster")) {
                    //pw.println("-------------------");
                    Hamster h = (Hamster)this.dueños.get(i).getMascotas().get(j);
                    pw.println("Hamster"+" "+h.getId()+" "+h.getName()+" "+h.getHairColor()+" "+h.getWeight());
                    pw.println(this.dueños.get(i).getName()+" "+this.dueños.get(i).getId());
                }
            }
        }
        pw.flush();
        pw.close();
    }
    
    public void removerMacota(){
        Scanner leer = new Scanner(System.in);
        System.out.println("Identificacion de la mascota: ");
        String id = leer.next();
        for (int i = 0; i < this.dueños.size(); i++) {
            for (int j = 0; j < this.dueños.get(i).getMascotas().size(); j++) {
                if (this.dueños.get(i).getMascotas().get(j).getId().equals(id)) {
                    this.dueños.get(i).removePet(this.dueños.get(i).getMascotas().get(j));
                }
            }
        }
    }
    
    public void ListarTipo() throws ProgramException{
        Scanner leer = new Scanner(System.in);
        System.out.println("Tipo de macotas que desea listar...");
        System.out.println("1-Dog\n2-Cat\n3-Hamster");
        int opcion = leer.nextInt();
        if ((opcion<1)||(opcion>3)) {
            throw new ProgramException("Opción inválida...");
        }
        String tipo = null;
        switch(opcion){
            case 1:
                tipo = "Data.Dog";
                break;
            case 2:
                tipo = "Data.Cat";
                break;
            case 3:
                tipo = "Data.Hamster";
                break;
            default:break;
        }
               
        for (int i = 0; i < this.dueños.size(); i++) {
            for (int j = 0; j < this.dueños.get(i).getMascotas().size(); j++) {
                if (this.dueños.get(i).getMascotas().get(j).getClass().getName().equals(tipo)) {
                    if (tipo.equals("Data.Dog")) {
                        Dog d = (Dog) this.dueños.get(i).getMascotas().get(j);
                        System.out.println(d.getId()+" "+d.getName()+" "+d.getHairColor()+" "+d.getBreed());
                    }else if (tipo.equals("Data.Cat")) {
                        Cat c = (Cat) this.dueños.get(i).getMascotas().get(j);
                        System.out.println(c.getId()+" "+c.getName()+" "+c.getHairColor()+" "+c.isIsHunter());
                    }else if (tipo.equals("Data.Hamster")) {
                        Hamster h = (Hamster) this.dueños.get(i).getMascotas().get(j);
                        System.out.println(h.getId()+" "+h.getName()+" "+h.getHairColor()+" "+h.getWeight());
                    }
                }
            }
        }
    }
    
    public void Listar(){
        for (int i = 0; i < this.dueños.size(); i++) {
            for (int j = 0; j < this.dueños.get(i).getMascotas().size(); j++) {
                System.out.println(this.dueños.get(i).getMascotas().get(j).getName()+" "+
                        this.dueños.get(i).getMascotas().get(j).getId());
                /*if (this.dueños.get(i).getMascotas().getClass().getName().equals("Data.Dog")) {
                    Dog d = (Dog) this.dueños.get(i).getMascotas().get(j);
                    System.out.println(d.getId()+" "+d.getName()+" "+d.getHairColor()+" "+d.getBreed());
                }else if (this.dueños.get(i).getMascotas().getClass().getName().equals("Data.Cat")) {
                    Cat c = (Cat) this.dueños.get(i).getMascotas().get(j);
                        System.out.println(c.getId()+" "+c.getName()+" "+c.getHairColor()+" "+c.isIsHunter());
                }else if (this.dueños.get(i).getMascotas().getClass().getName().equals("Data.Hamster")) {
                    Hamster h = (Hamster) this.dueños.get(i).getMascotas().get(j);
                        System.out.println(h.getId()+" "+h.getName()+" "+h.getHairColor()+" "+h.getWeight());
                }*/
            }
        }
    }
    
    public void Registrar() throws ProgramException{
        Scanner leer = new Scanner(System.in);
        System.out.println("Nombre del dueño: ");
        String name = leer.next();
        System.out.println("Identificacion: ");
        String identificador = leer.next();
        System.out.println("Tipo de mascota que desea registar: ");
        System.out.println("1-Dog\n2-Cat\n3-Hamster");
        int opcion = leer.nextInt();
        if ((opcion<1)||(opcion>3)) {
            throw new ProgramException("Opción inválida...");
        }
        if (opcion ==1) {
            boolean eleccion = true;
            System.out.println("Id: ");
            String id = leer.next();
            System.out.println("Nombre: ");
            String nombre = leer.next();
            System.out.println("Color de pelo: ");
            String color = leer.next();
            System.out.println("Raza: ");
            String raza = leer.next();
            Dog d = new Dog(raza, id, name, color);
            for (int i = 0; i < this.dueños.size(); i++) {
                if ((this.dueños.get(i).getName().equals(id))&&(this.dueños.get(i).getName().equals(name))) {
                    this.dueños.get(i).addPet(d);
                    eleccion = false;
                }
            }
            if (eleccion) {
                Person p = new Person(nombre, identificador);
                p.addPet(d);
                this.dueños.add(p);
            }
        }else if (opcion ==2) {
            boolean eleccion = true;
            System.out.println("Id: ");
            String id = leer.next();
            System.out.println("Nombre: ");
            String nombre = leer.next();
            System.out.println("Color de pelo: ");
            String color = leer.next();
            System.out.println("Es un Hamster: ");
            boolean esHamster = leer.nextBoolean();
            Cat c = new Cat(esHamster, id, name, color);
            for (int i = 0; i < this.dueños.size(); i++) {
                if ((this.dueños.get(i).getName().equals(id))&&(this.dueños.get(i).getName().equals(name))) {
                    this.dueños.get(i).addPet(c);
                    eleccion = false;
                }
            }
            if (eleccion) {
                Person p = new Person(nombre, identificador);
                p.addPet(c);
                this.dueños.add(p);
            }
        }else if (opcion ==3) {
            boolean eleccion = true;
            System.out.println("Id: ");
            String id = leer.next();
            System.out.println("Nombre: ");
            String nombre = leer.next();
            System.out.println("Color de pelo: ");
            String color = leer.next();
            System.out.println("Peso: ");
            double peso = leer.nextDouble();
            Hamster h = new Hamster(peso, id, name, color);
            for (int i = 0; i < this.dueños.size(); i++) {
                if ((this.dueños.get(i).getName().equals(id))&&(this.dueños.get(i).getName().equals(name))) {
                    this.dueños.get(i).addPet(h);
                    eleccion = false;
                }
            }
            if (eleccion) {
                Person p = new Person(nombre, identificador);
                p.addPet(h);
                this.dueños.add(p);
            }
        }
    }
    public boolean addDueño(Person d){
        return this.dueños.add(d);
    }

    public ArrayList<Person> getDueños() {
        return dueños;
    }
    
    
}
