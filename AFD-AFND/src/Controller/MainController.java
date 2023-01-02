/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.AFD;
import Model.ReaderWriter;
import Model.TransicionAFD;
import View.AFD_Dialog;
import View.MyCanvas;
import View.mainWindow;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author manu_
 */
public class MainController implements ActionListener{
        
    private mainWindow w;
    private AFD aut;
    private AFD_Dialog d;
    private ArrayList<Integer> estadosFinales;
    private ArrayList<TransicionAFD> transiciones;
    private ArrayList<Integer> estados;
    private MyCanvas cv;
    private char [] secuencia;
    private int secuenceindex = 0;
    private int _actual = -1;
    private ArrayList<TransicionAFD> _temptrans;
    private JFileChooser fileChooser;
    private ReaderWriter rw;
    
    public MainController() throws UnsupportedLookAndFeelException
    {
        UIManager.setLookAndFeel(new FlatMacLightLaf());
        
        w = new mainWindow();
        d = new AFD_Dialog();

        addListeners();
        
        w.setTitle("Practica 2 AMC");
        w.setSize(1250,560);
        w.setLocationRelativeTo(null);
        w.setVisible(true);
        
        cv = new MyCanvas(850,500);
        w.canvasContainer.setLayout(new GridLayout());
        w.canvasContainer.add(cv);
        rw = new ReaderWriter(w);
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
        d.addInitStateButton.addActionListener(this);
        d.addEndStateButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch (e.getActionCommand()) {
            
            case "addAFD":
                
                aut = new AFD();
                
                d.setLocationRelativeTo(null);
                d.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                d.setVisible(true);
                
                break;
            
            case "addTransition":
                int init = Integer.parseInt(d.initStateTextField.getText());
                int end = Integer.parseInt(d.endStateTextField.getText());
                char sym = d.symbolTextField.getText().toCharArray()[0];
                aut.agregarTransicion(init, sym, end);
                
                cv.update();
                
            break;
            
            case "addInitState": 
                
                int ini = Integer.parseInt(d.initStateAFDTextField.getText());
                aut.setInicial(ini);
                
                cv.update();
                
                break;
                
            case "addEndState": 
                
                int en = Integer.parseInt(d.endStateAFDTextField.getText());
                aut.agregarFinal(en);
                
                cv.update();
                
                break;
                
            case "showAFD":
                
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
                
                cv.drawAFD(aut);
                
                break;
                
            case "saveFile":
                
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
                
                break;
                
            case "enterSecuence":
                    
                secuenceindex = 0;
                _temptrans = new ArrayList<TransicionAFD>();
                
                if(!aut.reconocer(String.valueOf(w.secuenceTextField.getText())))
                    w.console.append("\nLa secuencia introducida no es válida");
                else
                    secuencia = w.secuenceTextField.getText().toCharArray();
                    
                break;
                
            case "executeSecuence":
                    
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
                
                break;
                
            case "stepSecuence":
                
                int _actualtemp;
                
                if(secuenceindex == secuencia.length - 1)
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
                    
                    secuenceindex++;
                }
                    

                w.console.append("q" + _actual);
                
                if(secuenceindex < secuencia.length - 1)
                    w.console.append(" ---> ");
                
                break;
        }
    }
}