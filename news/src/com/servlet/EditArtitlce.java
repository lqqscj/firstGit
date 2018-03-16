package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Article;
import com.sql.SqlHelper;
import com.tools.MyFuns;

public class EditArtitlce extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public EditArtitlce() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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

		String article_title = request.getParameter("article_title");
		String article_date = request.getParameter("article_date");
		String article_content = request.getParameter("content1");
		String nav_id = request.getParameter("nav_id");
		String article_id = request.getParameter("article_id");
		if(article_title == null || article_date == null || nav_id==null || null == article_id){
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE></TITLE></HEAD>");
			out.println("  <BODY>");
			out.print("请将信息输入完整。<a href=\"articleList.jsp\">返回重新尝试。</a>");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
			return ;
		}
		Article article = new Article();
		article.setArticle_content(MyFuns.convert2Utf8(article_content));
		article.setArticle_date(article_date);
		article.setNav_id(nav_id);
		article.setArticle_id(article_id);
		article.setArticle_title(MyFuns.convert2Utf8(article_title));
		SqlHelper sqlHelper = new SqlHelper();
		boolean b = sqlHelper.editArticle(article);
		sqlHelper.destroy();
		if(!b){
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE></TITLE></HEAD>");
			out.println("  <BODY>");
			out.print("修改新闻错误。<a href=\"editArticle.jsp?article_id="+article_id+"\">返回重新输入。</a>");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
		}else{
			response.sendRedirect("articleList.jsp");
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
