package com.sunlights.common.service;

import com.sunlights.common.dal.CodeMstrDao;
import models.CodeMstr;
import play.Logger;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
* <p>Project: fsp</p>
* <p>Title: CodeMstrService.java</p>
* <p>Description: </p>
* <p>Copyright (c) 2014 Sunlights.cc</p>
* <p>All Rights Reserved.</p>
*
* @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
*/
@Transactional
public class CodeMstrService {

    private CodeMstrDao codeMstrDao = new CodeMstrDao();
    
    private static Map<String, CodeMstr> params = new HashMap<String, CodeMstr>();

    public void loadAllCodeMstr()  {
        if (params.isEmpty()){
            Logger.info(">>> Loading CodeMstr <<<");
            long t1 = System.currentTimeMillis();
            List<CodeMstr> list = getAllCodeMstrList();

            for (CodeMstr codeMstr : list) {
                params.put(codeMstr.getCodeCat() + codeMstr.getCodeAbbr(), codeMstr);
            }
            long t2 = System.currentTimeMillis();
            Logger.info(">>> Loading CodeMstr consumed {} second(s). <<<", (t2 - t1) / 1000.0);
        }
    }

    public String getCodeDescByCodeCatAbbr(String codeCatAbbr)  {
        if (codeCatAbbr == null) {
            return null;
        } else {
            CodeMstr codeMstr = getCodeMstr(codeCatAbbr);
            return codeMstr == null ? null : codeMstr.getCodeVal();
        }
    }

    public void clearAllCodeMstr() {
        Logger.info(">>> Begin to clear all CodeMstr. <<<");
        params.clear();
        Logger.info(">>> End clear all CodeMstr. <<<");
    }

    public CodeMstr getCodeMstr(String codeCatAbbr)  {
        loadAllCodeMstr();
        CodeMstr codeMstr = params.get(codeCatAbbr);
        return codeMstr;
    }

    public List<CodeMstr> getCodeMstrByCodeCat(String codeCat)  {
        List<CodeMstr> list = codeMstrDao.getCodeMstrByCodeCat(codeCat);
        return list;
    }

    public List<CodeMstr> getCodeMstrParentListByCodeCatAbbr(String codeCatAbbr){
        return codeMstrDao.getCodeMstrParentListByCodeCatAbbr(codeCatAbbr);
    }


    public List<CodeMstr> getAllCodeMstrList()  {
        return codeMstrDao.getAllCodeMstrList();
    }


}
