/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.AFD;
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
    
    public MainController() throws UnsupportedLookAndFeelException
    {
        UIManager.setLookAndFeel(new FlatMacLightLaf());
        
        w = new mainWindow();
        d = new AFD_Dialog();

        addListeners();
        
        w.setTitle("Practica 2 AMC");
        w.setSize(900,560);
        w.setLocationRelativeTo(null);
        w.setVisible(true);
        
        MyCanvas cv = new MyCanvas(500,500);
        w.canvasContainer.setLayout(new GridLayout());
        w.canvasContainer.add(cv);
    }
    
    public void addListeners()
    {
        w.addAFDButton.addActionListener(this);
        w.addAFNDButton.addActionListener(this);
        w.showAFDButton.addActionListener(this);
        w.stepSecuenceButton.addActionListener(this);
        w.executeSecuenceButton.addActionListener(this);
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
                
            break;
            
            case "addInitState": 
                
                int ini = Integer.parseInt(d.initStateAFDTextField.getText());
                aut.setInicial(ini);
                
                break;
                
            case "addEndState": 
                
                int en = Integer.parseInt(d.endStateAFDTextField.getText());
                aut.agregarFinal(en);
                
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
                
                break;
                
            case "executeSecuence":
                    
                    w.console.append("\nEJECUTANDO LA SECUENCIA: \n");
                
                    char [] secuencia = w.secuenceTextField.getText().toCharArray();
                    
                    if(!aut.reconocer(String.valueOf(secuencia)))
                    {
                        w.console.append("La secuencia introducida no es válida");
                        break;
                    }
                    
                    int actual = aut.getEstadoInicial();
                    
                    w.console.append("q" + actual + " ---> ");
                    
                    for(int i = 0; i < secuencia.length; i++)
                    {
                        actual = aut.transicion(actual, secuencia[i]);
                        w.console.append("q" + actual);
                        
                        if(i != secuencia.length - 1)
                            w.console.append(" ---> ");
                    }
                
                break;
        }
    }
}
