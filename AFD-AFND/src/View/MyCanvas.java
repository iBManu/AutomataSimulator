/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author manu_
 */
public class MyCanvas extends Canvas{
    
    public MyCanvas(int WIDTH, int HEIGHT)
    {
        this.setSize(WIDTH, HEIGHT);
        this.setBackground(Color.pink);
    }
    
    @Override
    public void paint(Graphics g)
    {
    
    }
    
    public void update(Graphics g)
    {
        paint(g);
    }
    
}
