package com.mybatis.classes;

/**
 * 使用  MapperFactoryBean 来管理 sqlSession 
 * @author Administrator
 *
 */
public interface IClassesDAO {
	// 也可以在这里使用注解配置 sql语句  这样就不用写 映射文件了
	Classes selectClassAndStudentListById(int cid);
	int delClassesBycid(int cid);
}
