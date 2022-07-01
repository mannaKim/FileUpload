package com.ezen.product.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.product.dao.ProductDao;
import com.ezen.product.dto.ProductVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ProductWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//현재 작성 영역은 서블릿이 아니고 자바 클래스이며, 서블릿에서 전달된 request를 받아 사용하는 곳이므로
		//request에서 세션을 추출한 후 session.getServletContext()를 사용합니다.
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		String path = context.getRealPath("upload");
		
		ProductVO pvo = new ProductVO();
		
		MultipartRequest multi = new MultipartRequest(
				request, path, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
		
		pvo.setName(multi.getParameter("name"));
		pvo.setPrice(Integer.parseInt(multi.getParameter("price")));
		pvo.setDescription(multi.getParameter("description"));
		pvo.setPictureurl(multi.getFilesystemName("pictureurl"));
		//※ input type="file"로 받은 데이터는, 
		//multi(MultipartRequest)로 꺼내 쓸 때 getParameter가 아니라 getFilesystemName을 사용해야 함!
		
		ProductDao pdao = ProductDao.getInstance();
		pdao.insertProduct(pvo);
		
		response.sendRedirect("product.do?command=index");
	}

}
