/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.MainController;
import View.mainWindow;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;

/**
 *
 * @author manu_
 */
public class ReaderWriter {

    private AFD aut;
    private AFND autN;
    private mainWindow w;
    
    public ReaderWriter(mainWindow w)
    {
        this.w = w;
    }

    public void write(String data) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save File");
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                FileWriter writer = new FileWriter(fileToSave + ".amc");
                writer.write(data);
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public AFD readAFD() {
        aut = new AFD();

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));

                List<String> states = new ArrayList<>();
                String line = reader.readLine();
                while (line != null) {
                    w.console.append(line + "\n");
                    if (line.startsWith("ESTADOS:")) {
                        Pattern pattern = Pattern.compile("q\\d+");
                        Matcher matcher = pattern.matcher(line);
                        while (matcher.find()) {
                            String number = matcher.group().substring(1);
                            states.add(number);
                        }
                    } else if (line.startsWith("ESTADO INICIAL:")) {
                        Pattern pattern = Pattern.compile("q\\d+");
                        Matcher matcher = pattern.matcher(line);
                        while (matcher.find()) {
                            String number = matcher.group().substring(1);
                            aut.setInicial(Integer.valueOf(number));
                        }
                    } else if (line.startsWith("ESTADOS FINALES:")) {
                        Pattern pattern = Pattern.compile("q\\d+");
                        Matcher matcher = pattern.matcher(line);
                        while (matcher.find()) {
                            String number = matcher.group().substring(1);
                            aut.agregarFinal(Integer.valueOf(number));
                        }
                    } else if (line.startsWith("TRANSICIONES:")) {

                    } else {
                        Pattern pattern = Pattern.compile("\\d+");
                        Matcher matcher = pattern.matcher(line);
                        int count = 0;
                        String number1 = null, number2 = null, number3 = null;
                        while (matcher.find()) {
                            count++;
                            if (count == 1) {
                                number1 = matcher.group();
                            } else if (count == 2) {
                                number2 = matcher.group();
                            } else if (count == 3) {
                                number3 = matcher.group();
                            }
                        }

                        aut.agregarTransicion(Integer.valueOf(number1), number2.charAt(0), Integer.valueOf(number3));
                    }
                    line = reader.readLine();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        w.console.append("----------------------------");

        return aut;
    }
    
    public AFND readAFND() {
        autN = new AFND();

        boolean leyendolambda = false;
        
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));

                List<String> states = new ArrayList<>();
                String line = reader.readLine();
                while (line != null) {
                    w.console.append(line + "\n");
                    if (line.startsWith("ESTADOS:")) {
                        Pattern pattern = Pattern.compile("q\\d+");
                        Matcher matcher = pattern.matcher(line);
                        while (matcher.find()) {
                            String number = matcher.group().substring(1);
                            states.add(number);
                        }
                    } else if (line.startsWith("ESTADO INICIAL:")) {
                        Pattern pattern = Pattern.compile("q\\d+");
                        Matcher matcher = pattern.matcher(line);
                        while (matcher.find()) {
                            String number = matcher.group().substring(1);
                            autN.setInicial(Integer.valueOf(number));
                        }
                    } else if (line.startsWith("ESTADOS FINALES:")) {
                        Pattern pattern = Pattern.compile("q\\d+");
                        Matcher matcher = pattern.matcher(line);
                        while (matcher.find()) {
                            String number = matcher.group().substring(1);
                            autN.agregarFinal(Integer.valueOf(number));
                        }
                    } else if (line.startsWith("TRANSICIONES:")) {
                        leyendolambda = false;
                    } else if(line.startsWith("TRANSICIONES LAMBDA:"))
                    {
                        leyendolambda = true;
                    }
                    else if (line.startsWith("-----------------------------------"))
                    {
                    
                    }
                    else
                    {
                        
                        if(leyendolambda)
                        {
                            System.out.println("Linea lambda: " + line);
                            String [] parts = line.split("'Lambda'");
                            String s1 = line.substring(2, 3).trim();
                            String s2 = line.substring(14, 15).trim();
                            System.out.println("s1: " + s1 + ",s2: " + s2);

                            autN.agregarTransicionLambda(Integer.parseInt(s1), Integer.parseInt(s2));
                        }
                        else
                        {
                            Pattern pattern = Pattern.compile("\\d+");
                            Matcher matcher = pattern.matcher(line);
                            int count = 0;
                            String number1 = null, number2 = null, number3 = null;
                            while (matcher.find()) {
                                count++;
                                if (count == 1) {
                                    number1 = matcher.group();
                                } else if (count == 2) {
                                    number2 = matcher.group();
                                } else if (count == 3) {
                                    number3 = matcher.group();
                                }
                            }

                            autN.agregarTransicion(Integer.valueOf(number1), number2.charAt(0), Integer.valueOf(number3));
                        }
                        
                    }
                    
                    line = reader.readLine();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        w.console.append("----------------------------");

        return autN;
    }
}
