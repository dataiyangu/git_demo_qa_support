package com.mybatis.classes;

public interface IClassesService {
	Classes selectClassAndStudentListById(int cid);
	
	int delClassesBycid(int cid);
}
