<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<!-- 이쪽에 메뉴바 포함 할꺼임 -->
<jsp:include page="../common/header.jsp"/>

<div class="content">
   <br><br>
   <div class="innerOuter">
       <h2>마이페이지</h2>
       <br>

       <form action="update.me" method="post">
           <div class="form-group">
               <label for="userId">* ID :</label>
               <input type="text" class="form-control" id="userId" name="userId" value="${ loginUser.userId }" readonly><br>
               
               <label for="userName">* Name :</label>
               <input type="text" class="form-control" id="userName" name="userName" value="${ loginUser.userName }" readonly><br>
               
               <label for="email"> &nbsp; Email :</label>
               <input type="email" class="form-control" id="email" name="email" value="${ loginUser.email }"><br>
               
               <label for="age"> &nbsp; Age :</label>
               <input type="number" class="form-control" id="age" name="age" value="${ loginUser.age }"><br>
               
               <label for="phone"> &nbsp; Phone :</label>
               <input type="tel" class="form-control" id="phone" name="phone" value="${ loginUser.phone }"><br>
               
               <label for="address"> &nbsp; Address :</label>
               <input type="text" class="form-control" id="address" name="address" value="${ loginUser.address }"><br>
               
               <label for=""> &nbsp; Gender : </label> &nbsp;&nbsp;
               <c:choose>
            		<c:when test="${ loginUser.gender } == 'M'">
            			<input type="radio" name="gender" id="Male" value="M" checked>
		                <label for="Male">남자</label> &nbsp;&nbsp;
		                <input type="radio" name="gender" id="Female" value="F">
		                <label for="Female">여자</label><br>
            		</c:when>
            		<c:otherwise>
            			<input type="radio" name="gender" id="Male" value="M">
		                <label for="Male">남자</label> &nbsp;&nbsp;
		                <input type="radio" name="gender" id="Female" value="F" checked>
		                <label for="Female">여자</label><br>
            		</c:otherwise>
               </c:choose> 
               
           </div>
           <br>
           <div class="btns" align="center">
               <button type="submit" class="btn btn-primary">수정하기</button>
               <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteForm">회원탈퇴</button>
           </div>
       </form>

   </div>
   <br><br>
</div>

<!-- 회원탈퇴 버튼 클릭시 보여질 Modal -->
<div class="modal" id="deleteForm">
   <div class="modal-dialog">
       <div class="modal-content">
       
           <!-- Modal Header -->
           <div class="modal-header">
           <h4 class="modal-title">회원탈퇴</h4>
           <button type="button" class="close" data-dismiss="modal">&times;</button>
           </div>
           
           <!-- Modal body -->
           <div class="modal-body" align="center">
           
               <b>
                               탈퇴 후 복구가 불가능합니다. <br>   
                               정말로 탈퇴 하시겠습니까?
               </b>

               <form action="" method="post">
                       비밀번호 : 
                   <input type="password" name="userPwd" required>
                   <button type="submit" class="btn btn-danger">탈퇴하기</button>
               </form>

           </div>
           
       </div>
   </div>
</div>

<!-- 이쪽에 푸터바 포함할꺼임 -->
<jsp:include page="../common/footer.jsp"/>

</body>
</html>