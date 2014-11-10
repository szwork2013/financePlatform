package com.sunlights.common.dal;


import models.CodeMstr;

import javax.persistence.Query;
import java.util.List;


/**
* <p>Project: fsp</p>
* <p>Title: CodeMstrDao.java</p>
* <p>Description: </p>
* <p>Copyright (c) 2014 Sunlights.cc</p>
* <p>All Rights Reserved.</p>
*
* @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
*/
public class CodeMstrDao extends EntityBaseDao{

    public List<CodeMstr> getAllCodeMstrList(){
        List<CodeMstr> list = findAll(CodeMstr.class);
        return list;
    }

    public CodeMstr getCodeMstrByCodeCatAbbr(String codeCatAbbr){
        Query query = em.createQuery("select cm from CodeMstr cm where cm.codeCat||cm.codeAbbr = ?0 order by cm.codeSeq", CodeMstr.class);
        query.setParameter(0, codeCatAbbr);
        List<CodeMstr> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<CodeMstr> getCodeMstrByCodeCat(String codeCat){
        Query query = em.createQuery("select cm from CodeMstr cm where cm.codeCat = ?0 order by cm.codeSeq", CodeMstr.class);
        query.setParameter(0, codeCat);
        List<CodeMstr> list = query.getResultList();
        return list;
    }

    public List<CodeMstr> getCodeMstrParentListByCodeCatAbbr(String parentCodeCatAbbr){
        Query query = em.createQuery("select cm from CodeMstr cm where cm.parentCodeCat||cm.codeAbbr = ?0 order by cm.codeSeq", CodeMstr.class);
        query.setParameter(0, parentCodeCatAbbr);
        List<CodeMstr> list = query.getResultList();
        return list;
    }

}
