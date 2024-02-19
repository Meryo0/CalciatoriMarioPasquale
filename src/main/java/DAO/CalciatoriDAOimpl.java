package DAO;

import DatabaseConnection.ConnessioneDatabase;
import model.Calciatore;
import Types.Piede;

import java.sql.*;
import java.util.List;

public class CalciatoriDAOimpl implements CalciatoriDAO {
    @Override
    public void inserisci(Calciatore calciatore) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "INSERT INTO calciatore (nome, cognome, piede, datan, sesso, data_ritiro, nazionalità)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = connection.prepareStatement(query);

            // Set the parameters using the corresponding methods based on the data types
            pstmt.setString(1, calciatore.getNome());
            pstmt.setString(2, calciatore.getCognome());
            pstmt.setObject(3,calciatore.getPiede(),Types.OTHER);
            pstmt.setDate(4, java.sql.Date.valueOf(calciatore.getDataNascita()));
            pstmt.setString(5, calciatore.getSesso());
            // Assuming data_ritiro is of type LocalDate, you need to convert it to java.sql.Date
            pstmt.setDate(6, calciatore.getDataRitiro() != null ? java.sql.Date.valueOf(calciatore.getDataRitiro()) : null);
            pstmt.setString(7, calciatore.getNazionalita());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void modifica(Calciatore calciatore) {

    }

    @Override
    public void elimina(Calciatore calciatore) {

    }

    @Override
    public List<Calciatore> displaycalciatori() {
        return null;
    }
}
