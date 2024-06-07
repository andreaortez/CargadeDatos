/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cargadedatos;

import java.awt.Color;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;

/**
 *
 * @author Andrea
 */
public class GUI extends javax.swing.JFrame {

    Archivo archivo = null;
    //File secciones = null;
    //File alumnos = null;
    java.io.File archivoS = null;
    java.io.File archivoA = null;

    public GUI() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        tf_FilepathS = new javax.swing.JTextField();
        bt_buscarS = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        tf_FilepathA = new javax.swing.JTextField();
        bt_buscarA = new javax.swing.JButton();
        bt_a�adirP = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        bt_cargarA = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(27, 43, 88));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tf_FilepathS.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tf_FilepathS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_FilepathSActionPerformed(evt);
            }
        });
        jPanel2.add(tf_FilepathS, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 490, 40));

        bt_buscarS.setText("...");
        bt_buscarS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_buscarSMouseClicked(evt);
            }
        });
        jPanel2.add(bt_buscarS, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, -1, 40));

        jLabel36.setBackground(new java.awt.Color(0, 0, 0));
        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Elija el archivo de secciones:");
        jPanel2.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 650, -1));

        jLabel37.setBackground(new java.awt.Color(0, 0, 0));
        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(51, 51, 51));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Elija el archivo de alumnos:");
        jPanel2.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 650, -1));

        tf_FilepathA.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 18)); // NOI18N
        tf_FilepathA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_FilepathAActionPerformed(evt);
            }
        });
        jPanel2.add(tf_FilepathA, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 490, 40));

        bt_buscarA.setText("...");
        bt_buscarA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_buscarAMouseClicked(evt);
            }
        });
        jPanel2.add(bt_buscarA, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 240, -1, 40));

        bt_a�adirP.setBackground(new java.awt.Color(195, 22, 28));
        bt_a�adirP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_a�adirPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_a�adirPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_a�adirPMouseExited(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setText("A�adir Empleo");

        javax.swing.GroupLayout bt_a�adirPLayout = new javax.swing.GroupLayout(bt_a�adirP);
        bt_a�adirP.setLayout(bt_a�adirPLayout);
        bt_a�adirPLayout.setHorizontalGroup(
            bt_a�adirPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel69, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bt_a�adirPLayout.setVerticalGroup(
            bt_a�adirPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bt_a�adirPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(bt_a�adirP, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 670, 120, 40));

        bt_cargarA.setBackground(new java.awt.Color(195, 22, 28));
        bt_cargarA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_cargarAMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_cargarAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_cargarAMouseExited(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setText("Cargar Archivos");

        javax.swing.GroupLayout bt_cargarALayout = new javax.swing.GroupLayout(bt_cargarA);
        bt_cargarA.setLayout(bt_cargarALayout);
        bt_cargarALayout.setHorizontalGroup(
            bt_cargarALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel70, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bt_cargarALayout.setVerticalGroup(
            bt_cargarALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bt_cargarALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(bt_cargarA, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 330, 130, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Carga de Archivos");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 650, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 650, 400));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/unitec blanco.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_FilepathSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_FilepathSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_FilepathSActionPerformed

    private void bt_buscarSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_buscarSMouseClicked
        try {
            JFileChooser jfc = new JFileChooser();
            int seleccion = jfc.showOpenDialog(this);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                archivoS = jfc.getSelectedFile();
                File f = new File(archivoS.getPath());
                if (f.ValidFile(archivoS, 0)) {
                    tf_FilepathS.setText(archivoS.getName());
                }
            } else {
                JOptionPane.showMessageDialog(this, "�Ocurri� un error!", "Warning", WARNING_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_bt_buscarSMouseClicked

    private void tf_FilepathAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_FilepathAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_FilepathAActionPerformed

    private void bt_buscarAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_buscarAMouseClicked
        try {
            JFileChooser jfc = new JFileChooser();
            int seleccion = jfc.showOpenDialog(this);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                archivoA = jfc.getSelectedFile();
                File f = new File(archivoA.getPath());

                if (f.ValidFile(archivoA, 1)) {
                    tf_FilepathA.setText(archivoA.getName());
                }
            } else {
                JOptionPane.showMessageDialog(this, "�Ocurri� un error!", "Warning", WARNING_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_bt_buscarAMouseClicked

    private void bt_a�adirPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_a�adirPMouseClicked

    }//GEN-LAST:event_bt_a�adirPMouseClicked

    private void bt_a�adirPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_a�adirPMouseEntered
        bt_a�adirP.setBackground(new Color(153, 0, 0));
    }//GEN-LAST:event_bt_a�adirPMouseEntered

    private void bt_a�adirPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_a�adirPMouseExited
        bt_a�adirP.setBackground(new Color(195, 22, 28));
    }//GEN-LAST:event_bt_a�adirPMouseExited

    private void bt_cargarAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_cargarAMouseClicked
        if (tf_FilepathS.getText().isEmpty() || tf_FilepathA.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "�Debe subir ambos archivos!", "Warning", WARNING_MESSAGE);
        } else {
            archivo = new Archivo(archivoA, archivoS);

            //este metodo genera la base de datos
            archivo.setup();
            //este metodo cambia inconsistencias mediante regex
            archivo.openFile();
            //escribe registros
            archivo.registros();
            //escribe secciones
            archivo.secciones();

            JOptionPane.showMessageDialog(this, "�Archivo cargado con �xito!", "Notification", INFORMATION_MESSAGE);
            this.dispose();
        }
    }//GEN-LAST:event_bt_cargarAMouseClicked

    private void bt_cargarAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_cargarAMouseEntered
        bt_cargarA.setBackground(new Color(153, 0, 0));
    }//GEN-LAST:event_bt_cargarAMouseEntered

    private void bt_cargarAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_cargarAMouseExited
        bt_cargarA.setBackground(new Color(195, 22, 28));
    }//GEN-LAST:event_bt_cargarAMouseExited

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bt_a�adirP;
    private javax.swing.JButton bt_buscarA;
    private javax.swing.JButton bt_buscarS;
    private javax.swing.JPanel bt_cargarA;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField tf_FilepathA;
    private javax.swing.JTextField tf_FilepathS;
    // End of variables declaration//GEN-END:variables
}
