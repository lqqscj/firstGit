package com.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.db.DB;
import com.entity.Article;
import com.entity.ArticlePojo;
import com.entity.Manager;
import com.entity.Nav;

public class SqlHelper {
	private Connection conn=null;
	private int perPage = 10;
	
	public SqlHelper(){
		conn=DB.getConnection();
	}
	public void destroy(){
		if(conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public Manager queryManagerById(String manager_id){
		String sql = "select manager_id,manager_name,manager_pwd from manager where manager_id=?";
		Manager manager = null;
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, manager_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				manager = new Manager();
				manager.setManager_id(rs.getString("manager_id"));
				manager.setManager_name(rs.getString("manager_name"));
				manager.setManager_pwd(rs.getString("manager_pwd"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return manager;
	}
	public Manager queryManagerByIdAndPwd(Manager m){
		String sql = "select manager_id,manager_name,manager_pwd from manager where manager_id=? and manager_pwd=?";
		Manager manager = null;
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, m.getManager_id());
			ps.setString(2, m.getManager_pwd());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				manager = new Manager();
				manager.setManager_id(rs.getString("manager_id"));
				manager.setManager_name(rs.getString("manager_name"));
				manager.setManager_pwd(rs.getString("manager_pwd"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return manager;
	}
	public boolean insertManager(Manager manager){
		String sql = "insert into manager(manager_id,manager_name,manager_pwd)values(?,?,?)";
		boolean b=false;
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, manager.getManager_id());
			ps.setString(2, manager.getManager_name());
			ps.setString(3, manager.getManager_pwd());
			ps.executeUpdate();
			b = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return b;
	}
	public boolean insertNav(Nav nav){
		String sql = "insert into nav(nav_id,nav_name,nav_feight)values(?,?,?)";
		boolean b=false;
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nav.getNav_id()		);
			ps.setString(2, nav.getNav_name()	);
			ps.setInt	(3, nav.getNav_feight()	);
			ps.executeUpdate();
			b = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return b;
	}
	public boolean deleteManager(Manager manager){
		String sql = "delete from manager where manager_id=?";
		boolean b=false;
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, manager.getManager_id());
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return b;
	}
	public boolean updateManagerPwd(Manager manager){
		String sql = "update manager set manager_pwd=? where manager_id=?";
		boolean b=false;
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, manager.getManager_pwd());
			ps.setString(1, manager.getManager_id());
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return b;
	}
	public boolean updateManagerName(Manager manager){
		String sql = "update manager set manager_name=? where manager_id=?";
		boolean b=false;
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, manager.getManager_name());
			ps.setString(2, manager.getManager_id());
			ps.executeUpdate();
			b=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return b;
	}
	public boolean updateManagerNameAndPwd(Manager manager){
		String sql = "update manager set manager_name=?,manager_pwd=? where manager_id=?";
		boolean b=false;
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, manager.getManager_name());
			ps.setString(2, manager.getManager_pwd());
			ps.setString(3, manager.getManager_id());
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return b;
	}
	public List<Manager> queryManagerAll(){
		List <Manager>list = new ArrayList();
		String sql = "select manager_id,manager_name from manager";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Manager manager = new Manager();
				manager.setManager_id(rs.getString("manager_id"));
				manager.setManager_name(rs.getString("manager_name"));
				list.add(manager);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	public List<Nav> queryNavAll() {
		List <Nav> list = new ArrayList();
		String sql = "select nav_id,nav_name,nav_feight from nav order by nav_feight asc";
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Nav nav = new Nav();
				nav.setNav_id(rs.getString("nav_id"));
				nav.setNav_name(rs.getString("nav_name"));
				nav.setNav_feight(rs.getInt("nav_feight"));
				list.add(nav);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	public Nav queryNavById(String nav_id) {
		String sql = "select nav_id,nav_name,nav_feight from nav where nav_id=? limit 0,1";
		Nav nav = null;
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nav_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				nav = new Nav();
				nav.setNav_id(rs.getString("nav_id"));
				nav.setNav_name(rs.getString("nav_name"));
				nav.setNav_feight(rs.getInt("nav_feight"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return nav;
	}
	public boolean updateNav(Nav nav) {
		String sql = "update nav set nav_name=?,nav_feight=? where nav_id=?";
		boolean b = false;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nav.getNav_name());
			ps.setInt(2, nav.getNav_feight());
			ps.setString(3, nav.getNav_id());
			ps.executeUpdate();
			b = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	/**
	 * 删除新闻类别
	 * @param nav
	 */
	public void deleteNav(Nav nav) {
		//判断一下在新闻表中是否引用这个类别
		String sql = "delete from nav where nav_id=?";
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nav.getNav_id());
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 根据新闻类别查询新闻信息
	 * @param nav_id
	 * @return
	 */
	public List<Article> queryArticleByNav_id(String nav_id) {
		String sql = "select article_id,article_title,article_date,article_content,nav_id from article where nav_id=? order by article_date desc";
		List <Article>list = new ArrayList();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nav_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Article article = new Article();
				article.setArticle_content(rs.getString("article_content"));
				article.setArticle_date(rs.getString("article_date"));
				article.setArticle_id(rs.getString("article_id"));
				article.setArticle_title(rs.getString("article_title"));
				article.setNav_id(rs.getString("nav_id"));
				list.add(article);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	public List<Article> queryArticleByNav_id4Index(String nav_id) {
		String sql = "select article_id,article_title,"
				+ "article_date,nav_id "
				+ "from article "
				+ "where nav_id=? order by article_date desc limit 0,10";
		List <Article>list = new ArrayList();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nav_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Article article = new Article();
				article.setArticle_date(rs.getString("article_date"));
				article.setArticle_id(rs.getString("article_id"));
				article.setArticle_title(rs.getString("article_title"));
				article.setNav_id(rs.getString("nav_id"));
				list.add(article);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 保存新闻（增加）
	 * @param article
	 * @return
	 */
	public boolean insertArticle(Article article) {
		String sql = "insert into article(article_id,article_title,article_date,article_content,nav_id)values(?,?,?,?,?)";
		boolean b = false;
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, article.getArticle_id());
			ps.setString(2, article.getArticle_title());
			ps.setString(3, article.getArticle_date());
			ps.setString(4, article.getArticle_content());
			ps.setString(5, article.getNav_id());
			ps.executeUpdate();
			b = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return b;
	}
	
	public List<ArticlePojo> queryArticle() {
		String sql = "select article_id,article_title,article_date,nav_name "
				+ "from article,nav "
				+ "where article.nav_id=nav.nav_id "
				+ "order by article_date desc";
		List <ArticlePojo>list = new ArrayList();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ArticlePojo art = new ArticlePojo();
				art.setArticle_date(rs.getString("article_date"));
				art.setArticle_title(rs.getString("article_title"));
				art.setArticle_id(rs.getString("article_id"));
				art.setNav_name(rs.getString("nav_name"));
				list.add(art);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Map> queryArticleMap() {
		String sql = "select article_id,article_title,article_date,nav_name "
				+ "from article,nav "
				+ "where article.nav_id=nav.nav_id "
				+ "order by article_date desc";
		
		List <Map>list = new ArrayList();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Map map = new HashMap();
				map.put("article_date", 	rs.getString("article_date")	);
				map.put("article_title",	rs.getString("article_title")	);
				map.put("article_id", 		rs.getString("article_id")		);
				map.put("nav_name", 		rs.getString("nav_name")		);
				list.add(map);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	public List<Map> queryArticleMapByPage(int p) {
//		p  start       perPage    ==> start = p*perPage
//		0  0=perPage*0 perPage=3  
//		1  3=perPage*1 perPage=3
//		2  6=perPage*2 perPage=3
//		3  9=perPage*3 perPage=3
		int start = p*perPage;
		String sql = "select article_id,article_title,article_date,nav_name "
				+ "from article,nav "
				+ "where article.nav_id=nav.nav_id "
				+ "order by article_date desc "
				+ "limit "+start+","+perPage;
		
		List <Map>list = new ArrayList();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Map map = new HashMap();
				map.put("article_date", 	rs.getString("article_date")	);
				map.put("article_title",	rs.getString("article_title")	);
				map.put("article_id", 		rs.getString("article_id")		);
				map.put("nav_name", 		rs.getString("nav_name")		);
				list.add(map);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 查询总的新闻条数
	 */
	public int queryArticleCount(){
		String sql = "select count(*) from article";
		try{
			PreparedStatement  ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	public List<Map> queryArticleMapByPage(int p,String nav_id,String article_title) {
		
		int start = p*perPage;
		int ii = start+1;
		String sql = "select article_id,article_title,article_date,nav_name "
				+ "from article,nav "
				+ "where article.nav_id=nav.nav_id ";
		if(nav_id !=null && !"".equals(nav_id)){
			sql += " and nav.nav_id=? ";
		}
		if(article_title !=null && !"".equals(article_title)){
			sql += " and article_title like ? ";
		}
		sql	+= " order by article_date desc "
				+ "limit "+start+","+perPage;
		
		List <Map>list = new ArrayList();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			int pos = 1;
			if(nav_id !=null && !"".equals(nav_id)){
				ps.setString(1, nav_id);
				pos = 2;
			}
			if(article_title !=null && !"".equals(article_title)){
				ps.setString(pos, "%"+article_title+"%");
			}
			
			ResultSet rs = ps.executeQuery();
			System.out.println(rs);
			while(rs.next()){
				Map map = new HashMap();
				map.put("order_no", 		ii++							);
				map.put("article_date", 	rs.getString("article_date")	);
				map.put("article_title",	rs.getString("article_title")	);
				map.put("article_id", 		rs.getString("article_id")		);
				map.put("nav_name", 		rs.getString("nav_name")		);
				list.add(map);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	public int queryArticleCount(String nav_id,String article_title){
		String sql = "select count(*) from article";
		int pos = 0;
		boolean f1 = nav_id !=null && !"".equals(nav_id);
		if(f1){
			sql="select count(*) from article,nav "
					+ "where article.nav_id=nav.nav_id and nav.nav_id=? ";
			pos = 1;
		}
		boolean f2 = article_title !=null && !"".equals(article_title);
		if(f2){
			sql += (pos>0?" and ":" where ") +"  article_title like ? ";
			pos+=1;
		}
		System.out.println(sql);
		try{
			PreparedStatement  ps = conn.prepareStatement(sql);
			
			if(f1){
				ps.setString(1, nav_id);
			}
			if(f2){
				ps.setString(pos, "%"+article_title+"%");
			}
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	public Article queryArticleById(String article_id) {
		String sql = "select article_id,article_title,article_date,article_content,nav_id "
				+ "from article "
				+ "where article_id=?";
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, article_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				Article a = new Article();
				a.setArticle_content(rs.getString("article_content"));
				a.setArticle_date(rs.getString("article_date"));
				a.setArticle_id(article_id);
				a.setArticle_title(rs.getString("article_title"));
				a.setNav_id(rs.getString("nav_id"));
				return a;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 更新新闻
	 * @param article
	 * @return
	 */
	public boolean editArticle(Article article) {
		String sql = "update article "
					+ "set article_title=?,article_date=?,article_content=?,nav_id=? "
					+ "where article_id=?";
		//update article set article_title='ddd',article_date='dddd',article_content='ddd',nav_id='b211031a-fb81-4255-985e-0b8d95aa5d65' where article_id='eb0bdb11-6fa0-4387-9db9-b1c1b4c5f93a'
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, article.getArticle_title()	);
			ps.setString(2, article.getArticle_date()	);
			ps.setString(3, article.getArticle_content());
			ps.setString(4, article.getNav_id()			);
			ps.setString(5, article.getArticle_id()		);
			ps.executeUpdate();
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 删除新闻
	 * @param article_id
	 * @return
	 */
	public boolean deleteNewsById(String article_id) {
		String sql = "delete from article where article_id=?";
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, article_id);
			ps.executeUpdate();
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public List<Article> queryLastArticles() {
		String sql = "select article_id,article_title from article order by article_date desc limit 0,10";
		List list = new ArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Article a = new Article();
				a.setArticle_id(rs.getString("article_id"));
				a.setArticle_title(rs.getString("article_title"));
				list.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}
