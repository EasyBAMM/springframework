<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		Spring Security
	</div>
	<div class="card-body">
		<div class="card">
			<div class="card-header">
				로그인/로그아웃
			</div>
			<div class="card-body">
				<a class="btn btn-info btn-sm" href="loginForm">로그인</a>
				<a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/logout">로그아웃</a>
			</div>
		</div>
	</div>
</div>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>