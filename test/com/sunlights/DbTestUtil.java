package com.sunlights;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: BasedTestCase.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Assert;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DbTestUtil {

    private static DbTestUtil dbTestUtil = new DbTestUtil();
    public static DbTestUtil getInstance(){
        return dbTestUtil;
    }


    /**
     * Get IDatabaseConnection connection
     *
     * @return
     * @throws Exception
     */
    protected IDatabaseConnection getIDatabaseConnection(String driverClass,String url,String db_user,String db_pwd) throws Exception {
        IDatabaseTester databaseTester = new JdbcDatabaseTester(driverClass, url, db_user, db_pwd);
        IDatabaseConnection iconn =  databaseTester.getConnection();
        return iconn;
    }

    /**
     * This is used to assert the data from table and the expected data set. If all of the them has the same records, then the assert is true.
     *
     * @param tableName
     * @param sql
     * @param expectedDataSet
     * @param iconn
     * @throws Exception
     */
    protected void assertDataSet(String tableName, String sql, IDataSet expectedDataSet, IDatabaseConnection iconn) throws Exception {
        printDataAsXml(iconn, tableName, sql);
        QueryDataSet loadedDataSet = new QueryDataSet(iconn);
        loadedDataSet.addTable(tableName, sql);
        ITable table1 = loadedDataSet.getTable(tableName);
        ITable table2 = expectedDataSet.getTable(tableName);
        Assert.assertEquals(table2.getRowCount(), table1.getRowCount());

        DefaultColumnFilter.includedColumnsTable(table1, table2.getTableMetaData().getColumns());
        Assertion.assertEquals(table2, table1);

    }



    /**
     * Create the data set by input stream which read from the dbunit xml data file.
     *
     * @param is
     * @return
     * @throws Exception
     */
    protected ReplacementDataSet createDataSet(InputStream is) throws Exception {
        return new ReplacementDataSet(new FlatXmlDataSet(is));
    }

    /**
     * Convert the data in the ITable to List
     *
     * @param table
     * @return
     * @throws Exception
     */
    private List<Map<?, ?>> getDataFromTable(ITable table) throws Exception {
        List<Map<?, ?>> ret = new ArrayList<Map<?, ?>>();
        int count_table = table.getRowCount();
        if (count_table > 0) {
            Column[] columns = table.getTableMetaData().getColumns();
            for (int i = 0; i < count_table; i++) {
                Map<String, Object> map = new TreeMap<String, Object>();
                for (Column column : columns) {
                    map.put(column.getColumnName().toUpperCase(), table.getValue(i, column.getColumnName()));
                }
                ret.add(map);
            }
        }
        return ret;
    }

    /**
     * Get data by the SQL and table name, then convert the data in the ITable to List
     *
     * @param iconn
     * @param tableName
     * @param sql
     * @return
     * @throws Exception
     */
    protected List<Map<?, ?>> getTableDataFromSql(IDatabaseConnection iconn, String tableName, String sql) throws Exception {
        ITable table = iconn.createQueryTable(tableName, sql);
        return getDataFromTable(table);
    }

    /**
     * Get data by the SQL and table name, then convert the data in the ITable to List. And the print the data as xml data format.
     *
     * @param iconn
     * @param tableName
     * @param sql
     * @throws Exception
     */
    protected void printDataAsXml(IDatabaseConnection iconn, String tableName, String sql) throws Exception {
        List<Map<?, ?>> datas = getTableDataFromSql(iconn, tableName, sql);
        StringBuffer sb;
        for (Map<?, ?> data : datas) {
            sb = new StringBuffer();
            sb.append("<" + tableName.toUpperCase() + " ");
            for (Object o : data.keySet()) {
                sb.append(o + "=\"" + data.get(o) + "\" ");
            }
            sb.append("/>");
            System.out.println(sb.toString());
        }
    }


    /**
     * 查询table数据
     * @param conn
     * @param tableNameList
     * @return
     * @throws Exception
     */
    public QueryDataSet getQueryDataSet(IDatabaseConnection conn, List<String> tableNameList) throws Exception {
        QueryDataSet dataSet = null;
        if (conn == null) {
            return null;
        }
        if (tableNameList == null || tableNameList.size() == 0) {
            return null;
        }
        dataSet = new QueryDataSet(conn);
        for (String tableName : tableNameList) {
            dataSet.addTable(tableName);
        }

        return dataSet;
    }

    /**
     * 导出tableName数据，DBUnit.xml格式
     * @param conn
     * @param tableNameList
     * @param resultFile
     * @throws Exception
     */
    public void exportData(IDatabaseConnection conn, List<String> tableNameList, String resultFile) throws Exception {
        QueryDataSet dataSet = getQueryDataSet(conn, tableNameList);
        if (dataSet != null) {
            FlatXmlDataSet.write(dataSet, new FileOutputStream(resultFile));
        }
    }


}
