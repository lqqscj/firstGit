package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Manager;
import com.sql.SqlHelper;
import com.tools.MyFuns;

public class InsertManager extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public InsertManager() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String manager_id 	= request.getParameter("manager_id");
		String manager_name	= request.getParameter("manager_name");
		String manager_pwd	= request.getParameter("manager_pwd");
		
		Manager manager = new Manager();
		manager.setManager_id(MyFuns.convert2Utf8(manager_id));
		manager.setManager_name(MyFuns.convert2Utf8(manager_name));
		manager.setManager_pwd(manager_pwd);
		
		if(manager.getManager_id()==null || "".equals(manager.getManager_id())
			||manager.getManager_name()==null || "".equals(manager.getManager_name())	
			||manager.getManager_pwd()==null || "".equals(manager.getManager_pwd())
		){
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE></TITLE></HEAD>");
			out.println("  <BODY>");
			out.print("请把信息输入完整。<a href=\"addManager.jsp\">返回重新输入。</a>");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
		}else{
			SqlHelper sqlHelper = new SqlHelper();
			if(sqlHelper.queryManagerById(manager.getManager_id())!=null){
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
				out.println("<HTML>");
				out.println("  <HEAD><TITLE></TITLE></HEAD>");
				out.println("  <BODY>");
				out.print("用户名已经存在。<a href=\"addManager.jsp\">返回重新输入。</a>");
				out.println("  </BODY>");
				out.println("</HTML>");
				out.flush();
				out.close();
				sqlHelper.destroy();
			}else{
				boolean b = sqlHelper.insertManager(manager);
				sqlHelper.destroy();
				response.sendRedirect("managerList.jsp");
			}
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
