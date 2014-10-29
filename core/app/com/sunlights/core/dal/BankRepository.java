package com.sunlights.core.dal;

import com.sunlights.common.page.Pager;
import com.sunlights.core.models.Bank;
import com.sunlights.core.vo.BankCardVo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Administrator on 2014/10/28.
 */
public interface BankRepository extends CrudRepository<Bank, Long> {
}
