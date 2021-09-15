<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table style="width:100%" class="table table-sm table-bordered">
   <tr>
      <th style="width:10%">번호</th>
      <th style="width:auto">제목</th>
      <th style="width:15%">글쓴이</th>
      <th style="width:15%">날짜</th>
   </tr>
   
   <c:forEach var="board" items="${boards}">
      <tr>
         <td>${board.bno}</td>
         <td><a href="boardDetail?bno=${board.bno}">${board.btitle}</a></td>
         <td>${board.mid}</td>
         <td><fmt:formatDate value="${board.bdate}" pattern="yyyy-MM-dd"/></td>
      </tr>
   </c:forEach>
   
</table>
