package com.sample.util;

import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.NoSuchColumnException;
import org.dbunit.dataset.NoSuchTableException;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;

@Service
public class DBUnitHelper {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private IDatabaseConnection databaseConnection;

    public void deleteDataSet(IDataSet dataSet) {
        if (dataSet != null) {
            try {
                DatabaseOperation.DELETE.execute(databaseConnection, dataSet);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public IDataSet loadDataSet(String path) {
        try {
            IDataSet dataSet = new FlatXmlDataSetBuilder().build(getClass().getResourceAsStream(path));
            ReplacementDataSet rDataSet = new ReplacementDataSet(dataSet);
            rDataSet.addReplacementObject("[null]", null);
            DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, rDataSet);
            return dataSet;
        } catch (NoSuchTableException e) {
            throw new RuntimeException("Error on read file " + path + ". No such table " + e.getMessage(), e);
        } catch (NoSuchColumnException e) {
            throw new RuntimeException("Error on read file " + path + ". No such column " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error on read file " + path, e);
        }
    }

    @Bean
    public IDatabaseConnection getDatabaseConnection() {
        try {
            return new DatabaseDataSourceConnection(dataSource);
        } catch (SQLException e) {
            throw new RuntimeException("Error initialization DBUnit", e);
        }
    }
}