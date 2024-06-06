/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cargadedatos;

import java.io.FileInputStream;
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
        System.out.println("----------SECCIONES---------");

        if (file.exists()) {
            try {
                Scanner sc = new Scanner(file);
                metadata = sc.nextLine();
                campos = metadata.split(",");

                RandomAccessFile rf = new RandomAccessFile(file, "rw");
                String new_metadata = "";

                for (int i = 0; i < campos.length; i++) {
                    campos[i] = campos[i].replace('_', ' ');
                    System.out.println(campos[i]);
                    new_metadata += campos[i];
                    if (i < campos.length - 1) {
                        new_metadata += ",";
                    }
                }
                System.out.println("->" + new_metadata);

                rf.writeBytes(new_metadata);

                rf.close();
                sc.close();
            } catch (Exception ex) {
            }
        }
    }

    public void openFileA(java.io.File archivo) {
        this.file = archivo;
        System.out.println("----------ALUMNOS---------");
        if (file.exists()) {
            try {
                Scanner sc = new Scanner(new FileInputStream(file));
                metadata = sc.nextLine();
                int tam1 = metadata.length() - 1;
                metadata = metadata.substring(0, tam1);

                System.out.println(metadata);

                String record = new String();
                if (archivo.exists()) {
                    while (sc.hasNextLine()) {
                        record = sc.nextLine();
                        record = record.replace('Ñ', '$');
                        int tam2 = record.length() - 1;

                        if (record.charAt(tam2) == ',') {
                            record = record.substring(0, tam2);
                        } else if (record.charAt(tam2) == '.') {
                            record = record.substring(0, tam2 - 1);
                        }

                        System.out.println(record);
                        registros.add(record);
                    }
                }

                sc.close();
            } catch (Exception ex) {
            }
        }
    }
}
