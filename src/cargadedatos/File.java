/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cargadedatos;

import java.sql.Statement;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Andrea
 */
public class File extends java.io.File {

    private java.io.File file;
    private String metadata;
    private String[] campos;
    private ArrayList<String> registros = new ArrayList();
    Connection connection;

    public File(String pathname) {
        super(pathname);
        this.file = new java.io.File(pathname);
        this.metadata = "";

        String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "1234";

        String sqlScript1 = "drop table if Exists SeccionesXAlumno;\n"
                + "drop table if exists Seccion;\n"
                + "drop table if exists Curso;\n"
                + "drop table if exists Alumno;\n"
                + "\n"
                + "\n"
                + "create table Alumno(\n"
                + "	cuenta INT PRIMARY KEY,\n"
                + "	carrera VARCHAR NOT NULL,\n"
                + "	facultad VARCHAR NOT NULL);\n"
                + "	\n"
                + "\n"
                + "\n"
                + "create table Curso(\n"
                + "	materia VARCHAR PRIMARY KEY,\n"
                + "	materia_nombre VARCHAR NOT NULL\n"
                + "	);\n"
                + "	\n"
                + "\n"
                + "create table Seccion(\n"
                + "	seccion int primary key,\n"
                + "	hora varchar not null,\n"
                + "	materia varchar  references Curso(materia),\n"
                + "	aula_id varchar not null,\n"
                + "	dias_habiles varchar  \n"
                + "	\n"
                + ");\n"
                + "\n"
                + "create table SeccionesXAlumno(\n"
                + "	seccion int not null references Seccion(seccion),\n"
                + "	cuenta int not null references Alumno(cuenta),\n"
                + "	primary key(seccion, cuenta)\n"
                + ");";

        String sqlScript2 = "CREATE OR REPLACE PROCEDURE loadFile(in direccion varchar)\n"
                + "LANGUAGE plpgsql\n"
                + "AS $$\n"
                + "Begin\n"
                + "EXECUTE 'drop table if exists Relacion;\n"
                + "	create table Relacion(\n"
                + "	CUENTA INT not null,\n"
                + "	CARRERA VARCHAR,\n"
                + "	FACULTAD VARCHAR,\n"
                + "	MATERIA VARCHAR,\n"
                + "	NOMBRE_MATERIA VARCHAR,\n"
                + "	SECCION INT not null,\n"
                + "	HORA_CLASE VARCHAR,\n"
                + "	AULA VARCHAR,\n"
                + "	PRIMARY KEY(CUENTA, SECCION)\n"
                + "	)';\n"
                + "EXECUTE format(\n"
                + "        'copy Relacion(CUENTA,CARRERA,FACULTAD,MATERIA,NOMBRE_MATERIA,SECCION,HORA_CLASE,AULA) \n"
                + "FROM %L \n"
                + " WITH (FORMAT CSV, ENCODING ''ISO-8859-1'', HEADER);',direccion);\n"
                + "\n"
                + "INSERT INTO Alumno\n"
                + "SELECT distinct CUENTA, CARRERA, replace(FACULTAD, '$', 'Ñ')\n"
                + "FROM Relacion\n"
                + "ON CONFLICT (CUENTA) DO NOTHING;\n"
                + "\n"
                + "INSERT INTO Curso\n"
                + "SELECT distinct MATERIA,replace(NOMBRE_MATERIA, '$', 'Ñ')\n"
                + "FROM Relacion\n"
                + "ON CONFLICT (MATERIA) DO NOTHING;\n"
                + "\n"
                + "INSERT INTO Seccion\n"
                + "select distinct SECCION, HORA_CLASE, MATERIA,AULA, NULL\n"
                + "from Relacion\n"
                + "ON CONFLICT (SECCION) DO NOTHING;\n"
                + "\n"
                + "INSERT INTO SeccionesXAlumno\n"
                + "Select SECCION, CUENTA\n"
                + "from Relacion\n"
                + "ON CONFLICT (SECCION, CUENTA) DO NOTHING;\n"
                + "\n"
                + "EXECUTE 'drop table Relacion';\n"
                + "END $$;\n"
                + "\n"
                + "Call loadfile('" + this.file.getPath() + "');";
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection.createStatement().execute(sqlScript1);
            connection.createStatement().execute(sqlScript2);
            System.out.println("funciono");

        } catch (Exception ex) {
        }
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

    public void executeSqlScript(Connection c, String sqlScript) {
        try (Statement statement = connection.createStatement()) {
            String[] sqlStatements = sqlScript.split(";");

            for (String sql : sqlStatements) {
                if (!sql.trim().isEmpty()) {
                    statement.execute(sql.trim());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar el script SQL");
            e.printStackTrace();
        }
    }

    public void openFile(java.io.File archivo, int flag) {
        this.file = archivo;

        if (flag == 1) {//si son los datos del alumno
            FileWriter fw = null;
            BufferedWriter bw = null;
            System.out.println("----------ALUMNOS---------");
            if (file.exists()) {
                try {
                    Scanner sc = new Scanner(new FileInputStream(file));
                    metadata = sc.nextLine();
                    metadata = metadata.replace(' ', '_');

                    int tam1 = metadata.length() - 1;
                    char c = metadata.charAt(tam1);

                    if (c == ',') {
                        metadata = metadata.substring(0, tam1);
                    }

                    System.out.println(metadata);
                    String s = metadata + "\n";

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
                            s += record + "\n";
                            registros.add(record);

                        }
                        fw = new FileWriter(archivo, false);
                        bw = new BufferedWriter(fw);
                        bw.write(s);
                        bw.flush();
                    }
                    bw.close();
                    fw.close();
                    sc.close();
                } catch (Exception ex) {
                }
            }
        }
    }
}
