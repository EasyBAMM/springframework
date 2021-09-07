<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		DTO 객체(Command Object)와 폼 연결
	</div>
	<div class="card-body">
		<form:form method="POST" modelAttribute="member">
		  <div class="form-group">
		    <label for="mid">ID</label>
		    <form:input type="text" class="form-control" path="mid"/>
		  </div>
		  <div class="form-group">
		    <label for="mname">Name</label>
		    <input type="text" class="form-control" id="mname" name="mname">
		  </div>
		  <div class="form-group">
		    <label for="mpassword">Password</label>
		    <form:password class="form-control" path="mpassword"/>
		  </div>
		  <div class="form-group">
		    <label for="mnation">Nation</label>
		    <form:input type="text" class="form-control" path="mnation"/>
		  </div>
		  <button type="submit" class="btn btn-primary btn-sm">Submit</button>
		</form:form>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>