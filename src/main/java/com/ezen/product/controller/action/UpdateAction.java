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

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		String path = context.getRealPath("upload");
		String code = "";
		ProductVO pvo = new ProductVO();
		
		try {
			MultipartRequest multi = new MultipartRequest(
				request, path, 20*1024*1024, "UTF-8", new DefaultFileRenamePolicy()
			);
			//hidden으로 보낸 code값 받기
			code = multi.getParameter("code");
			
			pvo.setCode(Integer.parseInt(code));
			pvo.setName(multi.getParameter("name"));
			pvo.setPrice(Integer.parseInt(multi.getParameter("price")));
			pvo.setDescription(multi.getParameter("description"));
		
			//수정할 이미지를 선택하지 않은 경우
			//hidden으로 보낸 oldPicture값(원본 이미지파일 url값) 받기
			if(multi.getFilesystemName("pictureurl")==null)
				pvo.setPictureurl(multi.getParameter("oldPicture")); 
			//수정할 이미지가 있는 경우
			else 
				pvo.setPictureurl(multi.getFilesystemName("pictureurl"));
			
			ProductDao pdao = ProductDao.getInstance();
			pdao.updateProduct(pvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//code를 사용해 수정완료한 곳(productView)으로 이동
		String url = "product.do?command=productView&code="+pvo.getCode();
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
