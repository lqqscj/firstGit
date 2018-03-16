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
 /**
  * 
  * 增加新闻的Servlet
  * @author scj
  *
  */
public class InsertArtitlce extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public InsertArtitlce() {
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
		//接收从addArticle.jsp页面传过来的参数（通过参数名获取参数值）
		String article_title 	= request.getParameter("article_title");
		String article_date 	= request.getParameter("article_date");
		String article_content 	= request.getParameter("content1");
		String nav_id 			= request.getParameter("nav_id");
		
		if(article_title == null || article_date == null || nav_id==null){
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE></TITLE></HEAD>");
			out.println("  <BODY>");
			out.print("请将信息输入完整。<a href=\"addArticle.jsp\">返回重新输入。</a>");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
			return ;
		}
		//为新闻的JavaBean赋值
		Article article = new Article();
		article.setArticle_content(MyFuns.convert2Utf8(article_content));
		article.setArticle_date(article_date);
		article.setNav_id(nav_id);
		article.setArticle_id(UUID.randomUUID().toString());
		article.setArticle_title(MyFuns.convert2Utf8(article_title));
		SqlHelper sqlHelper = new SqlHelper();
		boolean b = sqlHelper.insertArticle(article);//将新闻的JavaBean保存再数据库中(将新闻保存再数据库中)，返回是否保存成功的标记
		sqlHelper.destroy();
		if(!b){//如果保存失败
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE></TITLE></HEAD>");
			out.println("  <BODY>");
			out.print("增加新闻错误。<a href=\"addArticle.jsp\">返回重新输入。</a>");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
		}else{
			//新闻保存成功，则跳转到articleList.jsp页面
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
