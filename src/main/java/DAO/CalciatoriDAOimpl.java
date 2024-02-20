package DAO;

import DatabaseConnection.ConnessioneDatabase;
import javafx.collections.ObservableList;
import model.Calciatore;
import util.DisplayInfo;

import java.sql.*;

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
            pstmt.setObject(5, calciatore.getSesso(),Types.OTHER);
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
    public void modifica(Calciatore calciatore, int idModificare) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "UPDATE calciatore SET nome = ?, cognome = ?, piede = ?, datan = ?, sesso = ?, data_ritiro = ?, nazionalità = ? WHERE codicec = " + idModificare ;
            pstmt = connection.prepareStatement(query);

            // Set the parameters using the corresponding methods based on the data types
            pstmt.setString(1, calciatore.getNome());
            pstmt.setString(2, calciatore.getCognome());
            pstmt.setObject(3,calciatore.getPiede(),Types.OTHER);
            pstmt.setDate(4, java.sql.Date.valueOf(calciatore.getDataNascita()));
            pstmt.setObject(5, calciatore.getSesso(),Types.OTHER);
            // Assuming data_ritiro is of type LocalDate, you need to convert it to java.sql.Date
            pstmt.setDate(6, calciatore.getDataRitiro() != null ? java.sql.Date.valueOf(calciatore.getDataRitiro()) : null);
            pstmt.setString(7, calciatore.getNazionalita());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
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
    public void elimina(int idEliminare) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "DELETE FROM calciatore WHERE codicec = " + idEliminare;
            pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
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
    public ObservableList<DisplayInfo> displaycalciatori() {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "SELECT nome,cognome,";
            pstmt = connection.prepareStatement(query);

            // Set the parameters using the corresponding methods based on the data types

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

}
