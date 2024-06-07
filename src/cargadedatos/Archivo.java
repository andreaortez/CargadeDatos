/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cargadedatos;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * /**
 *
 * @author darie
 */
public class Archivo {

    private java.io.File fileRegistros;
    private java.io.File fileSecciones;
    private String metadata;
    private ArrayList<String> registros = new ArrayList();
    private Connection connection;
    private String jdbcUrl;
    private String username;
    private String password;
    private String setup = "";

    public Archivo(java.io.File fileRegistros, java.io.File fileSecciones) {
        this.fileRegistros = fileRegistros;
        this.fileSecciones = fileSecciones;
        System.out.println(fileRegistros.getPath());
        System.out.println(fileSecciones.getPath());
        
        jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
        username = "postgres";
        password = "12345678";

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setup() {
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
                + "/*\n"
                + "copy Relacion(CUENTA,CARRERA,FACULTAD,MATERIA,NOMBRE_MATERIA,SECCION,HORA_CLASE,AULA) \n"
                + "FROM 'C:\\Users\\darie\\Downloads\\OneDrive_1_6-6-2024\\data2.csv' \n"
                + "DELIMITER ','\n"
                + "CSV HEADER;\n"
                + "*/\n"
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
                + "END $$;";

        String sqlScript3 = "CREATE OR REPLACE function Filtro(in pAula_id varchar,in pDias varchar,in pHora varchar,in pFacultad varchar,in pCarrera varchar,in pMateria varchar)\n"
                + "RETURNS TABLE(Cuenta int,Carrera varchar, Facultad varchar, Seccion int, Aula_id varchar, Dias_Habiles varchar, Hora varchar, Materia varchar)  \n"
                + "LANGUAGE plpgsql\n"
                + "AS $$\n"
                + "declare \n"
                + "		condicion varchar = '';\n"
                + "		contador int = 0;\n"
                + "Begin\n"
                + "	/*filtro de aulas*/\n"
                + "	if(pAula_id != 'TODAS') then\n"
                + "		select concat(' where ',condicion, 'aula_id = ''', pAula_id, '''') into condicion;\n"
                + "		contador := 1;\n"
                + "	end if;\n"
                + "	\n"
                + "	/*filtro de dias*/\n"
                + "	if(pDias != 'TODAS') then\n"
                + "		if(contador = 1) then\n"
                + "			select concat(condicion, ' and ') into condicion;\n"
                + "		elsif(contador = 0) then\n"
                + "			select concat('where ' ,condicion) into condicion;\n"
                + "		end if;\n"
                + "		select concat(condicion, 'dias_habiles like ''%', pDias, '%''') into condicion;\n"
                + "		contador := 1;\n"
                + "	end if;\n"
                + "	\n"
                + "	\n"
                + "	/*filtro de hora*/\n"
                + "	if(pHora != 'TODAS') then\n"
                + "		if(contador = 1) then\n"
                + "			select concat(condicion, ' and ') into condicion;\n"
                + "		elsif(contador = 0) then\n"
                + "			select concat('where ' ,condicion) into condicion;\n"
                + "		end if;\n"
                + "		select concat(condicion, 'hora = ''', pHora, '''') into condicion;\n"
                + "		contador := 1;\n"
                + "	end if;\n"
                + "	\n"
                + "	/*filtro de facultad*/\n"
                + "	if(pFacultad != 'TODAS') then\n"
                + "		if(contador = 1) then\n"
                + "			select concat(condicion, ' and ') into condicion;\n"
                + "		elsif(contador = 0) then\n"
                + "			select concat('where ', condicion) into condicion;\n"
                + "		end if;\n"
                + "		select concat(condicion, 'facultad = ''', pFacultad,'''') into condicion;\n"
                + "		contador := 1;\n"
                + "	end if;\n"
                + "	\n"
                + "	/*filtro de carrera*/\n"
                + "	if(pCarrera != 'TODAS') then\n"
                + "		if(contador = 1) then\n"
                + "			select concat(condicion, ' and ') into condicion;\n"
                + "		elsif(contador = 0) then\n"
                + "			select concat('where ', condicion) into condicion;\n"
                + "		end if;\n"
                + "		select concat(condicion, 'carrera = ''',pCarrera,'''') into condicion;\n"
                + "		contador := 1;\n"
                + "	end if;\n"
                + "	\n"
                + "	/*filtro de materia*/\n"
                + "	if(pMateria != 'TODAS') then\n"
                + "		if(contador = 1) then\n"
                + "			select concat(condicion, ' and ') into condicion;\n"
                + "		elsif(contador = 0) then\n"
                + "			select concat('where ', condicion) into condicion;\n"
                + "		end if;\n"
                + "		select concat(condicion, 'carrera = ''',pMateria,'''') into condicion;\n"
                + "		contador := 1;\n"
                + "	end if;\n"
                + "\n"
                + "	return query execute 'select cuenta, carrera, facultad, seccion, aula_id, dias_habiles, hora, materia\n"
                + "	from Alumno natural join SeccionesXAlumno natural Join Seccion ' || condicion;\n"
                + "END $$;";
        String sqlScript4 = "CREATE OR REPLACE PROCEDURE loadSecciones(in direccion varchar)\n"
                + "LANGUAGE plpgsql\n"
                + "AS $$\n"
                + "Begin\n"
                + "EXECUTE 'drop table if exists Relacion;\n"
                + "	create table Relacion(\n"
                + "	horas varchar,\n"
                + "	codigo_materia varchar,\n"
                + "	seccion int primary key,\n"
                + "	codigo_aula varchar,\n"
                + "	dias_habiles varchar\n"
                + "	)';\n"
                + "EXECUTE format(\n"
                + "        'copy Relacion(horas, codigo_materia, seccion, codigo_aula, dias_habiles) \n"
                + "		FROM %L \n"
                + "		DELIMITER '',''\n"
                + "		CSV HEADER\n"
                + "		',direccion);\n"
                + "		\n"
                + "		UPDATE Seccion s\n"
                + "    	SET dias_habiles = r.dias_habiles\n"
                + "    	FROM Relacion r\n"
                + "    	WHERE r.seccion = s.seccion;\n"
                + "END $$;";
        try {
            connection.createStatement().execute(sqlScript1);
            connection.createStatement().execute(sqlScript2);
            connection.createStatement().execute(sqlScript3);
            connection.createStatement().execute(sqlScript4);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void registros() {
        try {
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
            
            connection.createStatement().execute(sqlScript1);
            CallableStatement call;
            call = connection.prepareCall("call loadFile(?)");
            call.setString(1, this.fileRegistros.getPath());
            call.execute();
            call.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void secciones() {

        CallableStatement call;
        try {
            call = connection.prepareCall("call public.loadSecciones(?)");
            call.setString(1, this.fileSecciones.getPath());
            call.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public void openFile() {
        java.io.File file = this.fileRegistros;

        FileWriter fw = null;
        BufferedWriter bw = null;
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

                String s = metadata + "\n";

                String record = new String();
                if (file.exists()) {
                    while (sc.hasNextLine()) {
                        record = sc.nextLine();
                        record = record.replace('Ñ', '$');
                        int tam2 = record.length() - 1;

                        if (record.charAt(tam2) == ',') {
                            record = record.substring(0, tam2);
                        } else if (record.charAt(tam2) == '.') {
                            record = record.substring(0, tam2 - 1);
                        }

                        s += record + "\n";
                        registros.add(record);

                    }
                    fw = new FileWriter(file, false);
                    bw = new BufferedWriter(fw);
                    bw.write(s);
                    bw.flush();
                }
                bw.close();
                fw.close();
                sc.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    public boolean ValidFile(java.io.File file, int num) {
        if (file.exists()) {
            try {
                RandomAccessFile rf = new RandomAccessFile(file, "rw");
                String header = rf.readLine();
                if (header.contains("dias") && num == 1) {
                    JOptionPane.showMessageDialog(null, "Elija un archivo para alumnos", "Cargar Archivo", WARNING_MESSAGE);
                    return false;
                } else if (!header.contains("dias") && num == 0) {
                    JOptionPane.showMessageDialog(null, "Elija un archivo para secciones", "Cargar Archivo", WARNING_MESSAGE);
                    return false;
                }

                rf.close();
            } catch (Exception ex) {
            }
        }
        return true;
    }
}
