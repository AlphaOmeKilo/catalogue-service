package com.oracle.demo.mapper;

import com.oracle.demo.model.CatalogueModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogueMapper {

    public static CatalogueModel mapCatalogueModel(ResultSet rs) throws SQLException {

        while(rs.next()) {
            String name = rs.getString("ITEMNAME");
            String description = rs.getString("DESCRIPTION");
            String price = rs.getString("PRICE");

            return new CatalogueModel(name, description, price);
        }

        return null;
    }

    public static List<CatalogueModel> mapCatalogueModels(ResultSet rs) throws SQLException {

        List<CatalogueModel> catalogueModels = new ArrayList<>();

        while(rs.next()) {
            String name = rs.getString("ITEMNAME");
            String description = rs.getString("DESCRIPTION");
            String price = rs.getString("PRICE");

            catalogueModels.add(new CatalogueModel(name, description, price));
        }

        return catalogueModels;

    }
}
