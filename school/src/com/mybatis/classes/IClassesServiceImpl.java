package com.mybatis.classes;

public class IClassesServiceImpl implements IClassesService {

	private IClassesDAO classesDAO;
	
	public void setClassesDAO(IClassesDAO classesDAO) {
		this.classesDAO = classesDAO;
	}

	public int delClassesBycid(int cid) {
		// TODO Auto-generated method stub
		return classesDAO.delClassesBycid(cid);
	}

	public Classes selectClassAndStudentListById(int cid) {
		// TODO Auto-generated method stub
		return classesDAO.selectClassAndStudentListById(cid);
	}

}
