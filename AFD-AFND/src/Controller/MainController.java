/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.AFD;
import Model.ReaderWriter;
import Model.TransicionAFD;
import View.AFD_Dialog;
import View.AFND_Dialog;
import View.MyCanvas;
import View.mainWindow;
//import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFileChooser;
//import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import Model.AFND;
import Model.TransicionAFND;
import Model.TransicionLambda;
/**
 *
 * @author manu_
 */
public class MainController implements ActionListener{
        
    private mainWindow w;
    private AFD aut;
    private AFND autN;
    private AFD_Dialog d;
    private ArrayList<Integer> estadosFinales;
    private ArrayList<TransicionAFD> transiciones;
    private ArrayList<TransicionAFND> transicionesAFND;
    private ArrayList<TransicionLambda> transicionesLambda;
    private ArrayList<Integer> estados;
    private MyCanvas cv;
    private char [] secuencia;
    private int secuenceindex = 0;
    private int _actual = -1;
    private ArrayList<TransicionAFD> _temptrans;
    private JFileChooser fileChooser;
    private ReaderWriter rw;
    private AFND_Dialog dL;
    private boolean esAFN;
    
    public MainController() throws UnsupportedLookAndFeelException
    {
        //UIManager.setLookAndFeel(new FlatMacLightLaf());
        
        w = new mainWindow();
        d = new AFD_Dialog();
        dL = new AFND_Dialog();
        addListeners();
        
        w.setTitle("Practica 2 AMC");
        w.setSize(1250,560);
        w.setLocationRelativeTo(null);
        w.setVisible(true);
        
        cv = new MyCanvas(850,500);
        w.canvasContainer.setLayout(new GridLayout());
        w.canvasContainer.add(cv);
        rw = new ReaderWriter(w);
        
        //d.endStateAFDTextField.setSize(15,15);
    }
    
