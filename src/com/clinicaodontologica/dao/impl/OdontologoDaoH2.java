package com.clinicaodontologica.dao.impl;

import com.clinicaodontologica.dao.IDao;
import com.clinicaodontologica.dbconnection.H2Connection;
import com.clinicaodontologica.entity.Odontologo;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ODONTOLOGOS (NUMERO_MATRICULA, NOMBRE, APELLIDO) VALUES (?, ?, ?)");
            preparedStatement.setInt(1, odontologo.getNumeroMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.execute();

            connection.commit();
            LOGGER.info("Se ha registrado el odontólogo: " + odontologo);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();

            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("Tuvimos un problema al registrar el odontólogo");
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException exception) {
                LOGGER.error(exception.getMessage());
                exception.printStackTrace();
            }
        }

        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Odontologo odontologo = crearObjetoOdontologo(resultSet);
                odontologos.add(odontologo);
            }

            LOGGER.info("Listado de odontólogos obtenido: " + odontologos);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException exception) {
                LOGGER.error(exception.getMessage());
                exception.printStackTrace();
            }
        }

        return odontologos;
    }

    private Odontologo crearObjetoOdontologo(ResultSet resultSet) throws SQLException {
        return new Odontologo(resultSet.getInt("NUMERO_MATRICULA"), resultSet.getString("NOMBRE"), resultSet.getString("APELLIDO"));
    }
}