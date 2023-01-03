/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author manu_
 */
public class TransicionAFND {
    private int initState;
    private char symbol;
    private int[] endStates;
    
    TransicionAFND(int _initState,char _symbol,int[] _endStates){
        initState = _initState;
        symbol = _symbol;
        endStates = _endStates;
    }
    
    public int getInitState() {
        return initState;
    }

    public int[] getEndState() {
        return endStates;
    }

    public char getSymbol() {
        return symbol;
    }
    
}
