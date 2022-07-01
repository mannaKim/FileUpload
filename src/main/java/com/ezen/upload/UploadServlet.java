package com.ezen.upload;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/upload.do")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 1. 파일이 업로드될 타겟 폴더를 지정, String으로 저장해둡니다.
		String savePath = "upload";
		
		// 2. 업로드 될 파일의 용량을 제한하기 위한 용량값을 int 변수에 저장해둡니다.
		int uploadFileSizeLimit = 5*1024*1024; //1바이트 기준 1024byte = 12Kbyte
		//1024KByte = 1MB
		
		// 3. 인코딩 방식을 String 변수에 저장해둡니다.
		String encType = "UTF-8";
		
		// 4. 업로드될 서버의 실제 저장장소를 설정하여 String 변수에 저장해둡니다.
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		
		System.out.println(uploadFilePath);
		
		MultipartRequest multi = new MultipartRequest(
				request, //jsp에서 전달된 request 객체를 MultipartRequest로 전달합니다.
				//enctype="multipart/form-data"로 보낸 파라미터들은 일반 request로 값을 얻을 수 없습니다.
				//MultipartRequest에 request를 넣어서 복합사용해야 담겨있는 유효한 값을 얻을 수 있습니다.
				uploadFilePath, //서버상의 실제 저장장소
				uploadFileSizeLimit, //최대업로드 용량 제한
				encType, //인코딩 방식
				new DefaultFileRenamePolicy() //저장장소에 업로드되는 파일이름 중복시 문제 해결하는 객체
		);
		System.out.println("request로 처리 - title : "+request.getParameter("title"));
		//일반 request는 multipart/form-data로 전달된 파라미터가 모두 null로 수신됩니다.
		System.out.println("multi로 처리 - title : "+multi.getParameter("title"));
		
		String name = multi.getParameter("name");
		String title = multi.getParameter("title");
		String fileName = multi.getFilesystemName("uploadFile"); //전달된 파일 이름 추출
		
		request.setAttribute("name", name);
		request.setAttribute("title", title);
		request.setAttribute("fileName", fileName);
		
		RequestDispatcher dp = request.getRequestDispatcher("01_result.jsp");
		dp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
