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

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.sunlights.common.AppConst;
import com.sunlights.common.vo.MessageVo;
import org.apache.commons.lang3.StringUtils;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import play.Logger;
import play.api.Play;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.FakeApplication;
import play.test.FakeRequest;
import play.test.WithApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.HeaderNames.CONTENT_TYPE;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public abstract class DBUnitBasedTest extends WithApplication {
    private static String driverClass = "org.postgresql.Driver";
    private static String url = "jdbc:postgresql://192.168.0.88:5432/sunlightstest";
    private static String user = "sunlights";
    public static String deviceNo = "23564F8D-B9EC-4BF8-88DA-68D4ACECAF88";
    public static String token = "2EBEC8910382BA815688D77378F2F677";

    private static String pwd = "sunlights";

    private String path = "test/testdata/";
    private DbTestUtil dbTestUtil = DbTestUtil.getInstance();
    private IDatabaseConnection databaseConnection;

    private List<String> rollBackTableNameList = Lists.newArrayList();


    @Override
    protected FakeApplication provideFakeApplication() {
        return fakeApplication(ImmutableMap.of(
                "db.default.driver", driverClass,
                "db.default.url", url,
                "db.default.jndiName", "DefaultDS",
                "jpa.default", "default"
        ));
    }

    @Before
    public void startPlay() {
        super.startPlay();

        try {
            databaseConnection = dbTestUtil.getIDatabaseConnection(driverClass, url, user, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initData(String prepareXml){
        if (StringUtils.isEmpty(prepareXml)) {
            return;
        }
        try {
            File file = Play.getFile(path + prepareXml, Play.current());
            FileInputStream fileInputStream = new FileInputStream(file);

            ReplacementDataSet createDataSet = new ReplacementDataSet(new FlatXmlDataSet(fileInputStream));
            DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, createDataSet);
        } catch (Exception e) {
            Logger.error("数据准备出错了", e);
            e.printStackTrace();
        }
    }

    @After
    public abstract void rollback();

    /**
     * 需要回滚的表
     * @param arrayString
     */
    public void needRollbackData(String arrayString){
        String[] tableNames = arrayString.split(",");
        rollBackTableNameList = Arrays.asList(tableNames);
    }
    
    private void updateToken(){
        String updateToken = "update c_customer_session set update_time = LOCALTIMESTAMP where token = '" + token + "'";
        Connection connect = null;
        Statement statement = null;
        try {
            connect = databaseConnection.getConnection();
            statement = connect.createStatement();
            statement.execute(updateToken);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @After
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
     * @param tableNameListStr 表名字符串，多条以逗号隔开
     * @param resultFile
     * @throws Exception
     */
    public void exportData(String tableNameListStr, String resultFile) {
        File file = Play.getFile(path + resultFile, Play.current());

        List<String> tableNameList = Arrays.asList(tableNameListStr.split(","));
        try {
            dbTestUtil.exportData(databaseConnection, tableNameList, file.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getJsonFile(String jsonName) {
        File file = Play.getFile(path + jsonName, Play.current());
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine().trim());
            }
        } catch (FileNotFoundException e) {
            Logger.debug("文件不存在！");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return buffer.toString();
    }

    private MessageVo toMessageVo(String content) {
        JsonNode jsonNode = Json.parse(content);
        return Json.fromJson(jsonNode, MessageVo.class);
    }

    public void assertMessage(String result, String expected){
        MessageVo resultMessageVo = toMessageVo(result);
        MessageVo expectedMessageVo = toMessageVo(expected);
        assertThat(Json.toJson(resultMessageVo).toString()).isEqualTo(Json.toJson(expectedMessageVo).toString());//此处判断message
    }


    public String getResult(String routes, Map formParams) {
        formParams.put("deviceNo", deviceNo);
        FakeRequest fakeRequest = fakeRequest(POST, routes);
        setRequestHeader(fakeRequest).withFormUrlEncodedBody(formParams);
        Result result = route(fakeRequest);
        Logger.info("result is " + contentAsString(result));
        assertThat(status(result)).isEqualTo(OK);
        return contentAsString(result);
    }

    /**
     * 先创建登录数据，后处理。。。init.xml需要c_customer_sessi
     * @param routes
     * @param formParams
     * @return
     */
    public String getResultWithLogin(String routes, Map formParams) {
        formParams.put("deviceNo", deviceNo);

        FakeRequest fakeRequest = fakeRequest(POST, routes);
        setRequestHeader(fakeRequest).withFormUrlEncodedBody(formParams);

        setRequestToken(fakeRequest);

        Result result = route(fakeRequest);
        Logger.info("result is " + contentAsString(result));
        assertThat(status(result)).isEqualTo(OK);
        return contentAsString(result);
    }

    public void setRequestToken(FakeRequest fakeRequest){
        updateToken();

        Http.Cookie cookie = new Http.Cookie("token", token, null, null, null, false, false);
        fakeRequest.withCookies(cookie);
    }


    public FakeRequest setRequestHeader(final FakeRequest fakeRequest) {
        fakeRequest.withHeader(CONTENT_TYPE, "application/x-www-form-urlencoded");
        fakeRequest.withHeader(AppConst.HEADER_USER_AGENT, "Mozilla/5.0 (iPhone; CPU iPhone OS 7_1 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) Mobile/11D167\\jindoujialicai\\1.4");
        fakeRequest.withHeader(AppConst.HEADER_DEVICE, deviceNo);

        return fakeRequest;
    }



}
