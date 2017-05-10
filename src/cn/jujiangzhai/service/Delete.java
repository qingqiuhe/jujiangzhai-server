package cn.jujiangzhai.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import cn.jujiangzhai.dao.impl.xml.CraftDao;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("id");
		
		if(id!=null){
			
			File path = new File(this.getServletContext().getRealPath("/")+"db/handicrafts.xml");
			

			CraftDao dao = new CraftDao(path);
			
			dao.delete(id);
				
				
			File imgPath = new File(this.getServletContext().getRealPath("/")+"img/handicrafts/"+id+".jpg");
			if(imgPath.exists()){
				if(imgPath.isFile()){
					imgPath.delete ();
				}
			}
				
				
				request.getRequestDispatcher("inputHandicraft.jsp").forward(request, response);
			
			
		}else{
			response.setHeader("refresh", "2;URL=inputHandicraft.jsp");
			response.getWriter().write("ID为空");
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
