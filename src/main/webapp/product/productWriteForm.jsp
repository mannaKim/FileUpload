<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>productWriteForm</title>
 <link rel="stylesheet" type="text/css" href="css/product.css">
</head>
<body>
  <div id="wrap" align="center">
    <h1>상품 등록 - 관리자 페이지</h1>
    <form method="post" enctype="multipart/form-data" action="product.do?command=productWrite">
      <!-- enctype="multipart/form-data"일 때는 MultipartRequest를 이용하기 때문에,
      일반적인 request로 command를 받을 수 없으므로 input type="hidden"으로 command 값을 보내면 안됨.
      form태그의 action에서 ? 뒤에 써야함 -->
      <table>
        <tr>
          <th>상 품 명</th>
          <td><input type="text" name="name" size="80"></td>
        </tr>
        <tr>
          <th>가 격</th>
          <td><input type="text" name="price">원</td>
        </tr>
        <tr>
          <th>사 진</th>
          <td><input type="file" name="pictureurl"><br></td>
        </tr>
        <tr>
          <th>설 명</th>
          <td><textarea cols="80" rows="10" name="description"></textarea></td>
        </tr>
      </table>
      <br>
      <input type="submit" value="등록">
      <input type="reset" value="다시 작성">
      <input type="button" value="목록" onClick="location.href='product.do?command=index'">
    </form>
  </div>
</body>
</html>