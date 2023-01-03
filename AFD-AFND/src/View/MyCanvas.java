/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.AFD;
import Model.TransicionAFD;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Model.TransicionLambda;
import Model.AFND;
import Model.TransicionAFND;

/**
 *
 * @author manu_
 */
public class MyCanvas extends Canvas {

    private int estadoInicial;
    private ArrayList<Integer> estadosFinales;
    private ArrayList<TransicionAFD> transiciones;
    private ArrayList<Integer> estados;
    private AFD aut;
    private AFND autN;
    private Map<Integer, coords> coordsMap;
    private ArrayList<TransicionAFD> transicionesSolucion;
    private ArrayList<TransicionLambda> transicionesLambda;
    private ArrayList<TransicionAFND> transicionesAFND;
    private boolean esAFN;

    class coords {

        public coords(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int x;
        int y;
    };

    public MyCanvas(int WIDTH, int HEIGHT) {
        this.setSize(WIDTH, HEIGHT);
        this.setBackground(Color.white);

        this.estadoInicial = -1;
        this.estadosFinales = new ArrayList<>();
        this.transiciones = new ArrayList<>();
        this.estados = new ArrayList<>();
        this.aut = new AFD();
        this.coordsMap = new HashMap<>();
        this.esAFN = true;
    }

    @Override
    public void paint(Graphics g) {
        Image img = createImage(this.getWidth(), this.getHeight());
        Graphics og = img.getGraphics();

        drawGrid(og);
        g.drawImage(img, 0, 0, null);

        if (estadoInicial == -1) {
            return;
        }

        int divisiones = ((estados.size() - estadosFinales.size() - 1) / 2) + 1;

        og.setColor(Color.black);

        drawState(0, 25, 250, false, og); //DIBUJAMOS EL ESTADO INICIAL
        coordsMap.put(0, new coords(25, 250));

        int divfinales = estadosFinales.size();

        for (int i = 0; i < estadosFinales.size(); i++) //DIBUJA LOS ESTADOS FINALES
        {
            drawState(estadosFinales.get(i), 800, (500 / divfinales) * i + 500 / divfinales / 2, true, og);
            coordsMap.put(estadosFinales.get(i), new coords(800, (500 / divfinales) * i + 500 / divfinales / 2));
        }
        
        int iter = 1;

        for (int i = 0; i < divisiones; i++) {
            for (int j = 0; j < 2; j++) {
                if (iter < estados.size() && !aut.esFinal(estados.get(iter))) {
                    if(!estadosFinales.contains(estados.get(iter)))
                    {
                        drawState(estados.get(iter), ((850 / divisiones) * i + (850 / divisiones / 2)), 125 * j + 250, false, og);
                        coordsMap.put(estados.get(iter), new coords(((850 / divisiones) * i + (850 / divisiones / 2)), 125 * j + 250));
                    }           
                }
                iter++;
            }
        }
        
        if (esAFN) {
            for (int i = 0; i < transiciones.size(); i++) {
                coords c1 = coordsMap.get(transiciones.get(i).getInitState());
                coords c2 = coordsMap.get(transiciones.get(i).getEndState());
                og.drawLine(c1.x + 20, c1.y + 15, c2.x, c2.y + 15);
                og.fillOval(c2.x - 5, c2.y + 12, 8, 8);
                /*int [] xPoints = {c1.x + 5,c1.x - 5,c2.x};
            int [] yPoints = {c1.y + 5,c2.y - 5,c2.y};
            
            og.drawPolygon(xPoints, yPoints, 3);*/
            }
        } else {
            for (int i = 0; i < transicionesAFND.size(); i++) {
                coords c1 = coordsMap.get(transicionesAFND.get(i).getInitState());
                for(int j = 0; j < transicionesAFND.get(i).getEndState().length; j++)
                {
                    coords c2 = coordsMap.get(transicionesAFND.get(i).getEndState()[j]);
                    og.drawLine(c1.x + 20, c1.y + 15, c2.x, c2.y + 15);
                    og.fillOval(c2.x - 5, c2.y + 12, 8, 8);
                } 
            }
            for (int i = 0; i < transicionesLambda.size(); i++) {
                coords c1 = coordsMap.get(transicionesLambda.get(i).getInitState());
                for(int j = 0; j < transicionesAFND.get(i).getEndState().length; j++)
                {
                    coords c2 = coordsMap.get(transicionesLambda.get(i).getEndState()[j]);
                    og.setColor(Color.blue);
                    og.drawLine(c1.x + 20, c1.y + 15, c2.x, c2.y + 15);
                    og.fillOval(c2.x - 5, c2.y + 12, 8, 8);
                    og.setColor(Color.black);
                } 
            }
        }

        if (transicionesSolucion != null) {
            og.setColor(Color.red);
            for (int i = 0; i < transicionesSolucion.size(); i++) {
                coords c1 = coordsMap.get(transicionesSolucion.get(i).getInitState());
                coords c2 = coordsMap.get(transicionesSolucion.get(i).getEndState());
                og.drawLine(c1.x + 20, c1.y + 15, c2.x, c2.y + 15);
                og.fillOval(c2.x - 5, c2.y + 12, 8, 8);
                /*int [] xPoints = {c1.x + 5,c1.x - 5,c2.x};
                int [] yPoints = {c1.y + 5,c2.y - 5,c2.y};

                og.drawPolygon(xPoints, yPoints, 3);*/
            }
        }

        g.drawImage(img, 0, 0, null);
    }

