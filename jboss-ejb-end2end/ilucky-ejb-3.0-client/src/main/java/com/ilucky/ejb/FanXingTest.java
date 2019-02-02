package com.ilucky.ejb;

public class FanXingTest {
	public static void main(String[] args) {
		
		FanXing<User> thiz = new FanXing<User>();
		
//		System.out.println(thiz.getClass().getGenericSuperclass().getClass());
//		System.out.println(thiz.getClass().getGenericSuperclass().toString());
//		System.out.println(thiz.getClass().getTypeParameters().getClass());
//		System.out.println(thiz.getClass().getGenericSuperclass().getClass());
		
		System.out.println(GenericsUtils.getSuperClassGenricType(thiz.getClass()).getSimpleName());
		// Class<T> clz=(Class<T>)(((ParameterizedType)thiz.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);  
	}
	
	
//	public Class<T> getClz(){  
//		Class<T> clz;  
//	    if (clz==null) {  
//	        clz=(Class<T>)(((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);  
//	    }  
//	    return clz;  
//	}  
	
}
