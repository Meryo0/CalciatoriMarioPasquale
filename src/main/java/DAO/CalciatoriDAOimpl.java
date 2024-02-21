package DAO;

import DatabaseConnection.ConnessioneDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Calciatore;
import util.DisplayInfo;
import Types.Piede;
import Types.Sesso;

import java.sql.*;
import java.time.LocalDate;

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
        PreparedStatement pstmtgf = null;
        PreparedStatement pstmtgs = null;
        PreparedStatement pstmtsquadre = null;
        ObservableList<DisplayInfo> list = FXCollections.observableArrayList();

        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query1 = "SELECT codicec, nome, cognome, piede, datan, sesso, data_ritiro, nazionalità FROM calciatore";
            pstmt = connection.prepareStatement(query1);
            ResultSet res1 = pstmt.executeQuery();

            while (res1.next()) {
                int codicec = res1.getInt("codicec");
                String querygf = "SELECT SUM(goal_fatti) as goal_fatti, SUM(partite_giocate) as partite_giocate " +
                        "FROM (" +
                        "    SELECT SUM(mc.goal_fatti) as goal_fatti, SUM(mc.partite_giocate) as partite_giocate " +
                        "    FROM calciatore c JOIN militanza_calciatore mc ON c.codicec = mc.codicec " +
                        "    WHERE c.codicec = " + codicec +
                        "    UNION " +
                        "    SELECT SUM(mp.goal_fatti) as goal_fatti, SUM(mp.partite_giocate) as partite_giocate " +
                        "    FROM calciatore c JOIN militanza_portiere mp ON c.codicec = mp.codicec " +
                        "    WHERE c.codicec = " + codicec +
                        ")";
                pstmtgf = connection.prepareStatement(querygf);
                ResultSet resgf = pstmtgf.executeQuery();

                String querygs = "SELECT SUM(goal_subiti) as goal_subiti FROM calciatore c JOIN militanza_portiere mp ON c.codicec = mp.codicec WHERE c.codicec = " + codicec;
                pstmtgs = connection.prepareStatement(querygs);
                ResultSet resgs = pstmtgs.executeQuery();

                String querysquadre = "SELECT codices, nomes " +
                        "FROM (" +
                        "    SELECT s.codices, s.nomes FROM squadra s JOIN militanza_calciatore mc ON s.codices = mc.codices " +
                        "    WHERE mc.codicec = " + codicec +
                        "    UNION " +
                        "    SELECT s.codices, s.nomes FROM squadra s JOIN militanza_portiere mp ON s.codices = mp.codices " +
                        "    WHERE mp.codicec = " + codicec +
                        ")";
                pstmtsquadre = connection.prepareStatement(querysquadre);
                ResultSet ressquadre = pstmtsquadre.executeQuery();

                String militanzasquadre = "";
                while (ressquadre.next()) {
                    militanzasquadre = militanzasquadre + ressquadre.getString("nomes");
                    if (!ressquadre.isLast()) {
                        militanzasquadre = militanzasquadre + ", ";
                    } else {
                        militanzasquadre = militanzasquadre + ";";
                    }
                }
                resgs.next();
                resgf.next();
                java.sql.Date dataNascita = res1.getDate("datan");
                LocalDate localDataNascita = (dataNascita != null) ? dataNascita.toLocalDate() : null;
                java.sql.Date dataRitiro = res1.getDate("data_ritiro");
                LocalDate localDataRitiro = (dataRitiro != null) ? dataRitiro.toLocalDate() : null;
                DisplayInfo rigainfo = new DisplayInfo(codicec, res1.getString("nome"), res1.getString("cognome"), Piede.valueOf(res1.getString("piede")),
                        Sesso.valueOf(res1.getString("sesso")), localDataNascita, localDataRitiro,
                        res1.getString("nazionalità"), militanzasquadre, resgf.getInt("goal_fatti"), resgf.getInt("partite_giocate"), resgs.getInt("goal_subiti"));

                list.add(rigainfo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (pstmtgf != null) {
                    pstmtgf.close();
                }
                if (pstmtgs != null) {
                    pstmtgs.close();
                }
                if (pstmtsquadre != null) {
                    pstmtsquadre.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return list;
    }

}
