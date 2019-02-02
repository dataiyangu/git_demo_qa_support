package com.amount.yeepay.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amount.yeepay.amount.Amount;
import com.amount.yeepay.amount.IAmountService;

public class AmountAction extends AmountBaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Amount amount;
	
	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	public String amount(){
		HttpServletRequest request=this.getRequest();
		HttpServletResponse resp = this.getResponse();
		return SUCCESS;
	}
	
	public String queryAmountById(){
		try {
			HttpServletRequest request=this.getRequest();
			amount.setCaid( (String)request.getAttribute("caid"));
			IAmountService amountService = (IAmountService)this.getServiceBean("amountService");
			amount = amountService.queryAmountByCAID(amount.getCaid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String delAmountById(){
		try {
			HttpServletRequest request=this.getRequest();
			IAmountService amountService = (IAmountService)this.getServiceBean("amountService");
			int row = amountService.delAmountByCAID(amount.getCaid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String addAmount(){
		try {
			HttpServletRequest request=this.getRequest();
			IAmountService amountService = (IAmountService)this.getServiceBean("amountService");
			int row = amountService.addAmount(amount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String updateAmountById(){
		try {
			HttpServletRequest request=this.getRequest();
			IAmountService amountService = (IAmountService)this.getServiceBean("amountService");
			int row = amountService.updateAmountByCAID(amount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String updateAmountByName(){
		try {
			HttpServletRequest request=this.getRequest();
			IAmountService amountService = (IAmountService)this.getServiceBean("amountService");
			int row = amountService.updateAmountByName(amount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
