package com.sunlights.op.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guxuelong on 2014/12/16.
 */
public class BaseXlsDto {
    /** excel的字段全部获取 ，放入定义的类的所有的字段名 */
    private List<String> nameList = new ArrayList<>();
    /** excel的字段部分获取 ，放入定义的类的字段名 以及 对应的excel的第几列（需要减一） */
    private List<String[]> nameAndRowNoList = new ArrayList<>();
    private int startRow;
    public int getLength(){
        if(!nameList.isEmpty()){
            return nameList.size();
        }else{
            return nameAndRowNoList.size();
        }
    }
    protected void addName(String name){
        this.nameList.add(name);
    }
    protected void addNameAndRowNo(String[] nameAndRowNo){
        this.nameAndRowNoList.add(nameAndRowNo);
    }
    public List<String> getNameList(){
        return this.nameList;
    }
    public List<String[]> getNameAndRowNoList(){
        return this.nameAndRowNoList;
    }
    public int getStartRow() {
        return startRow;
    }
    protected void setStartRow(int startRow) {
        this.startRow = startRow;
    }
}
