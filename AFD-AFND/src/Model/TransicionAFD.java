/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author manu_
 */
public class TransicionAFD {
    
    private int initState;
    private int endState;
    private char symbol;
    
    public TransicionAFD(int initState, int endState, char symbol)
    {
        this.initState = initState;
        this.endState = endState;
        this.symbol = symbol;
    }

    public int getInitState() {
        return initState;
    }

    public int getEndState() {
        return endState;
    }

    public char getSymbol() {
        return symbol;
    }
}
