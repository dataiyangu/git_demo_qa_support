package com.amount.yeepay.amount;

public interface IAmountDAO {
	
	public int addAmount(Amount amount);
	
	public int delAmountByName(String name);
	
	public int delAmountByCAID(String caid);
	
	public Amount selectAmountByName(String name);
	
	public Amount selectAmountByCAID(String caid);
	
	public int updateAmountByName(Amount amount);
	
	public int updateAmountByCAID(Amount amount);
}
