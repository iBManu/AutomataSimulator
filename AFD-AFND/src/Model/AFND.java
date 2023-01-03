/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manu_
 */
public class AFND implements Proceso, Cloneable {

    private List<Integer> estadosFinales; //indica cuales son los estados Finales
    private int estadoInicial;
    private List<TransicionAFND> transiciones; //indica la lista de transiciones del AFND
    private List<TransicionLambda> transicionesLambda; //indica la lista de transiciones λ del AFND
    ArrayList<Integer> estados;

    public AFND() {
        estadosFinales = new ArrayList<Integer>();
        estadoInicial = 0;
        transiciones = new ArrayList<TransicionAFND>();
        transicionesLambda = new ArrayList<TransicionLambda>();
        estados = new ArrayList<Integer>();
    }

    public void agregarTransicion(int e1, char simbolo, int[] e2) {
        transiciones.add(new TransicionAFND(e1, simbolo, e2));
        if (estados.isEmpty()) {
            estados.add(e1);
        }
        for (int i = 0; i < e2.length; i++) {
            if (!estados.contains(e2[i])) {
                estados.add(e2[i]);
            }
        }
    }

    public void agregarTransicionLambda(int e1, int[] e2) {
        transicionesLambda.add(new TransicionLambda(e1, e2));
        if (estados.isEmpty()) {
            estados.add(e1);
        }
        for (int i = 0; i < e2.length; i++) {
            if (!estados.contains(e2[i])) {
                estados.add(e2[i]);
            }
        }
    }

    private int[] transicion(int estado, char simbolo) {
        ArrayList temp = new ArrayList<Integer>();
        for (int i = 0; i < transiciones.size(); i++) {
            if (transiciones.get(i).getInitState() == estado && transiciones.get(i).getSymbol() == simbolo) {
                temp.add(transiciones.get(i).getEndState());
            }
        }
        int[] arrayFinal = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            arrayFinal[i] = (int) temp.get(i);
        }
        return arrayFinal;
    }

    public int[] transicion(int[] macroestado, char simbolo) {
        ArrayList temp = new ArrayList<Integer>();
        for (int i = 0; i < macroestado.length; i++) {
            for (int j = 0; j < transiciones.size(); j++) {
                if (macroestado[i] == transiciones.get(j).getInitState() && simbolo == transiciones.get(j).getSymbol()) {
                    temp.add(transiciones.get(i).getEndState());
                }
            }
        }
        for (int i = 0; i < macroestado.length; i++) {
            for (int j = 0; j < transicionesLambda.size(); j++) {
                if (macroestado[i] == transiciones.get(j).getInitState()) {
                    temp.add(transiciones.get(i).getEndState());
                }
            }
        }
        int[] arrayFinal = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            arrayFinal[i] = (int) temp.get(i);
        }
        return arrayFinal;
    }

    public int[] transicionLambda(int estado) {
        ArrayList temp = new ArrayList<Integer>();
        for (int i = 0; i < transicionesLambda.size(); i++) {
            if (transicionesLambda.get(i).getInitState() == estado) {
                temp.add(transicionesLambda.get(i).getEndState());
            }
        }
        int[] arrayFinal = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            arrayFinal[i] = (int) temp.get(i);
        }
        return arrayFinal;
    }

    public boolean esFinal(int estado) {
        if (estadosFinales.contains(estado)) {
            return true;
        }
        return false;
    }

    public boolean esFinal(int[] macroestado) {
        for (int i = 0; i < macroestado.length; i++) {
            if (estadosFinales.contains(macroestado[i])) {
                return true;
            }
        }
        return false;
    }

    private int[] lambda_clausura(int[] macroestado) {
        ArrayList temp = new ArrayList<Integer>();
        for (int i = 0; i < macroestado.length; i++) {
            for (int j = 0; j < transicionesLambda.size(); j++) {
                if (transicionesLambda.get(j).getInitState() == macroestado[i]) {
                    temp.add(transicionesLambda.get(j).getEndState());
                }
            }
        }
        int[] arrayFinal = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            arrayFinal[i] = (int) temp.get(i);
        }
        return arrayFinal;
    }

    public boolean reconocer(String cadena) {

        char[] simbolo = cadena.toCharArray();
        int[] estado = {0}; //El estado inicial es el 0
        int[] macroestado = lambda_clausura(estado);
        for (int i = 0; i < simbolo.length; i++) {
            macroestado = transicion(macroestado, simbolo[i]);
        }

        return esFinal(macroestado);
    }

    /*public static AFND pedir() {

        return null;
    }*/
    @Override
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(AFND.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void agregarFinal(int e) {
        estadosFinales.add(e);
    }

    public void setInicial(int e) {
        this.estadoInicial = e;
    }

    public int getEstadoInicial() {
        return estadoInicial;
    }

    public List<Integer> getEstadosFinales() {
        return estadosFinales;
    }

    public List<TransicionAFND> getTransiciones() {
        return transiciones;
    }

    public List<Integer> getEstados() {

        return estados;
    }

    public List<TransicionLambda> getTransicionesLambda() {
        return this.transicionesLambda;
    }

}
