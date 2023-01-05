/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author manu_
 */
public class TransicionAFND {
    private int initState;
    private char symbol;
    private int endState;
    
    TransicionAFND(int _initState,char _symbol,int _endState){
        initState = _initState;
        symbol = _symbol;
        endState = _endState;
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
