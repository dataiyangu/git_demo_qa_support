/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.63
 * Generated at: 2016-07-28 14:51:12 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class amount_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("    <base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath() );
      out.write("/resource/js/jquery-1.11.3.js\"></script>\r\n");
      out.write("    <title>Redis_amout</title>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("jQuery(\"document\").ready(function(){\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\tjQuery(\"#save\").click(function(){\r\n");
      out.write("\t\t//alert(\"123\");\r\n");
      out.write("\t\tvar name = jQuery(\"#name\").val();\r\n");
      out.write("\t\tvar amount = jQuery(\"#amount\").val();\r\n");
      out.write("\t\tvar caid = jQuery(\"#id\").val();\r\n");
      out.write("\t\tjQuery(\"#amount\").attr(\"action\",\"");
      out.print(request.getContextPath() );
      out.write("/updateAmountById.action?name=\"+name+\"&amount=\"+amount+\"&caid=\"+caid);\r\n");
      out.write("\t\tjQuery(\"#amount\").submit();\r\n");
      out.write("\t});\r\n");
      out.write("\tjQuery(\"#search\").click(function(){\r\n");
      out.write("\t\t//alert(\"123\");\r\n");
      out.write("\t\tvar caid = jQuery(\"#id\").val();\r\n");
      out.write("\t\tjQuery(\"#amount\").attr(\"action\",\"");
      out.print(request.getContextPath() );
      out.write("/queryAmountById.action\");\r\n");
      out.write("\t\tjQuery(\"#amount\").submit();\r\n");
      out.write("\t});\r\n");
      out.write("\tjQuery(\"#add\").click(function(){\r\n");
      out.write("\t\t//alert(\"123\");\r\n");
      out.write("\t\tjQuery(\"#amount\").attr(\"action\",\"");
      out.print(request.getContextPath() );
      out.write("/addAmount.action\");\r\n");
      out.write("\t\tjQuery(\"#amount\").submit();\r\n");
      out.write("\t});\r\n");
      out.write("\tjQuery(\"#id\").change(function(){\r\n");
      out.write("\t\tvar caid = jQuery(this).val();\r\n");
      out.write("\t\tjQuery(\"#name\").val(\"stu\"+caid);\r\n");
      out.write("\t\tjQuery(\"#amount\").val(\"99.9\");\r\n");
      out.write("\t});\r\n");
      out.write("})\r\n");
      out.write("function getRedis(){\r\n");
      out.write("\r\n");
      out.write("\tvar caid = jQuery(\"#id\").val();\r\n");
      out.write("//\talert(\"get->\"+name);\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("\t    type:'post',\r\n");
      out.write("\t\turl: \"");
      out.print(request.getContextPath() );
      out.write("/queryAmountById.action?caid=\"+caid, //调用的action  \r\n");
      out.write("\t    data: {}, //请求的参数  \r\n");
      out.write("\t    dataType:'json',\r\n");
      out.write("\t    success:function(data){  \r\n");
      out.write("//\t    \talert(\"msg\"+data);\r\n");
      out.write("\t    \t$(\"#name\").val(data.name);\r\n");
      out.write("\t    \t$(\"#amount\").val(data.svalue);\r\n");
      out.write("\t    }  \r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function delRedis(){\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("\tvar caid = jQuery(\"#id\").val();\r\n");
      out.write("//\talert(\"del->\"+name);\t\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("\t\t\turl: \"");
      out.print(request.getContextPath() );
      out.write("/amount/delAmountById.action?caid=\"+caid, //调用的action  \r\n");
      out.write("\t\t    data: {  }, //请求的参数  \r\n");
      out.write("\t\t    type:'post',\r\n");
      out.write("\t\t    dataType:'json',\r\n");
      out.write("\t\t    success:function(data){  \r\n");
      out.write("//\t\t    \talert(\"msg\"+data);\r\n");
      out.write("\t\t    \t$(\"#dbxl\").html(data);\r\n");
      out.write("\t\t    }  \r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("function delstu(sid){\r\n");
      out.write("\r\n");
      out.write("\t$.ajax({  \r\n");
      out.write("\t    url: \"");
      out.print(request.getContextPath() );
      out.write("/student/delStudentById.action?student.sid=\"+sid, //调用的action  \r\n");
      out.write("\t    data: {  }, //请求的参数  \r\n");
      out.write("\t    type:'post',\r\n");
      out.write("\t    datatype:'json',\r\n");
      out.write("\t    success:function(data){  \r\n");
      out.write("\t    \t//alert(\"msg\"+data);\r\n");
      out.write("\t    \t$(\"#\"+sid).remove();\r\n");
      out.write("\t        $(\"#dbxl\").html(data);\r\n");
      out.write("\t    }  \r\n");
      out.write("\t});\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\t\r\n");
      out.write("  </head>\r\n");
      out.write("  \r\n");
      out.write("  <body>\r\n");
      out.write("  \t <form id=\"amount\" action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/amount/amount.action\" method=\"post\" >\r\n");
      out.write("     \t<table cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#F4F4F4\">\r\n");
      out.write("     \t\t<tr>\r\n");
      out.write("     \t\t\t<td>id</td>\r\n");
      out.write("     \t\t\t<td><input type=\"text\" id=\"id\" name=\"id\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("     \t\t\t<td>name</td>\r\n");
      out.write("     \t\t\t<td><input type=\"text\" id=\"name\" name=\"name\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("     \t\t\t<td>amount</td>\r\n");
      out.write("     \t\t\t<td><input type=\"text\" id=\"amount\" name=\"amount\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${amount}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("     \t\t</tr>\r\n");
      out.write("     \t</table>\r\n");
      out.write("<!--     \t<input type=\"submit\" value=\"全部\"/>  -->\r\n");
      out.write("     \t<input type=\"button\" id=\"search\" value=\"查看\"  />\r\n");
      out.write("     \t<input type=\"button\" id=\"del\" value=\"删除\"  onclick=\"delRedis()\"/>\r\n");
      out.write("     \t<input type=\"button\" id=\"save\" value=\"保存\" />\r\n");
      out.write("     \t<input type=\"button\" id=\"add\" value=\"新增\" />\r\n");
      out.write("     \t<span id=\"dbxl\" ></span>\r\n");
      out.write("     </form>\r\n");
      out.write("     <input type=\"button\" onclick=\"window.location.href='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/amount/amount.action'\" value=\"刷新\"/><br/>\r\n");
      out.write("   ");
      out.write("\r\n");
      out.write("  </body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
