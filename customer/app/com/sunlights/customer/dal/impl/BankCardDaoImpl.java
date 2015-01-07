package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.BankCardDao;
import models.BankCard;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Administrator on 2014/12/19.
 */
public class BankCardDaoImpl extends EntityBaseDao implements BankCardDao {

    @Override
    public BankCard getByCustId(String custId) {
        String sql = "select bc from BankCard bc where bc.customerId = :customerId and bc.deleted = false and bc.loadAllInd = 'N'";
        Query query = em.createQuery(sql, BankCard.class);
        query.setParameter("customerId", custId);
        List<BankCard> bankCardList = query.getResultList();
        if (!bankCardList.isEmpty()){
            return bankCardList.get(0);
        }
        sql = "select bc from BankCard bc where bc.customerId = :customerId and bc.deleted = false and bc.loadAllInd = 'Y'";
        query = em.createQuery(sql, BankCard.class);
        query.setParameter("customerId", custId);
        bankCardList = query.getResultList();
        if (!bankCardList.isEmpty()) {
            return bankCardList.get(0);
        }

        return null;
    }

    @Override
    public BankCard create(BankCard bankCard) {
        return super.create(bankCard);
    }

    @Override
    public void deleteById(Long bankCardId) {
        BankCard bankCard = super.find(BankCard.class, bankCardId);
        super.delete(bankCard);
    }

    @Override
    public List<BankCard> findBankCards(String customerId) {
        String sql = "select bc from BankCard bc where bc.customerId = :customerId and bc.deleted = false";
        Query query = em.createQuery(sql, BankCard.class);
        query.setParameter("customerId", customerId);
        List<BankCard> bankCardList = query.getResultList();
        return bankCardList;
    }

    @Override
    public void updateBankCard(BankCard bankCard) {
        update(bankCard);
    }


    @Override
    public boolean hasBankCard(String bankCardNo) {
        List<BankCard> bankCards = super.findBy(BankCard.class, "bankCardNo", bankCardNo);
        return !bankCards.isEmpty();
    }

}
