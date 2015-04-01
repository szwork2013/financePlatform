package com.sunlights.op.dto;

/**
 * Created by guxuelong on 2014/12/16.
 */
public class ReconcileFroXlsDto extends BaseXlsDto {
    private String dev;
    private String prd;

    {
        String[] str = new String[2];
        str[0]="dev";
        str[1]="0";
        addNameAndRowNo(str);

        str = new String[2];
        str[0]="prd";
        str[1]="2";
        addNameAndRowNo(str);
        setStartRow(1);
    }


    public String getDev() {
        return dev;
    }

    public void setDev(String dev) {
        this.dev = dev;
    }

    public String getPrd() {
        return prd;
    }

    public void setPrd(String prd) {
        this.prd = prd;
    }
}
