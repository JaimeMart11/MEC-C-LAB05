/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package linechartex;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Jaime
 */
public final class Datos1 extends javax.swing.JFrame {

    int M = 0, F = 0, O = 0, A = 0, B = 0, AB = 0, NA = 0, nigga = 0, simp = 0, doble = 0;
    DefaultCategoryDataset datos = new DefaultCategoryDataset();
    DefaultPieDataset datost = new DefaultPieDataset();
    private Object Grafico;
    private Object Filtro;
    public byte[] llenar_texto(int n){
        String texto="";
        if(n == 0)
            texto = "FILTRO;GÉNERO;;TIPO DE SANGRE;;;;MULTIPLICIDAD EMBARAZO;;ETNIA";
        if(n == 1)
            texto = "\nCATEGORIA;MASCULINO;FEMENINO;A;B;AB;O;SIMPLE;DOBLE;NO PERTENECE;NEGRO(A), MULATO(A), AFRO COLOMBIANO(A) O AFRO DESCENDIENTE";
        if(n == 2)
            texto = "\nCANTIDAD;" + M + ";" + F + ";" + A + ";" + B + ";" + AB + ";" + O + ";" + simp + ";" + doble + ";" + NA + ";" + nigga;
        if(n == 3)
            texto = "\n\nTOP 3 TIPOS SANGRE;1. O; 2. A; 3. B";
        return texto.getBytes();
    }
    public void llenar_datos_bar(){
        switch(Filtro.getSelectedItem().toString()) {
            case "Etnia":
                datos.setValue(nigga, "Etnia", "Negro(a), Mulato(a), Afro Colombiano(a) O Afro Descendiente");
                datos.setValue(NA, "Etnia", "No Pertenece");
                break;
            case "Tipo de Sangre":
                datos.setValue(A, "Tipo de Sangre", "A");
                datos.setValue(AB, "Tipo de Sangre", "AB");
                datos.setValue(B, "Tipo de Sangre", "B");
                datos.setValue(O, "Tipo de Sangre", "O");
                break;
            case "Multiplicidad Embarazo":
                datos.setValue(simp, "Multiplicidad Embarazo", "Simple");
                datos.setValue(doble, "Multiplicidad Embarazo", "Doble");
                break;
            case "Género":
                datos.setValue(M, "Género", "Masculino");
                datos.setValue(F, "Género", "Femenino");
                break;
            default:
                datos.setValue(nigga, "Etnia", "Negro(a), Mulato(a), Afro Colombiano(a) O Afro Descendiente");
                datos.setValue(NA, "Etnia", "No Pertenece");
                datos.setValue(A, "Tipo de Sangre", "A");
                datos.setValue(AB, "Tipo de Sangre", "AB");
                datos.setValue(B, "Tipo de Sangre", "B");
                datos.setValue(O, "Tipo de Sangre", "O");
                datos.setValue(simp, "Multiplicidad Embarazo", "Simple");
                datos.setValue(doble, "Multiplicidad Embarazo", "Doble");
                datos.setValue(M, "Género", "Masculino");
                datos.setValue(F, "Género", "Femenino");
                break;
                
        }
    }
        public void llenar_datos_tor(){
        switch(Filtro.getSelectedItem().toString()) {
            case "Etnia":
                datost.setValue("Negro(a), Mulato(a), Afro Colombiano(a) O Afro Descendiente", nigga);
                datost.setValue("No Pertenece",NA);
                break;
            case "Tipo de Sangre":
                datost.setValue("A", A);
                datost.setValue("AB", AB);
                datost.setValue("B", B);
                datost.setValue("O", O);
                break;
            case "Multiplicidad Embarazo":
                datost.setValue("Simple", simp);
                datost.setValue("Doble", doble);
                break;
            case "Género":
                datost.setValue("Masculino", M);
                datost.setValue("Femenino",F);
                break;
            default:
                datost.setValue("Ver en graf. de barras", 100);
                break;
                
        }

    }
    public void Graf_barras(){
        datos.clear();
        Grafico.removeAll();
        llenar_datos_bar();     
        JFreeChart grafico_barras = ChartFactory.createBarChart("Salario minimo en colombia historricamente", Filtro.getSelectedItem().toString(), "Cantidad", datos);
        ChartPanel panel = new ChartPanel(grafico_barras);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(700,300));
        panel.setVisible(true);
        Grafico.setLayout(new BorderLayout());
        Grafico.add(panel,BorderLayout.NORTH);       
        pack();
        setLocationRelativeTo(null);
        
    }
    public void Graf_torta(){
        datost.clear();
        Grafico.removeAll();
        llenar_datos_tor();     
        JFreeChart grafico_circ = ChartFactory.createPieChart("Salario minimo en colombia historricamente\n" + Filtro.getSelectedItem().toString(), datost, true, true, false);
        ChartPanel panel = new ChartPanel(grafico_circ);        
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(700,300));
        panel.setVisible(true);
        Grafico.setLayout(new BorderLayout());
        Grafico.add(panel,BorderLayout.NORTH);       
        pack();
        setLocationRelativeTo(null);
    }
    public void llenar_variables(){
        Map<String, Integer> dic = new HashMap<>();
            dic.put("Tipo de Sangre", 15);
            dic.put("Multiplicidad Embarazo", 12);
            dic.put("Todos los anteriores", 0);
            dic.put("Género", 3);
            dic.put("Etnia",17);
            A = 0; B = 0; AB=0; O = 0; nigga = 0; NA = 0; simp = 0; doble = 0; M = 0; F = 0;
    try {
            try (BufferedReader lector = new BufferedReader(new FileReader("Serie_historica.csv"))) {
                String linea;
                String esp = Filtro.getSelectedItem().toString();
                System.out.println("Salió");
                // Lee
                while ((linea = lector.readLine()) != null) {
                    String[] valores = linea.split(",");
                    System.out.println(valores[dic.get(esp)]);
                    
                    if (valores[3].equals("MASCULINO"))
                        M++;
                    if (valores[3].equals("FEMENINO"))
                        F++;
                    if (valores[15].equals("O"))
                        O++;
                    if (valores[15].equals("A"))
                        A++;
                    if (valores[15].equals("B"))
                        B++;
                    if (valores[15].equals("AB"))
                        AB++;
                    if (valores[17].contains("NEGRO(A)"))
                        nigga++;
                    if (valores[17].equals("NINGUNO DE LOS ANTERIORES"))
                        NA++;
                    if (valores[12].equals("SIMPLE"))
                        simp++;
                    if (valores[12/*dic.get(esp)*/].equals("DOBLE"))
                        doble++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    public Datos1() {
        initComponents();
        llenar_variables();  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Salario minimo legal vigente colombiano historico ");

        jLabel2.setText("Salario");

        jLabel3.setText("Tipo de grafico ");

        jButton1.setText("Descargar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jButton1)))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(182, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            FileOutputStream file = new FileOutputStream("Serie_historica.csv");
            for(int c = 0; c < 4; c++){
                    byte texto[] = llenar_texto(c);
                    //datos.getBytes();            
            file.write(texto);  
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Grafs.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
                Logger.getLogger(Grafs.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Datos1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Datos1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Datos1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Datos1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Datos1().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables

    private static class Grafs {

        public Grafs() {
        }
    }

}
