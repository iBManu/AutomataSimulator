/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author manu_
 */
public class TransicionLambda {

    private int initState;
    private int[] endStates;

    TransicionLambda(int _initState, int[] _endStates) {
        initState = _initState;
        endStates = _endStates;
    }

    public int getInitState() {
        return initState;
    }

    public int[] getEndState() {
        return endStates;
    }
}
