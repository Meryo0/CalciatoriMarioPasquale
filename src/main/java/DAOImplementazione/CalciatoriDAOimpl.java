package DAOImplementazione;

import DAO.CalciatoriDAO;
import DatabaseConnection.ConnessioneDatabase;
import Types.Genere;
import Types.Piede;
import Types.Posizione;
import Types.Sesso;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.lang3.StringUtils;
import util.Constant;
import util.DisplayInfo;
import util.DisplayMilitanza;
import util.UserSession;

import java.sql.*;
import java.time.LocalDate;


public class CalciatoriDAOimpl implements CalciatoriDAO {

    @Override
    public void inseriscicalciatore(String nome, String cognome, Piede piede, Sesso sesso, LocalDate dataNascita, LocalDate dataRitiro, String nazionalita) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "INSERT INTO calciatore (nome, cognome, piede, datan, sesso, data_ritiro, nazionalità)" +
                    " VALUES (?, ?, ?, ?, ?,null,?)";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, nome);
            pstmt.setString(2, cognome);
            pstmt.setObject(3, piede, Types.OTHER);
            pstmt.setDate(4, Date.valueOf(dataNascita));
            pstmt.setObject(5, sesso, Types.OTHER);
            pstmt.setString(6, nazionalita);
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

    public void aggiungiMilitanzaCalciatore(LocalDate datainizio, LocalDate datafine, int goalfatti, int partitegiocate, int codicec, int codices) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "insert into militanza_calciatore (data_inizio,data_fine,goal_fatti,partite_giocate,codicec,codices)\n" +
                    "values (?,?,?,?,?,?)";
            pstmt = connection.prepareStatement(query);
            pstmt.setDate(1, Date.valueOf(datainizio));
            if (datafine == null) {
                pstmt.setDate(2, null);
            } else {
                pstmt.setDate(2, Date.valueOf(datafine));
            }
            pstmt.setInt(3, goalfatti);
            pstmt.setInt(4, partitegiocate);
            pstmt.setInt(5, codicec);
            pstmt.setInt(6, codices);
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

    public void aggiungiMilitanzaPortiere(LocalDate datainizio, LocalDate datafine, int goalfatti, int partitegiocate, int goalsubiti, int codicec, int codices) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "insert into militanza_portiere (data_inizio,data_fine,goal_fatti,partite_giocate,goal_subiti,codicec,codices)\n" +
                    "values (?,?,?,?,?,?,?)";
            pstmt = connection.prepareStatement(query);
            pstmt.setDate(1, Date.valueOf(datainizio));
            if (datafine == null) {
                pstmt.setDate(2, null);
            } else {
                pstmt.setDate(2, Date.valueOf(datafine));
            }
            pstmt.setInt(3, goalfatti);
            pstmt.setInt(4, partitegiocate);
            pstmt.setInt(5, goalsubiti);
            pstmt.setInt(6, codicec);
            pstmt.setInt(7, codices);
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

    public int ottienicodicesquadra(String nomes, Genere genere) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        int codices = -1;
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "SELECT codices FROM SQUADRA WHERE nomes = ? and genere = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, nomes);
            pstmt.setObject(2, genere, Types.OTHER);
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

    public int ottienicodicecalciatore(String nome ,String cognome) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        int codicec = -1;
        try {

            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "SELECT codicec FROM calciatore WHERE nome = ? and cognome = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, nome);
            pstmt.setString(2, cognome);
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
    public void modificacalciatore(String nome, String cognome, Piede piede, Sesso sesso, LocalDate dataNascita, LocalDate dataRitiro, String nazionalita, int idModificare) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "UPDATE calciatore SET nome = ?, cognome = ?, piede = ?, datan = ?, sesso = ?, data_ritiro = ?, nazionalità = ? WHERE codicec = " + idModificare;
            pstmt = connection.prepareStatement(query);

            pstmt.setString(1, nome);
            pstmt.setString(2, cognome);
            pstmt.setObject(3, piede, Types.OTHER);
            pstmt.setDate(4, Date.valueOf(dataNascita));
            pstmt.setObject(5, sesso, Types.OTHER);
            pstmt.setDate(6,dataRitiro != null ? Date.valueOf(dataRitiro) : null);
            pstmt.setString(7, nazionalita);
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
    public void eliminacalciatore(int idEliminare) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "DELETE FROM calciatore WHERE codicec = " + idEliminare;
            pstmt = connection.prepareStatement(query);
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

    public void eliminaruolo(int idEliminare) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "DELETE FROM ricopre WHERE codicec = " + idEliminare;
            pstmt = connection.prepareStatement(query);
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

    public void eliminafeature(int idEliminare) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "DELETE FROM possiedef WHERE codicec = " + idEliminare;
            pstmt = connection.prepareStatement(query);
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

    public void eliminavincetrofeo(int idEliminare) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "DELETE FROM vince_giocatore WHERE codicec = " + idEliminare;
            pstmt = connection.prepareStatement(query);
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

    public void eliminamilitanzaportiere(int idEliminare) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "DELETE FROM militanza_portiere WHERE codicec = " + idEliminare;
            pstmt = connection.prepareStatement(query);
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

    public void eliminamilitanzacalciatore(int idEliminare) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query = "DELETE FROM militanza_calciatore WHERE codicec = " + idEliminare;
            pstmt = connection.prepareStatement(query);
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
            if (!resultSet.next()) {
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
            pstmt.setObject(2, posizione, Types.OTHER); // Assuming Posizione is an enum

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
    public ObservableList<DisplayInfo> displayCalciatori(UserSession user) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmtgf = null;
        PreparedStatement pstmtgs = null;
        PreparedStatement pstmtsquadre = null;
        ObservableList<DisplayInfo> list = FXCollections.observableArrayList();

        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query1 = "SELECT codicec, nome, cognome, piede, datan, sesso, data_ritiro, nazionalita, goal_fatti, partite_giocate, goal_subiti, squadre, ruoli FROM v_result_calciatore";

            String queryCalciatore = addDynamicWhereCondition(query1, user);
            pstmt = connection.prepareStatement(queryCalciatore);
            ResultSet res1 = pstmt.executeQuery();

            while (res1.next()) {

                Date dataNascita = res1.getDate("datan");
                LocalDate localDataNascita = (dataNascita != null) ? dataNascita.toLocalDate() : null;
                Date dataRitiro = res1.getDate("data_ritiro");
                LocalDate localDataRitiro = (dataRitiro != null) ? dataRitiro.toLocalDate() : null;
                DisplayInfo rigainfo = null;
                rigainfo = new DisplayInfo(res1.getInt("codicec"), res1.getString("nome"), res1.getString("cognome"), Piede.valueOf(res1.getString("piede")),
                        Sesso.valueOf(res1.getString("sesso")), localDataNascita, localDataRitiro,
                        res1.getString("nazionalita"), res1.getString("squadre"), res1.getInt("goal_fatti"), res1.getInt("partite_giocate"),
                        res1.getInt("goal_subiti"), res1.getString("ruoli"));

                if (rigainfo != null)
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

    /**
     * Metodo che crea una where clause dinamicamente
     * Per poter ampliare le funzionalità tenere presente che la entry.getKey() rappresente la
     * colonna su cui si vuole filtrare mentre la entry.getValue() rappresenta il valore del filtro
     *
     * @param query La query originaria
     * @param user  L'utente che tiene in canna i filtri dinamici
     * @return Resituisce la quuery complessiva
     */
    private String addDynamicWhereCondition(String query, UserSession user) {
        String whereClause = " WHERE 1 = 1";
        StringBuilder sb = new StringBuilder(query).append(whereClause);
        user.getFilters().entrySet().stream().forEach(
                entry -> {
                    if (StringUtils.containsIgnoreCase(query, entry.getKey())) { // inutile perchè c'è sempre la parola nella query, almeno al momento
                        if (StringUtils.equalsIgnoreCase(entry.getKey(), "nome")) {  // anche con il nome del filtro "Cognome" entra perchè contiene la parola nome visto che abbiamo un Contains
                            sb.append(" AND " + "(" + "UPPER(" + StringUtils.lowerCase(entry.getKey()) + ")" + " LIKE " + "UPPER('%" + entry.getValue() + "%')" +
                                    " OR " + "UPPER(" + StringUtils.lowerCase(Constant.FILTER_KEY_COGNOME) + ")" + " LIKE " + "UPPER('%" + entry.getValue() + "%')" + ")");
                        } else if (StringUtils.equalsIgnoreCase(entry.getKey(), "squadre") || StringUtils.equalsIgnoreCase(entry.getKey(), "ruoli")){
                            sb.append(" AND " + "(" + "UPPER(" + StringUtils.lowerCase(entry.getKey()) + ")" + " LIKE " + "UPPER('%" + entry.getValue() + "%'))");
                        } else if (StringUtils.equalsIgnoreCase(entry.getKey(), "datan")) {
                            sb.append(" AND EXTRACT(YEAR FROM AGE(CURRENT_DATE, datan)) = "+entry.getValue());
                        } else {
                            sb.append(" AND " + StringUtils.lowerCase(entry.getKey()) + " = " + "'" + entry.getValue() + "'");
                        }
                    }
                });
        return sb.toString();
    }

    public ObservableList<DisplayMilitanza> displaymilitanze(int codicec) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;
        PreparedStatement pstmt4 = null;
        ObservableList<DisplayMilitanza> list = FXCollections.observableArrayList();

        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            String query1 = "SELECT * FROM militanza_portiere where codicec = ? ";
            pstmt = connection.prepareStatement(query1);
            pstmt.setInt(1, codicec);
            ResultSet res1 = pstmt.executeQuery();
            while (res1.next()) {
                String query2 = "SELECT nomes FROM squadra where codices = " + res1.getString("codices");
                pstmt2 = connection.prepareStatement(query2);
                ResultSet res2 = pstmt2.executeQuery();
                res2.next();
                if (res1.getDate("data_fine") != null) {
                    DisplayMilitanza displayMilitanza = new DisplayMilitanza(res1.getDate("data_inizio").toLocalDate(), res1.getDate("data_fine").toLocalDate(),
                            res1.getInt("goal_fatti"), res1.getInt("partite_giocate"), res1.getInt("goal_subiti"),
                            codicec, res1.getInt("codices"), res2.getString("nomes"));
                    list.add(displayMilitanza);
                } else {
                    DisplayMilitanza displayMilitanza = new DisplayMilitanza(res1.getDate("data_inizio").toLocalDate(), null,
                            res1.getInt("goal_fatti"), res1.getInt("partite_giocate"), res1.getInt("goal_subiti"),
                            codicec, res1.getInt("codices"), res2.getString("nomes"));
                    list.add(displayMilitanza);
                }
            }
            String query3 = "SELECT * FROM militanza_calciatore where codicec = ? ";
            pstmt3 = connection.prepareStatement(query3);
            pstmt3.setInt(1, codicec);
            ResultSet res3 = pstmt3.executeQuery();
            while (res3.next()) {
                String query2 = "SELECT nomes FROM squadra where codices = " + res3.getString("codices");
                pstmt4 = connection.prepareStatement(query2);
                ResultSet res4 = pstmt4.executeQuery();
                res4.next();
                if (res3.getDate("data_fine") != null) {
                    DisplayMilitanza displayMilitanza = new DisplayMilitanza(res3.getDate("data_inizio").toLocalDate(), res3.getDate("data_fine").toLocalDate(),
                            res3.getInt("goal_fatti"), res3.getInt("partite_giocate"), null,
                            codicec, res3.getInt("codices"), res4.getString("nomes"));
                    list.add(displayMilitanza);
                } else {
                    DisplayMilitanza displayMilitanza = new DisplayMilitanza(res3.getDate("data_inizio").toLocalDate(), null,
                            res3.getInt("goal_fatti"), res3.getInt("partite_giocate"), null,
                            codicec, res3.getInt("codices"), res4.getString("nomes"));
                    list.add(displayMilitanza);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        return list;
    }

    public void modificaMilitanza(DisplayMilitanza displayMilitanza, LocalDate di) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        String temp = "'";
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();


            String s1 = "data_inizio = '" + di + "'";
            if(displayMilitanza.getGoalsubiti()!=null){
                String query = "UPDATE militanza_portiere" +
                        " SET data_inizio = ?, data_fine = ?, goal_fatti = ?, partite_giocate = ?, goal_subiti = ?" +
                        " WHERE codicec  = ? AND codices = ? AND "+s1;
                pstmt = connection.prepareStatement(query);
                pstmt.setDate(1, Date.valueOf(displayMilitanza.getDatainizio()));
                if(displayMilitanza.getDatafine()==null)
                    pstmt.setDate(2, null);
                else
                    pstmt.setDate(2, Date.valueOf(displayMilitanza.getDatafine()));
                pstmt.setInt(3, displayMilitanza.getGoalfatti());
                pstmt.setInt(4, displayMilitanza.getPartitegiocate());
                pstmt.setInt(5, displayMilitanza.getGoalsubiti());
                pstmt.setInt(6, displayMilitanza.getCodicec());
                pstmt.setInt(7, displayMilitanza.getCodices());
                pstmt.executeUpdate();
            }else {
                String query2 = "UPDATE militanza_calciatore" +
                        " SET data_inizio = ?, data_fine = ?, goal_fatti = ?, partite_giocate = ?" +
                        " WHERE codicec  = ? AND codices = ? AND "+s1;
                pstmt2 = connection.prepareStatement(query2);
                pstmt2.setDate(1, Date.valueOf(displayMilitanza.getDatainizio()));
                if(displayMilitanza.getDatafine()==null)
                    pstmt2.setDate(2, null);
                else
                    pstmt2.setDate(2, Date.valueOf(displayMilitanza.getDatafine()));
                pstmt2.setInt(3, displayMilitanza.getGoalfatti());
                pstmt2.setInt(4, displayMilitanza.getPartitegiocate());
                pstmt2.setInt(5, displayMilitanza.getCodicec());
                pstmt2.setInt(6, displayMilitanza.getCodices());
                pstmt2.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt2 != null) {
                    pstmt2.close();
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
