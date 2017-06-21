package com.oracle.demo.persistance;

import com.oracle.demo.mapper.CatalogueMapper;
import com.oracle.demo.model.CatalogueModel;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.List;

@Service
public class CatalogueOracleDBAdapter {

    private final String CATALOGUE_TABLE = "catalogue";

    private Connection connection;

    @PostConstruct
    private void postContruct() {
        try {

            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl", "catalogueadmin", "catlogueadminpwd");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
    }

    public CatalogueModel getCatalogueEntry(int id) throws SQLException {

        Statement stmt = null;
        String query = "SELECT * FROM " + CATALOGUE_TABLE +
                " WHERE ITEMID = '" + id + "'";

        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return CatalogueMapper.mapCatalogueModel(rs);
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (stmt != null) { stmt.close(); }
        }

        return null;
    }

    public List<CatalogueModel> getCatalogueEntries() throws SQLException {
        Statement stmt = null;
        String query = "SELECT * FROM " + CATALOGUE_TABLE;

        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return CatalogueMapper.mapCatalogueModels(rs);
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (stmt != null) { stmt.close(); }
        }

        return null;
    }

    public void addCatalogueEntry(CatalogueModel model) throws SQLException {
        Statement stmt = null;
        String query = "INSERT INTO " + CATALOGUE_TABLE +
                " (ITEMNAME, DESCRIPTION, PRICE) VALUES('" +
                model.getName() + "','" + model.getDescription() +
                "'," + model.getPrice() +")";

        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Insert Successful");
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }

    public void deleteCatalogueEntry(String id) throws SQLException {
        Statement stmt = null;
        String query = "DELETE FROM " + CATALOGUE_TABLE +
                " WHERE ITEMID = " + id ;

        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Delete Successful");
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }
}