    public void update() {
        paint(this.getGraphics());
    }

    public void drawAFD(AFD aut) {
        this.esAFN = true;
        this.aut = aut;

        this.estadoInicial = aut.getEstadoInicial();
        this.estadosFinales = (ArrayList<Integer>) aut.getEstadosFinales();
        this.transiciones = (ArrayList<TransicionAFD>) aut.getTransiciones();
        this.estados = (ArrayList<Integer>) aut.getEstados();

        paint(this.getGraphics());
    }

    public void drawAFND(AFND autN) {
        this.esAFN = false;
        this.autN = autN;
        this.estadoInicial = autN.getEstadoInicial();
        this.estadosFinales = (ArrayList<Integer>) autN.getEstadosFinales();
        this.transicionesAFND = (ArrayList<TransicionAFND>) autN.getTransiciones();
        this.transicionesLambda = (ArrayList<TransicionLambda>) autN.getTransicionesLambda();
        this.estados = (ArrayList<Integer>) autN.getEstados();
        
        System.out.println("Tam finales: " + estadosFinales.size());
        System.out.println("Tam AFND: " + transicionesAFND.size());
        System.out.println("Tam Lambda: " + transicionesLambda.size());
        System.out.println("Tam estados: " + estados.size());
        
        paint(this.getGraphics());
    }

    public void drawState(int estado, int x, int y, boolean esfinal, Graphics og) {
        og.setColor(Color.white);
        og.fillOval(x, y, 30, 30);
        og.setColor(Color.black);
        og.drawOval(x, y, 30, 30);
        og.drawString("q" + estado, x + 10, y + 20);
        if (esfinal) {
            og.drawOval(x - 5, y - 5, 40, 40);
        }
    }

    public void drawGrid(Graphics og) {
        og.setColor(new Color(235, 236, 240));

        for (int i = 0; i < 34; i++) {
            og.drawLine(25 * i + 25, 0, 25 * i + 25, 499);
        }
        for (int i = 0; i < 20; i++) {
            og.drawLine(0, 25 * i + 25, 849, 25 * i + 25);
        }

        og.setColor(Color.black);
        og.drawRect(0, 0, 849, 499);
    }

    public void drawSolution(ArrayList<TransicionAFD> transicionesSolucion) {
        this.transicionesSolucion = transicionesSolucion;
        update();
    }
}
