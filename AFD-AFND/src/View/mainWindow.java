/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Frame.java to edit this template
 */
package View;

/**
 *
 * @author manu_
 */
public class mainWindow extends java.awt.Frame {

    /**
     * Creates new form mainWindow
     */
    public mainWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        canvasContainer = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();
        executeSecuenceButton = new javax.swing.JButton();
        stepSecuenceButton = new javax.swing.JButton();
        addAFNDButton = new javax.swing.JButton();
        addAFDButton = new javax.swing.JButton();
        showAFDButton = new javax.swing.JButton();
        secuenceTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(900, 560));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(canvasContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 500, 500));

        console.setColumns(20);
        console.setRows(5);
        jScrollPane1.setViewportView(console);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 340, 270));

        executeSecuenceButton.setText("Ejecutar");
        executeSecuenceButton.setActionCommand("executeSecuence");
        jPanel1.add(executeSecuenceButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 450, 160, 60));

        stepSecuenceButton.setText("Paso");
        stepSecuenceButton.setActionCommand("stepSecuence");
        jPanel1.add(stepSecuenceButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 450, 160, 60));

        addAFNDButton.setText("Añadir AFND");
        addAFNDButton.setActionCommand("addAFND");
        jPanel1.add(addAFNDButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 340, 130, 40));

        addAFDButton.setText("Añadir AFD");
        addAFDButton.setActionCommand("addAFD");
        jPanel1.add(addAFDButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 290, 130, 40));

        showAFDButton.setText("Mostrar AFD");
        showAFDButton.setActionCommand("showAFD");
        jPanel1.add(showAFDButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 290, 130, 40));
        jPanel1.add(secuenceTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 420, 340, -1));

        jLabel1.setText("Indica la secuencia:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 400, -1, -1));

        add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainWindow().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton addAFDButton;
    public javax.swing.JButton addAFNDButton;
    public javax.swing.JPanel canvasContainer;
    public javax.swing.JTextArea console;
    public javax.swing.JButton executeSecuenceButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextField secuenceTextField;
    public javax.swing.JButton showAFDButton;
    public javax.swing.JButton stepSecuenceButton;
    // End of variables declaration//GEN-END:variables
}
