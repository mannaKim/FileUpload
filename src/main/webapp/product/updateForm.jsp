<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>updateForm</title>
  <link rel="stylesheet" type="text/css" href="css/product.css">
</head>
<body>
  <div id="wrap" align="center">
    <h1>상품 수정 - 관리자 페이지</h1>
    <form method="post" enctype="multipart/form-data" action="product.do?command=update">
      <input type="hidden" name="code" value="${product.code}">
      <input type="hidden" name="oldPicture" value="${product.pictureurl}">
      <table>
        <tr>
          <td>
            <c:choose>
              <c:when test="${empty product.pictureurl}">
                <img src="upload/noname.jpg" width="220" height="300">
              </c:when>
              <c:otherwise>
                <img src="upload/${product.pictureurl}" width="220" height="300">
              </c:otherwise>
            </c:choose>
          </td>
          <td>
            <table border="1">
              <tr>
                <th style="width:80px">상품명</th>
          		<td><input type="text" name="name" value="${product.name}" size="80"></td>
        	  </tr>
        	  <tr>
        	    <th>가 격</th>
          		<td><input type="text" name="price" value="${product.price}">원</td>
        	  </tr>
        	  <tr>
        	    <th>사 진</th>
          		<td><input type="file" name="pictureurl"><br>
          		  (주의사항 : 이미지를 변경하고자 할 때만 선택하시오.)
          		</td>
        	  </tr>
        	  <tr>
        	    <th>설 명</th>
         	 	<td>
         	 	  <textarea cols="90" rows="10" name="description">
         	 	    ${product.description}
         	 	  </textarea>
         	 	</td>
       		  </tr>
            </table>
          </td>
        </tr>
      </table>
      <br>
      <input type="submit" value="수정">
      <input type="reset" value="다시 작성">
      <input type="button" value="목록" onClick="location.href='product.do?command=index'">
    </form>
  </div>
</body>
</html>