package com.amount.yeepay.amount;

public interface IAmountService {
	
	public int delAmountByCAID(String caid);
	
	public int addAmount(Amount amount);
	
	public int updateAmountByCAID(Amount amount);
	
	public int updateAmountByName(Amount amount);
	
	public Amount queryAmountByCAID(String caid);
	
	public Amount queryAmountByName(String name);
}
