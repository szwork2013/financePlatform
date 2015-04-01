package com.sunlights.op.dto;

/**
 * Created by Administrator on 2014/12/23.
 */
public class ExchangeResultXlsDto extends BaseXlsDto {
    private String id;
    private String exchangeDate;
    private String realName;
    private String mobile;
    private String scene;
    private String status;
    private String bankName;
    private String bankCard;
    private String amt;
    private String exchangedAmount;
    private String balance;
	private String paymentReceiptNo;


    {
        addName("id");
        addName("exchangeDate");
        addName("realName");
        addName("mobile");
        addName("scene");
        addName("status");
        addName("bankName");
        addName("bankCard");
        addName("amt");
        addName("exchangedAmount");
        addName("balance");
        addName("paymentReceiptNo");
        setStartRow(1);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(String exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public String getExchangedAmount () {
		return exchangedAmount;
	}

	public void setExchangedAmount (String exchangedAmount) {
		this.exchangedAmount = exchangedAmount;
	}

	public String getBalance () {
		return balance;
	}

	public void setBalance (String balance) {
		this.balance = balance;
	}

	public String getPaymentReceiptNo () {
		return paymentReceiptNo;
	}

	public void setPaymentReceiptNo (String paymentReceiptNo) {
		this.paymentReceiptNo = paymentReceiptNo;
	}
}
