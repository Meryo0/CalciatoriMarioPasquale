package DAO;

import DatabaseConnection.ConnessioneDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Calciatore;
import model.MilitanzaCalciatore;
import model.MilitanzaPortiere;
import util.DisplayInfo;
import Types.*;

import java.sql.*;
import java.time.LocalDate;

public class CalciatoriDAOimpl implements CalciatoriDAO {
    @Override
    public void inseriscicalciatore(Calciatore calciatore) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {

            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "INSERT INTO calciatore (nome, cognome, piede, datan, sesso, data_ritiro, nazionalità)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = connection.prepareStatement(query);

            pstmt.setString(1, calciatore.getNome());
            pstmt.setString(2, calciatore.getCognome());
            pstmt.setObject(3,calciatore.getPiede(),Types.OTHER);
            pstmt.setDate(4, Date.valueOf(calciatore.getDataNascita()));
            pstmt.setObject(5, calciatore.getSesso(),Types.OTHER);

            if (calciatore.getDataRitiro() == null){
                pstmt.setDate(6,null);
            }else {
                pstmt.setDate(6, Date.valueOf(calciatore.getDataRitiro()));
            }
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
    public void aggiungiMilitanzaCalciatore(MilitanzaCalciatore militanzaCalciatore) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {

            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "insert into militanza_calciatore (data_inizio,data_fine,goal_fatti,partite_giocate,codicec,codices)\n" +
                    "values (?,?,?,?,?,?)";
            pstmt = connection.prepareStatement(query);
            pstmt.setDate(1, Date.valueOf(militanzaCalciatore.getDatainizio()));
            if (militanzaCalciatore.getDatafine()== null)
            {
                pstmt.setDate(2, null);
            }else {
                pstmt.setDate(2, Date.valueOf(militanzaCalciatore.getDatafine()));
            }
            pstmt.setInt(3,militanzaCalciatore.getGoalfatti());
            pstmt.setInt(4,militanzaCalciatore.getPartitegiocate());
            pstmt.setInt(5,militanzaCalciatore.getCodicec());
            pstmt.setInt(6,militanzaCalciatore.getCodices());
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
    public void aggiungiMilitanzaPortiere(MilitanzaPortiere militanzaPortiere) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "insert into militanza_portiere (data_inizio,data_fine,goal_fatti,partite_giocate,goal_subiti,codicec,codices)\n" +
                    "values (?,?,?,?,?,?,?)";
            pstmt = connection.prepareStatement(query);
            pstmt.setDate(1, Date.valueOf(militanzaPortiere.getDatainizio()));
            if (militanzaPortiere.getDatafine()== null)
            {
                pstmt.setDate(2, null);
            }else {
                pstmt.setDate(2, Date.valueOf(militanzaPortiere.getDatafine()));
            }
            pstmt.setInt(3,militanzaPortiere.getGoalfatti());
            pstmt.setInt(4,militanzaPortiere.getPartitegiocate());
            pstmt.setInt(5,militanzaPortiere.getGoalsubiti());
            pstmt.setInt(6,militanzaPortiere.getCodicec());
            pstmt.setInt(7,militanzaPortiere.getCodices());
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
    public int ottienicodicesquadra(String nomes, Genere genere ) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        System.out.println(String.valueOf(genere));
        int codices = -1;
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "SELECT codices FROM SQUADRA WHERE nomes = ? and genere = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1,nomes);
            pstmt.setObject(2,genere,Types.OTHER);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                codices = res.getInt("codices");
            } else {
                System.out.println("nessun codice trovato");
            }

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
        return codices;
    }
    public int ottienicodicecalciatore(Calciatore calciatore) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        int codicec = -1;
        try {

            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "SELECT codicec FROM calciatore WHERE nome = ? and cognome = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, calciatore.getNome());
            pstmt.setString(2,calciatore.getCognome());
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                codicec = res.getInt("codicec");
            } else {
                System.out.println("nessun codice trovato");
            }


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
        return codicec;
    }
    

    @Override
    public void modificacalciatore(Calciatore calciatore, int idModificare) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "UPDATE calciatore SET nome = ?, cognome = ?, piede = ?, datan = ?, sesso = ?, data_ritiro = ?, nazionalità = ? WHERE codicec = " + idModificare ;
            pstmt = connection.prepareStatement(query);

            pstmt.setString(1, calciatore.getNome());
            pstmt.setString(2, calciatore.getCognome());
            pstmt.setObject(3,calciatore.getPiede(),Types.OTHER);
            pstmt.setDate(4, Date.valueOf(calciatore.getDataNascita()));
            pstmt.setObject(5, calciatore.getSesso(),Types.OTHER);
            pstmt.setDate(6, calciatore.getDataRitiro() != null ? Date.valueOf(calciatore.getDataRitiro()) : null);
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
    public void eliminacalciatore(int idEliminare) {
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
    public void eliminaruolo(int idEliminare) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "DELETE FROM ricopre WHERE codicec = " + idEliminare;
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
    public void eliminafeature(int idEliminare) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "DELETE FROM possiedef WHERE codicec = " + idEliminare;
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
    public void eliminavincetrofeo(int idEliminare) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "DELETE FROM vince_giocatore WHERE codicec = " + idEliminare;
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
    public void eliminamilitanzaportiere(int idEliminare) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "DELETE FROM militanza_portiere WHERE codicec = " + idEliminare;
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
    public void eliminamilitanzacalciatore(int idEliminare) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "DELETE FROM militanza_calciatore WHERE codicec = " + idEliminare;
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

    public void aggiungiruolo(Posizione posizione, int codicec) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmtcontr = null;

        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String querycontrollo = "SELECT FROM ricopre WHERE codicec = ? and ruolo = ?";
            pstmtcontr = connection.prepareStatement(querycontrollo);
            pstmtcontr.setInt(1, codicec);
            pstmtcontr.setObject(2, posizione, Types.OTHER);
            ResultSet resultSet = pstmtcontr.executeQuery();
            if(!resultSet.next()) {
                String query = "INSERT INTO ricopre VALUES (?, ?)";
                pstmt = connection.prepareStatement(query);
                pstmt.setInt(1, codicec);
                pstmt.setObject(2, posizione, Types.OTHER); // Assuming Posizione is stored as an object in the database
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
        } finally {
            // Close the connection and PreparedStatement in a finally block
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void eliminaRuolo(int codicec) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "DELETE FROM ricopre WHERE codicec = ? ";

            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, codicec);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
        } finally {
            // Close the connection and PreparedStatement in a finally block
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void eliminaRicopre(int codicec, Posizione posizione) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "DELETE FROM ricopre WHERE codicec = ? AND ruolo = ?";

            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, codicec);
            pstmt.setObject(2,posizione,Types.OTHER); // Assuming Posizione is an enum

            pstmt.executeUpdate();
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
        } finally {
            // Close the connection and PreparedStatement in a finally block
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
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
                    Boolean onlyone = Boolean.TRUE;
                    militanzasquadre = militanzasquadre + ressquadre.getString("nomes");
                    if (!ressquadre.isLast()) {
                        onlyone = Boolean.FALSE;
                        militanzasquadre = militanzasquadre + ", ";
                    } else {
                        if(!onlyone){
                            militanzasquadre = militanzasquadre + ";";
                        }

                    }
                }
                String queryruolo = "SELECT ruolo FROM ricopre WHERE codicec = ?";
                PreparedStatement pstmtruolo = connection.prepareStatement(queryruolo);
                pstmtruolo.setInt(1, codicec); // Assuming codicec is an integer, adjust accordingly
                ResultSet resruolo = pstmtruolo.executeQuery(); // Fix the typo here

                String allruoli = "";
                while (resruolo.next()) {
                    Boolean onlyone = Boolean.TRUE;
                    allruoli = allruoli + resruolo.getString("ruolo");
                    if (!resruolo.isLast()) {
                        onlyone = Boolean.FALSE;
                        allruoli = allruoli + ", ";
                    } else {
                        if(!onlyone){
                            allruoli = allruoli + ";";
                        }
                    }
                }
                resgs.next();
                resgf.next();
                Date dataNascita = res1.getDate("datan");
                LocalDate localDataNascita = (dataNascita != null) ? dataNascita.toLocalDate() : null;
                Date dataRitiro = res1.getDate("data_ritiro");
                LocalDate localDataRitiro = (dataRitiro != null) ? dataRitiro.toLocalDate() : null;
                DisplayInfo rigainfo = new DisplayInfo(codicec, res1.getString("nome"), res1.getString("cognome"), Piede.valueOf(res1.getString("piede")),
                        Sesso.valueOf(res1.getString("sesso")), localDataNascita, localDataRitiro,
                        res1.getString("nazionalità"), militanzasquadre, resgf.getInt("goal_fatti"), resgf.getInt("partite_giocate"),
                        resgs.getInt("goal_subiti"),allruoli);

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
