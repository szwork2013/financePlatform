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

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import play.Logger;
import play.api.Play;
import play.db.jpa.Transactional;
import play.test.WithApplication;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Transactional(value = "test")
public abstract class DBUnitBasedTest extends WithApplication {
    private String path = "test/tablexml/";

    private DbTestUtil dbTestUtil = DbTestUtil.getInstance();
    private IDatabaseConnection databaseConnection;
    private List<String> rollBackTableNameList = Lists.newArrayList();
    private String prepareXml;


    @Override
    public void startPlay() {
        super.startPlay();

        try {
            databaseConnection = dbTestUtil.getIDatabaseConnection();
            prepareData(prepareXml);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareData(String prepareXml) throws Exception{
        if (StringUtils.isEmpty(prepareXml)) {
            return;
        }
        File file = Play.getFile(path + prepareXml, Play.current());
        FileInputStream fileInputStream = new FileInputStream(file);

        ReplacementDataSet createDataSet = new ReplacementDataSet(new FlatXmlDataSet(fileInputStream));
        DatabaseOperation.INSERT.execute(databaseConnection, createDataSet);
    }

    @Override
    public void stopPlay() {
        super.stopPlay();

        try {
            QueryDataSet dataSet = dbTestUtil.getQueryDataSet(databaseConnection, rollBackTableNameList);
            if (dataSet == null) {
                return;
            }
            ReplacementDataSet createDataSet = new ReplacementDataSet(dataSet);
            DatabaseOperation.DELETE.execute(databaseConnection, createDataSet);
            Logger.info(">>数据删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (databaseConnection != null) {
                try {
                    databaseConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 判断期望中的表数据与数据库中数据是否一致
     * @param expectedDataSet
     * @param tableName
     * @param columns
     * @throws Exception
     */
    public void assertDataSet(IDataSet expectedDataSet, String tableName, String columns) throws Exception {
        String actualQuery = "select " + columns + " from " + tableName;
        dbTestUtil.assertDataSet(tableName, actualQuery, expectedDataSet, databaseConnection);
    }

    /**
     * 读取xml数据
     * @param xmlName
     * @return
     * @throws Exception
     */
    public IDataSet getExpectedDataSet(String xmlName) throws Exception{
        File file = Play.getFile(path + xmlName, Play.current());
        IDataSet expectedDataSet = new FlatXmlDataSet(new FileInputStream(file));
        return expectedDataSet;
    }


    /**
     * 导出表数据形成xml
     * @param tableNameList
     * @param resultFile
     * @throws Exception
     */
    public void exportData(List<String> tableNameList, String resultFile) throws Exception {
        File file = Play.getFile(path + resultFile, Play.current());

        dbTestUtil.exportData(databaseConnection, tableNameList, resultFile);
    }


    /**
     * 需要回滚的表
     * @param tableNames
     */
    public void needRollbackData(String... tableNames){
        rollBackTableNameList = Arrays.asList(tableNames);
    }

}
