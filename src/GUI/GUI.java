/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ProgramException.ProgramException;
import java.util.Scanner;

/**
 *
 * @author jrozou
 */
public class GUI {
    public int MostrarMenu()throws ProgramException{
        Scanner leer = new Scanner(System.in);
        System.out.println("--------Bienvenido a la veterinaria-----------");
        System.out.println("1-Leer\n2-Guardar\n3-Remover mascota\n4-Listar mascotas (tipo)"
                + "\n5-Listar mascotas\n6-Registrar");
        int opcion = leer.nextInt();
        if ((opcion<1)||(opcion>6)) {
            throw new ProgramException("Opcion inválida...");
        }
        return opcion;
    }
}
