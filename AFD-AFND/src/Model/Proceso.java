/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model;

/**
 *
 * @author manu_
 */
public interface Proceso {
    public abstract boolean esFinal(int estado); //true si estado es un estado final
    public abstract boolean reconocer(String cadena) ; //true si la cadena es reconocida
    public abstract String toString( ) ; //muestra las transiciones y estados finales
}
