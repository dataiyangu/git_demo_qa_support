package com.amount.yeepay.amount;

public class IAmountServiceImpl implements IAmountService {
	
	private IAmountDAO iAmountDAO;
	
	public void setiAmountDAO(IAmountDAO iAmountDAO) {
		this.iAmountDAO = iAmountDAO;
	}

	public int delAmountByCAID(String caid){
		
		return iAmountDAO.delAmountByCAID(caid);
	};
	
	public int addAmount(Amount amount){
		
		return iAmountDAO.addAmount(amount);
	};
	
	public int updateAmountByCAID(Amount amount){
		
		return iAmountDAO.updateAmountByCAID(amount);
	};
	
	public int updateAmountByName(Amount amount){
		
		return iAmountDAO.updateAmountByName(amount);
	}

	@Override
	public Amount queryAmountByCAID(String caid) {
		// TODO Auto-generated method stub
		return iAmountDAO.selectAmountByCAID(caid);
	}

	@Override
	public Amount queryAmountByName(String name) {
		// TODO Auto-generated method stub
		return iAmountDAO.selectAmountByName(name);
	};
	
	
	
}