    public void addListeners()
    {
        w.addAFDButton.addActionListener(this);
        w.addAFNDButton.addActionListener(this);
        w.showAFDButton.addActionListener(this);
        w.showAFNDButton.addActionListener(this);
        w.stepSecuenceButton.addActionListener(this);
        w.executeSecuenceButton.addActionListener(this);
        w.saveFileButton.addActionListener(this);
        w.openFileButton.addActionListener(this);
        w.moreAFDButton.addActionListener(this);
        w.moreAFNDButton.addActionListener(this);
        w.enterSecuenceButton.addActionListener(this);
        d.addTransitionButton.addActionListener(this);
        d.addEndStateButton.addActionListener(this);
        dL.addTransitionLambdaButton.addActionListener(this);
        dL.addEndStateButton.addActionListener(this);
        dL.addTransitionButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch (e.getActionCommand()) {
            
            case "addTransitionLambda":
                //Botón de añadir transiciones normales en AFND.
                int init = Integer.parseInt(dL.initStateTextField.getText());
                char[] end = dL.endStateTextField.getText().toCharArray();
                int[] endFinal = new int[end.length];
                for(int i = 0;i < end.length;i++){
                    endFinal[i] = Character.getNumericValue(end[i]);
                }
                char sym = dL.symbolTextField.getText().toCharArray()[0];
                autN.agregarTransicion(init, sym, endFinal);
                cv.update();
                break;
            case "addEndStateLambda":
                //Botón de añadir estado final en AFND.
                int en = Integer.parseInt(dL.endStateAFNDTextField.getText());
                autN.agregarFinal(en);
                
                cv.update();
                break;
            case "addLambdaTransition":
                //Botón de añadir lambda en AFND.
                int initN = Integer.parseInt(dL.initStateLambdaTextField.getText());
                char[] endN = dL.endStateLambdaTextField.getText().toCharArray();
                int[] endFinalN = new int[endN.length];
                for(int i = 0;i < endN.length;i++){
                    endFinalN[i] = Character.getNumericValue(endN[i]);
                }
                autN.agregarTransicionLambda(initN,endFinalN);
                cv.update();
                break;
            
            case "addAFND":
                
                esAFN = false;
                autN = new AFND();
                autN.setInicial(0);
                
                dL.setLocationRelativeTo(null);
                dL.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                dL.setVisible(true);
                break;
            case "addAFD":
                
                esAFN = true;
                aut = new AFD();
                aut.setInicial(0);
                
                d.setLocationRelativeTo(null);
                d.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                d.setVisible(true);
                break;
            
            case "addTransition":
                int initT = Integer.parseInt(d.initStateTextField.getText());
                int endT = Integer.parseInt(d.endStateTextField.getText());
                char symT = d.symbolTextField.getText().toCharArray()[0];
                aut.agregarTransicion(initT, symT, endT);
                
                cv.update();
                
            break;
                
            case "addEndState": 
                
                int en_ = Integer.parseInt(d.endStateAFDTextField.getText());
                aut.agregarFinal(en_);
                
                cv.update();
                
                break;
                
            case "showAFD":
                
                setConsole();
                
                cv.drawSolution(null);
                cv.drawAFD(aut);
                
                break;
            
            case "showAFND":
                
                setConsoleAFND();
                
                cv.drawSolution(null);
                cv.drawAFND(autN);
                break;
                
            case "saveFile":
                
                setConsole();
                
                rw.write(w.console.getText());
                
                break;
                
            case "openFile":
                
                aut = rw.read();
                
                cv.drawAFD(aut);
                
                break;
                
            case "moreAFD":
                
                d.setLocationRelativeTo(null);
                d.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                d.setVisible(true);
                
                setConsole();
                
                break;
                
            case "moreAFND":
                
                dL.setLocationRelativeTo(null);
                dL.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                dL.setVisible(true);
                
                setConsoleAFND();
                
            case "enterSecuence":
                    
                if(esAFN)
                {
                    secuenceindex = 0;
                    _temptrans = new ArrayList<TransicionAFD>();
                    _actual = -1;
                    cv.drawSolution(null);

                    cv.drawAFD(aut);

                    if(!aut.reconocer(String.valueOf(w.secuenceTextField.getText()))) //XD VALUEOF (MIRAR)
                        w.console.append("\nLa secuencia introducida no es válida");
                    else
                        secuencia = w.secuenceTextField.getText().toCharArray();

                    break;
                }
                else
                {
                    if(!autN.reconocer(String.valueOf(w.secuenceTextField.getText())))
                        w.console.append("\nLa secuencia introducida no es válida");
                    else
                        secuencia = w.secuenceTextField.getText().toCharArray();
                }
                
                
            case "executeSecuence":
                   
                if(esAFN)
                {
                    ArrayList<TransicionAFD> temptrans = new ArrayList<TransicionAFD>();
                    int actualtemp;

                    w.console.append("\nEJECUTANDO LA SECUENCIA: \n");

                    int actual = aut.getEstadoInicial();

                    w.console.append("q" + actual + " ---> ");

                    for(int i = 0; i < secuencia.length; i++)
                    {
                        actualtemp = actual;
                        actual = aut.transicion(actual, secuencia[i]);
                        w.console.append("q" + actual);

                        temptrans.add(new TransicionAFD(actualtemp,actual,secuencia[i]));

                        if(i != secuencia.length - 1)
                            w.console.append(" ---> ");
                    }

                    cv.drawSolution(temptrans);
                }
                else
                {
                    w.console.append("\nEJECUTANDO LA SECUENCIA: \n");
                    
                    int actual = autN.getEstadoInicial();
                    w.console.append("q" + actual + " ---> ");
                    
                    for (int i = 0; i < secuencia.length; i++) {
                        
                    }
                }
                
                
                
                break;
                
            case "stepSecuence":
                
                int _actualtemp;
                
                if(secuenceindex == secuencia.length)
                {
                    w.console.append("\nSECUENCIA TERMINADA");
                    break;
                }
                else if(secuenceindex == 0 && _actual == -1)
                {
                    w.console.append("\nEJECUTANDO LA SECUENCIA POR PASO: \n");
                    _actual = aut.getEstadoInicial();
                    
                    w.console.append("q" + _actual + " ---> ");
                    
                    _actualtemp = _actual;
                    _actual = aut.transicion(_actual, secuencia[secuenceindex]);
                    _temptrans.add(new TransicionAFD(_actualtemp,_actual,secuencia[secuenceindex]));
                    cv.drawSolution(_temptrans);
                }
                else
                {
                    _actualtemp = _actual;
                    _actual = aut.transicion(_actual, secuencia[secuenceindex]);
                    _temptrans.add(new TransicionAFD(_actualtemp,_actual,secuencia[secuenceindex]));
                    cv.drawSolution(_temptrans);
                }
                    
                secuenceindex++;
                w.console.append("q" + _actual);
                
                if(secuenceindex < secuencia.length)
                    w.console.append(" ---> ");
                
                break;
        }
    }
    
