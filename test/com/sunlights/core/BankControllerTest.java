package com.sunlights.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.sunlights.core.controller.BankController;
import com.sunlights.core.dal.BankRepository;
import com.sunlights.core.models.Bank;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import play.GlobalSettings;
import play.libs.Json;
import play.mvc.Result;
import play.test.WithApplication;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static play.test.Helpers.*;

/**
 * An integration test focused on testing our routes configuration and interactions with our controller.
 * However we can mock repository interactions here so we don't need a real db.
 */
@RunWith(MockitoJUnitRunner.class)
public class BankControllerTest extends WithApplication {

    private static final Long SOME_ID = 1L;
    private BankController bankController;

    @Mock
    private BankRepository bankRepository;

    @Before
    public void setUp() throws Exception {
        bankController = new BankController(bankRepository);

        final GlobalSettings global = new GlobalSettings() {
            @Override
            public <A> A getControllerInstance(Class<A> aClass) {
                return (A) bankController;
            }
        };

        start(fakeApplication(global));
    }

    @Test
    public void hello(){
        final Result result = route(fakeRequest(GET, "/core/test"));
        assertEquals(OK, status(result));
    }

    @Test
    public void findAllBankCards() {
        final Bank bank = new Bank();
        bank.setBankName("ioc");
        bank.setBankCode("0001");
        final List<Bank> banks = new ArrayList<>();
        banks.add(bank);
        when(bankRepository.findAll()).thenReturn(banks);

        final Result result = route(fakeRequest(GET, "/core/banks"));

        assertEquals(OK, status(result));
        String json = contentAsString(result);
        Bank resultBank = Json.fromJson(Json.parse(json).get(0), Bank.class);
        assertEquals("ioc", resultBank.getBankName());
    }

}
