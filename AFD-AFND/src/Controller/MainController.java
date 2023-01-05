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
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
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
    private boolean esAFD;
    
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
                int end = Integer.parseInt(dL.endStateTextField.getText());
                char sym = dL.symbolTextField.getText().toCharArray()[0];
                autN.agregarTransicion(init, sym, end);
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
                int endFinalN = Integer.parseInt(dL.endStateLambdaTextField.getText());
                autN.agregarTransicionLambda(initN,endFinalN);
                cv.update();
                break;
            
            case "addAFND":
                
                esAFD = false;
                autN = new AFND();
                autN.setInicial(0);
                
                dL.setLocationRelativeTo(null);
                dL.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                dL.setVisible(true);
                break;
            case "addAFD":
                
                esAFD = true;
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
                
                if(esAFD)
                {
                    setConsole();

                    rw.write(w.console.getText());
                }
                else
                {
                    setConsoleAFND();
                    
                    rw.write(w.console.getText());
                }
                
                
                
                break;
                
            case "openFile":
                
                if(esAFD)
                {
                    aut = rw.readAFD();
                    cv.drawAFD(aut);
                }
                else
                {
                    autN = rw.readAFND(); 
                    cv.drawAFND(autN);
                }
                
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
                    
                if(esAFD)
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
                    /*if(!autN.reconocer(String.valueOf(w.secuenceTextField.getText())))
                        w.console.append("\nLa secuencia introducida no es válida");
                    else*/
                        secuencia = w.secuenceTextField.getText().toCharArray();
                }
                
                break;
                
            case "executeSecuence":
                   
                if(esAFD)
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
                    ArrayList<Integer> macroestado;
                    ArrayList<Integer> macroestadoLambdas;
                    w.console.append("q" + actual + " ---> ");
                    
                    macroestado = autN.transicionLambda(actual);
                    ArrayList<Integer> camino = new ArrayList<Integer>();
                    camino = solucionAFND(macroestado, secuencia, camino);
                    /*for (int i = 0; i < secuencia.length; i++)
                    {
                        macroestado = autN.transicion(macroestado, secuencia[i]);
                    }*/
                    
                    for(int i = 0; i < camino.size(); i++)
                    {
                        w.console.append("q" + camino.get(i) + " ---> ");
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
            w.console.append(" q" + transicionesAFND.get(i).getInitState() + " '" + transicionesAFND.get(i).getSymbol() + "' q" + transicionesAFND.get(i).getEndState());
            w.console.append("\n");
        }
            
            

        transicionesLambda = (ArrayList<TransicionLambda>) autN.getTransicionesLambda();
        
        w.console.append("TRANSICIONES LAMBDA: \n");
        
        for(int i = 0; i < transicionesLambda.size(); i++)
        {
            w.console.append(" q" + transicionesLambda.get(i).getInitState() + " 'Lambda' q" + transicionesLambda.get(i).getEndState());
            w.console.append("\n");
        }
            
        
        w.console.append("-----------------------------------\n");
    }
    
    public ArrayList<Integer> solucionAFND_Antigua(ArrayList<Integer> macroestado, char [] secuencia, ArrayList<Integer> camino)
    {
        /*macroestado = autN.transicion(macroestado, secuencia[0]);
        macroestado = autN.lambda_clausura(macroestado);*/
        
        /*System.out.println("------------------------------------");
        
        System.out.println("Tam secuencia: " + secuencia.length);
        System.out.println("Secuencia actual: " + secuencia[0]);
        
        System.out.println("Macroestados: ");
        
        for(int i = 0; i < macroestado.size(); i++)
        {
            System.out.println(macroestado.get(i) + ",");
        }
        
        if(camino != null)
        {
            System.out.println("Camino: ");
            for(int i = 0; i < camino.size(); i++)
            {
                System.out.println(camino.get(i) + ",");
            }
        }
        else
            secuencia = new char [secuencia.length - 1];*/
        
        if(secuencia.length == 1) //SI HEMOS LLEGADO AL FINAL DE LA SECUENCIA, COMPROBAMOS QUE ALGUNO DE LOS ESTADOS SEAN FINALES
        {
            /*macroestado = autN.transicion(macroestado, secuencia[0]);
            System.out.println("Macroestado sin lambda: ");
            for(int i = 0; i < macroestado.size(); i++)
            {
                System.out.println(macroestado.get(i));
            }
            //macroestado = autN.lambda_clausura(macroestado);
            System.out.println("Macroestado con lambda: ");
            for(int i = 0; i < macroestado.size(); i++)
            {
                System.out.println(macroestado.get(i));
            }*/
            for(int i = 0; i < macroestado.size(); i++)
            {
                System.out.println("Es " + macroestado.get(i) + " final?: " + autN.esFinal(macroestado.get(i)));
                System.out.println("Proviene " + macroestado.get(i) + " de lambda?: " + autN.provieneDeLambda(macroestado.get(i)));
                if(autN.esFinal(macroestado.get(i)) && !autN.provieneDeLambda(macroestado.get(i))) //AÑADIR CONDICION DE QUE NO PROVENGA DE LAMBDA
                {
                    
                    if(autN.provieneDeLambda(macroestado.get(i)))
                    {
                        camino.add(macroestado.get(i));
                        macroestado = autN.getFullMacroestado(macroestado.get(i), secuencia[0]);
                        return solucionAFND(macroestado, secuencia, camino);
                    }
                    else
                    {
                        camino.add(macroestado.get(i));
                        return camino;
                    } 
                }
            }
        }
        else //SI NO, RECURSIVIDAD
        {
            char [] secuenciamenosuno = new char [secuencia.length - 1];
            /*for(int i = 1; i < secuenciamenosuno.length; i++)
                secuenciamenosuno[i] = secuencia[i];*/
            
            System.arraycopy(secuencia, 1, secuenciamenosuno, 0, secuenciamenosuno.length);
            
            for(int i = 0; i < macroestado.size(); i++)
            {
                if(autN.transicionLambda(0).contains(macroestado.get(i))) //PARA CADA ESTADO DEL MACROESTADO, COMPROBAMOS SI PROVIENE DE UNA TRANSICION LAMBDA DEL ESTADO "PADRE"(LO HACEMOS SACANDO LAS TRANSICIONES LAMBDA DEL INDICE 0 DEL MACROESTADO, YA QUE ESTE ES EL INICIO DEL MACROESTADO), PARA NO CONTAR UN DIGITO DE LA SECUENCIA
                {
                    //System.out.println("tam macro: " + macroestado.size());
                    camino.add(macroestado.get(i));
                    macroestado = autN.getFullMacroestado(macroestado.get(i), secuencia[0]);
                    return solucionAFND(macroestado, secuencia, camino);
                }
                else
                {
                    //System.out.println("udihfdsojfsd: " + macroestado.get(i) + "," + secuenciamenosuno[0]);
                    camino.add(macroestado.get(i));
                    macroestado = autN.getFullMacroestado(macroestado.get(i), secuencia[0]);
                    return solucionAFND(macroestado, secuenciamenosuno, camino);
                }
            }
        }  
        return null;
    }
    
    public ArrayList<Integer> solucionAFND(ArrayList<Integer> macroestado, char[] secuencia, ArrayList<Integer> camino) {
    if (secuencia.length == 0) {
        // Si hemos llegado al final de la secuencia, comprobamos que alguno de los estados sea final
        for (int i = 0; i < macroestado.size(); i++) {
            if (autN.esFinal(macroestado.get(i)) && !autN.provieneDeLambda(macroestado.get(i))) {
                // Añadimos el estado al camino y retornamos el camino
                camino.add(macroestado.get(i));
                return camino;
            }
        }
    } else {
        // Si no, recursividad
        char[] secuenciamenosuno = new char[secuencia.length - 1];
        System.arraycopy(secuencia, 1, secuenciamenosuno, 0, secuenciamenosuno.length);

        for (int i = 0; i < macroestado.size(); i++) {
            if (autN.transicionLambda(0).contains(macroestado.get(i))) {
                // Si el estado proviene de una transición lambda desde el estado inicial, no contamos un dígito de la secuencia
                camino.add(macroestado.get(i));
                ArrayList<Integer> subCamino = solucionAFND(autN.getFullMacroestado(macroestado.get(i), secuencia[0]), secuencia, camino);
                if (!subCamino.isEmpty()) {
                    // Si se ha encontrado un camino, lo retornamos
                    return subCamino;
                }
            } else {
                // Si no, seguimos avanzando en la secuencia
                camino.add(macroestado.get(i));
                ArrayList<Integer> subCamino = solucionAFND(autN.getFullMacroestado(macroestado.get(i), secuencia[0]), secuenciamenosuno, camino);
                if (!subCamino.isEmpty()) {
                    // Si se ha encontrado un camino, lo retornamos
                    return subCamino;
                }
            }
        }
    }

    // Si no se ha encontrado ningún camino, retornamos una lista vacía
    return new ArrayList<>();
}

}