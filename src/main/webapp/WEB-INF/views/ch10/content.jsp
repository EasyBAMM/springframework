<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		예외 처리
	</div>
	<div class="card-body">
		<div class="card">
			<div class="card-header">
				try-catch 예외 처리
			</div>
			<div class="card-body">
				<a class="btn btn-danger btn-sm" href="handlingException1">예외 처리1</a>
			</div>
			
			<div class="card-header">
				예외 처리 클래스를 이용한 예외 처리
			</div>
			<div class="card-body">
				<a class="btn btn-danger btn-sm" href="handlingException2">예외 처리2</a>
				<a class="btn btn-danger btn-sm" href="handlingException3">예외 처리3</a>
				<a class="btn btn-danger btn-sm" href="handlingException4">예외 처리4</a>
				<a class="btn btn-danger btn-sm" href="handlingException5">예외 처리5</a>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>