    public void setConsole()
    {
        w.console.setText("");
                
        estados = (ArrayList<Integer>) aut.getEstados();

        w.console.append("ESTADOS: ");

        for(int i = 0; i < estados.size(); i++)
            w.console.append("q" + estados.get(i) + " ");

        w.console.append("\n");

        w.console.append("ESTADO INICIAL: q" + aut.getEstadoInicial() + "\n");
        w.console.append("ESTADOS FINALES: ");

        estadosFinales = (ArrayList<Integer>) aut.getEstadosFinales();

        for(int i = 0; i < estadosFinales.size(); i++)
            w.console.append("q" + estadosFinales.get(i) + " ");

        w.console.append("\n");

        transiciones = (ArrayList<TransicionAFD>) aut.getTransiciones();

        w.console.append("TRANSICIONES: \n");

        for(int i = 0; i < transiciones.size(); i++)
            w.console.append(" q" + transiciones.get(i).getInitState() + " '" + transiciones.get(i).getSymbol() + "' q" + transiciones.get(i).getEndState() + "\n");

        w.console.append("-----------------------------------\n");
    }
    
    public void setConsoleAFND()
    {
        w.console.setText("");
                
        estados = (ArrayList<Integer>) autN.getEstados();

        w.console.append("ESTADOS: ");

        for(int i = 0; i < estados.size(); i++)
            w.console.append("q" + estados.get(i) + " ");

        w.console.append("\n");

        w.console.append("ESTADO INICIAL: q" + autN.getEstadoInicial() + "\n");
        w.console.append("ESTADOS FINALES: ");

        estadosFinales = (ArrayList<Integer>) autN.getEstadosFinales();

        for(int i = 0; i < estadosFinales.size(); i++)
            w.console.append("q" + estadosFinales.get(i) + " ");

        w.console.append("\n");

        transicionesAFND = (ArrayList<TransicionAFND>) autN.getTransiciones();

        w.console.append("TRANSICIONES: \n");

        for(int i = 0; i < transicionesAFND.size(); i++)
        {
            w.console.append(" q" + transicionesAFND.get(i).getInitState() + " '" + transicionesAFND.get(i).getSymbol() + "'");
            for(int j = 0; j < transicionesAFND.get(i).getEndState().length; j++)
                w.console.append(" q" + transicionesAFND.get(i).getEndState()[j]);
            w.console.append("\n");
        }
            
            

        transicionesLambda = (ArrayList<TransicionLambda>) autN.getTransicionesLambda();
        
        w.console.append("TRANSICIONES LAMBDA: \n");
        
        for(int i = 0; i < transicionesLambda.size(); i++)
        {
            w.console.append(" q" + transicionesLambda.get(i).getInitState() + " 'Lambda' q" + transicionesLambda.get(i).getEndState() + "\n");
            for(int j = 0; j < transicionesLambda.get(i).getEndState().length; j++)
                w.console.append(" q" + transicionesLambda.get(i).getEndState()[j]);
            w.console.append("\n");
        }
            
        
        w.console.append("-----------------------------------\n");
    }
}