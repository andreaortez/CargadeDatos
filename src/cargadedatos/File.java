/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cargadedatos;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Andrea
 */
public class File extends java.io.File {

    private java.io.File file;
    private String metadata;
    private String[] campos;
    private ArrayList<String> registros = new ArrayList();

    public File(String pathname) {
        super(pathname);
        this.file = new java.io.File(pathname);
        this.metadata = "";
    }

    public java.io.File getFile() {
        return file;
    }

    public void setFile(java.io.File file) {
        this.file = file;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String[] getCampos() {
        return campos;
    }

    public void setCampos(String[] campos) {
        this.campos = campos;
    }

    public void openFileS(java.io.File archivo) {
        this.file = archivo;
        if (file.exists()) {
            try {
                Scanner sc = null;
                sc = new Scanner(archivo);
                metadata = sc.next();
                campos = metadata.split(",");
                for (int i = 0; i < campos.length; i++) {
                    campos[i] = campos[i].replace('_', ' ');
                    System.out.println(campos[i]);
                }
                sc.close();
            } catch (Exception ex) {
            }
        }//FIN IF
    }

    public void openFileA(java.io.File archivo) {
        this.file = archivo;
        if (file.exists()) {
            try {
                Scanner sc = null;
                sc = new Scanner(archivo);
                metadata = sc.next();
                campos = metadata.split(",");
                for (int i = 0; i < campos.length; i++) {
                    System.out.println(campos[i]);
                }

                registros = new ArrayList();
                String record = new String();

                if (archivo.exists()) {
                    try {
                        System.out.println("registros");
                        while (sc.hasNext()) {
                            record = sc.nextLine();
                            for (int i = 0; i < record.length(); i++) {
                                if ((int) record.charAt(i) == 164) {
                                }
                            }

                            System.out.println(record);
                            registros.add(record);

                        }
                    } catch (Exception ex) {
                    }
                    sc.close();
                }//FIN IF
            } catch (Exception ex) {
            }

        }//FIN IF

    }
}
