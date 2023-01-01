/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manu_
 */
public class AFD implements Proceso, Cloneable{
    
    private int estadoInicial;
    private List<Integer> estadosFinales; //indica cuales son los estados Finales
    private List<TransicionAFD> transiciones; //indica la lista de transiciones del AFD
    private List<Integer> estados;

    public AFD()
    {
        transiciones = new ArrayList<TransicionAFD>();
        estadosFinales = new ArrayList<Integer>();
        estados = new ArrayList<Integer>();
    }
    
    public void agregarTransicion(int e1, char simbolo, int e2)
    {
        transiciones.add(new TransicionAFD(e1,e2,simbolo));
        if(estados.isEmpty())
        {
            estados.add(e1);
            estados.add(e2);
        }
        else if(!estados.contains(e1))
            estados.add(e1);
        else if(!estados.contains(e2))
            estados.add(e2);
    }
    
    public void agregarFinal(int e)
    {
        estadosFinales.add(e);
    }
    
    public void setInicial(int e)
    {
        this.estadoInicial = e;
    }
    
    public int transicion(int estado, char simbolo)
    {
        for(int i = 0; i < transiciones.size(); i++)
        {   
            if(transiciones.get(i).getInitState() == estado && transiciones.get(i).getSymbol() == simbolo)
            {
                return transiciones.get(i).getEndState();
            }
        }
        return -1;
    }
    
    public boolean esFinal(int estado)
    {
        if(estadosFinales.contains(estado))
            return true;
        else
            return false;
    }
    
    public boolean reconocer(String cadena) {
      char [] simbolo = cadena.toCharArray();   
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
        Object o = null;
        
        try 
        {
            o = super.clone();
        }
        catch (CloneNotSupportedException ex)
        {
            Logger.getLogger(AFD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0; 
    }

    public int getEstadoInicial() {
        return estadoInicial;
    }

    public List<Integer> getEstadosFinales() {
        return estadosFinales;
    }

    public List<TransicionAFD> getTransiciones() {
        return transiciones;
    }

    public List<Integer> getEstados() {
        return estados;
    }
}
