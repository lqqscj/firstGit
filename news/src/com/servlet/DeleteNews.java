package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sql.SqlHelper;
/**
 * 删除新闻
 * @author scj
 *
 */
public class DeleteNews extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeleteNews() {
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
		//根据当前的新闻的id删除新闻
		String article_id = request.getParameter("article_id");
		if(null == article_id){//如果接受到的新闻id为空(也就是没有接受到新闻id)
			response.sendRedirect("articleList.jsp");
			return;
		}
		SqlHelper sqlHelper = new SqlHelper();
		boolean b = sqlHelper.deleteNewsById(article_id);//从数据库中删除对应id的新闻
		sqlHelper.destroy();
		if(b){//
			response.sendRedirect("articleList.jsp");
		}else{
			response.sendRedirect("deleteNewsError.jsp");
		}
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
			this.doGet(request, response);
		
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
