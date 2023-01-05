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
public class TransicionLambda {

    private int initState;
    private int endState;

    TransicionLambda(int _initState, int _endState) {
        initState = _initState;
        endState = _endState;
    }

    public int getInitState() {
        return initState;
    }

    public int getEndState() {
        return endState;
    }
}
