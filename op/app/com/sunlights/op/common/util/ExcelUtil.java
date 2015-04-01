package com.sunlights.op.common.util;

import com.google.common.io.Files;
import com.sunlights.common.MsgCode;
import com.sunlights.common.exceptions.ConverterException;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.op.dto.BaseXlsDto;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import play.Logger;
import play.mvc.Http;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guxuelong on 2014/12/16.
 */
public class ExcelUtil {
    private static List<String> xlsType = new ArrayList<>();

    static {
        xlsType.add("xls");
        xlsType.add("XLS");
    }

    /**
     * 读取excel文件内容
     *
     * @param file
     * @param fileType
     * @param xlsDto
     * @return
     * @throws Exception
     */
    public static List<BaseXlsDto> readExcel(File file, String fileType, BaseXlsDto xlsDto) throws Exception {
        if (xlsType.contains(fileType)) {
            return readXls(file, xlsDto);
        }
        return readXlsx(file, xlsDto);
    }


    /**
     *  * 读取xls文件内容
     *  *
     *  * @return List<T>对象
     *  * @throws IOException
     *  *    输入/输出(i/o)异常
     *  
     */
    private static List<BaseXlsDto> readXls(File file, BaseXlsDto xlsDto) throws Exception {
        InputStream is = new FileInputStream(file);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<BaseXlsDto> list = new ArrayList<>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = xlsDto.getStartRow(); rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                BaseXlsDto xlsDtoTemp = xlsDto.getClass().newInstance();
                Map<String, Object> map = setMapValues(xlsDto, hssfRow);
                try {
                    ConverterUtil.convertMap2Object(map, xlsDtoTemp);
                } catch (ConverterException e) {
                    Logger.info("转换map2bean失败，请检查定义的xlsDto的格式是否正确！");
                }
                list.add(xlsDtoTemp);
            }
        }
        return list;
    }




    /**
     *  * 读取xls文件内容
     *  *
     *  * @return List<T>对象
     *  * @throws IOException
     *  *    输入/输出(i/o)异常
     *  
     */
    private static List<BaseXlsDto> readXlsx(File file, BaseXlsDto xlsDto) throws Exception {
        InputStream is = new FileInputStream(file);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        List<BaseXlsDto> list = new ArrayList<>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = xlsDto.getStartRow(); rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow == null) {
                    continue;
                }
                BaseXlsDto xlsDtoTemp = xlsDto.getClass().newInstance();
                Map<String, Object> map = setMapValues(xlsDto, xssfRow);
                try {
                    ConverterUtil.convertMap2Object(map, xlsDtoTemp);
                } catch (ConverterException e) {
                    Logger.info("转换map2bean失败，请检查定义的xlsDto的格式是否正确！");
                }
                list.add(xlsDtoTemp);
            }
        }
        return list;
    }



    private static Map<String, Object> setMapValues(BaseXlsDto xlsDto,Row row) throws Exception {
        List<String> nameList = xlsDto.getNameList();
        List<String[]> nameAndRowNoList = xlsDto.getNameAndRowNoList();
        Map<String, Object> map = new HashMap<>();
        if(!nameList.isEmpty()) {
            for (int i = 0; i < xlsDto.getLength(); i++) {
                map.put(nameList.get(i), getValue(row.getCell(i)));
            }
            return map;
        }
        if(!nameAndRowNoList.isEmpty()){
            for (int i = 0; i < xlsDto.getLength(); i++) {
                String[] strings = nameAndRowNoList.get(i);
                map.put(strings[0], getValue(row.getCell(Integer.valueOf(strings[1]))));
            }
        }
        return map;
    }

    /**
     *  * 得到Excel表中的值
     *  *
     *  * @param cell
     *  *     Excel中的每一个格子
     *  * @return Excel中每一个格子中的值
     *  
     */
    @SuppressWarnings("static-access")
    private static String getValue(Cell cell) {
        if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf(cell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(cell.getStringCellValue());
        }
    }


    public static MessageVo uploadExcel(Http.Request request) {
        Logger.info("上传文件");
        Http.RequestBody body = request.body();
        MessageVo messageVo = new MessageVo();
        Http.MultipartFormData.FilePart orFile = null;
        try {
            if (body.asMultipartFormData() != null) {
                Http.MultipartFormData multipartFormData = body.asMultipartFormData();
                List<Http.MultipartFormData.FilePart> files = multipartFormData.getFiles();
                if (files != null) {
                    for (Http.MultipartFormData.FilePart file : files) {
                        String fileName = file.getFilename();
                        Logger.info("[fileFile.getName()]" + fileName + " key = " + file.getKey());
                        Logger.info("[fileFile.type]:" + Files.getFileExtension(fileName));
                        String fileType = Files.getFileExtension(fileName);
                        if(!xlsType.contains(fileType)){
                            Logger.info("上传文件格式不正确");
                            messageVo.setMessage(new Message(MsgCode.OPERATE_FAILURE));
                            messageVo.setValue("上传文件格式不正确");
                            return messageVo;
                        }
                        orFile = file;
                    }
                }
            }
        } catch (Exception e) {
            Logger.error("上传文件失败", e);
            messageVo.setMessage(new Message(MsgCode.OPERATE_FAILURE));
            messageVo.setValue("上传文件失败");
            return messageVo;
        }

        messageVo.setMessage(new Message(MsgCode.OPERATE_SUCCESS));
        messageVo.setValue(orFile);
        return messageVo;
    }
}
