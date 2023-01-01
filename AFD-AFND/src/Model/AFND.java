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
public class AFND implements Proceso, Cloneable{
    
    private int [ ] estadosFinales; //indica cuales son los estados Finales
    private List<TransicionAFND> transiciones; //indica la lista de transiciones del AFND
    private List<TransicionLambda> transicionesλ; //indica la lista de transiciones λ del AFND

    public AFND()
    {
    
    }
    
    public void agregarTransicion(int e1, char simbolo, int [ ] e2)
    {
        
    }
    
    public void agregarTransicionLambda(int e1, int [ ] e2)
    {
    
    }
    
    private int [ ] transicion(int estado, char simbolo)
    {
    
        return null;  
    }
    
    public int [ ] transicion(int [ ] macroestado, char simbolo)
    {
    
        return null;   
    }
    
    public int [ ] transicionLambda (int estado)
    {
    
        return null;  
    }
    
    public boolean esFinal(int estado)
    {
        
        return false;        
    }
    public boolean esFinal(int [ ] macroestado)
    {
        
        return false; 
    }
    
    private int[ ] lambda_clausura(int[ ] macroestado)
    {
    
        return null;
    }
    
    public boolean reconocer(String cadena) {
        
        char[ ] simbolo = cadena.toCharArray();
        int [ ] estado = { 0 }; //El estado inicial es el 0
        int[ ] macroestado = lambda_clausura(estado);
        for(int i=0; i<simbolo.length; i++) {
            macroestado = transicion(macroestado, simbolo[i]);
        }   
    
    return esFinal(macroestado);
    }

    public static AFND pedir()
    {
        
        return null; 
    }
    
    @Override
    public Object clone()
    {
        
        return null; 
    }
    
}

