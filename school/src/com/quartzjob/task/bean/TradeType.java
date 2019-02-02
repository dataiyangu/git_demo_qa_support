package com.quartzjob.task.bean;

public enum TradeType {
	
	incInfo, saveInc, all, saveAll;
	
	public static Integer equals(String type){
		TradeType[] tradeTypes = TradeType.values();
		for (TradeType tradeType : tradeTypes) {
			if (tradeType.name().equals(type)) {
				return tradeType.ordinal();
			}
		}
		return -1;
	}
}
