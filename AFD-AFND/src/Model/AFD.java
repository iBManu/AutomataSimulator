/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;

/**
 *
 * @author manu_
 */
public class AFD implements Proceso, Cloneable{
    
    private int [ ] estadosFinales; //indica cuales son los estados Finales
    private List<TransicionAFD> transiciones; //indica la lista de transiciones del AFD

    public AFD()
    {
    
    }
    
    public void agregarTransicion(int e1, char simbolo, int e2)
    {
    
    }
    
    public int transicion(int estado, char simbolo)
    {
    
        return 0;
    }
    
    public boolean esFinal(int estado)
    {
    
        return false;
    }
    
    public boolean reconocer(String cadena) {
    char[ ] simbolo = cadena.toCharArray();
        int estado = 0 ; //El estado inicial es el 0
        for(int i=0; i<simbolo.length; i++) {
        estado = transicion(estado,simbolo[i]);
    }
    return esFinal(estado);
    }

    public static AFD pedir()
    {
    
        return null;
    }
    
    @Override
    public Object clone()
    {
        
        return null; 
    }
}
