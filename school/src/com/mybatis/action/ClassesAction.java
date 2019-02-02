package com.mybatis.action;

import javax.servlet.http.HttpServletRequest;

import com.mybatis.classes.Classes;
import com.mybatis.classes.IClassesService;
import com.mybatis.common.BaseAction;

public class ClassesAction extends BaseAction {

	private Classes classes;
	
	/**
	 * 根据 id 删除
	 * @return
	 */
	public String delClassesById(){
		try {
			HttpServletRequest request=this.getRequest();
			IClassesService classesService=(IClassesService)this.getServiceBean("classesService");
			int rows=classesService.delClassesBycid(classes.getCid());
			if(rows>0){
				request.setAttribute("msg", "<script type='text/javascript'>alert('删除成功!');</script>");
			}else{
				request.setAttribute("msg", "<script type='text/javascript'>alert('删除失败!');</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 测试 右连接查询  会查询出 班级中拥有的学生 
	 * @return
	 */
	public String queryClassesById(){
		try {
			IClassesService classesService=(IClassesService)this.getServiceBean("classesService");
			classes=classesService.selectClassAndStudentListById(classes.getCid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	
}
