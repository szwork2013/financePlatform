package com.sunlights.op.service.impl;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.service.PageService;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.BankService;
import com.sunlights.op.vo.BankVo;
import models.Bank;

import java.util.List;

/**
 * bank related services
 */

public class BankServiceImpl implements BankService {

	private EntityBaseDao entityBaseDao = new EntityBaseDao();
	private PageService pageService = new PageService();

	@Override
	public List<BankVo> findBanksBy(PageVo pageVo) {
		StringBuilder xsql = new StringBuilder();

		xsql.append(" select new com.sunlights.op.vo.BankVo(b) from Bank b");
		xsql.append(" where 1=1");
		xsql.append(" /~ and b.bankCode like {bankCode} ~/ ");
		xsql.append(" /~ and b.bankName like {bankName} ~/ ");
		xsql.append(" /~ and b.enName like {enName} ~/ ");
		xsql.append(" /~ and b.status = {status} ~/ ");
		xsql.append(" order by b.createdTime desc");

		List<BankVo> bankVos = pageService.findXsqlBy(xsql.toString(), pageVo);
		return bankVos;
	}

	@Override
	public void save(BankVo bankVo) {
		if (hasBank(bankVo)) {
			throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.BANK_CODE_EXIST_ERROR));
		}
		Bank bank = bankVo.convertToBank();
		entityBaseDao.update(bank);
	}

	@Override
	public void delete(BankVo bankVo) {
		Bank bank = entityBaseDao.find(Bank.class, bankVo.getId());
		entityBaseDao.delete(bank);
	}

	private boolean hasBank(BankVo bankVo) {
		StringBuffer jpql = new StringBuffer();
		jpql.append(" select b from Bank b");
		jpql.append(" where b.bankCode = '" + bankVo.getBankCode().trim() + "'");
		if (bankVo.getId() != null) {
			jpql.append(" and b.id <> ").append(bankVo.getId());
		}
		List<Bank> banks = entityBaseDao.find(jpql.toString());
		return !banks.isEmpty();
	}
}
