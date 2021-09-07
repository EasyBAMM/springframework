<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		Spring Tag Library
	</div>
	<div class="card-body">
		<div class="card">
			<div class="card-header">
				DTO 객체(Command Object)와 폼 연결
			</div>
			<div class="card-body">
				<a class="btn btn-info btn-sm" href="form1">form1</a>
			</div>
		</div>
		
		<div class="card">
			<div class="card-header">
				DTO 객체의 필드 값을 양식의 드롭다운 리스트(select 태그)로 세팅
			</div>
			<div class="card-body">
				<a class="btn btn-info btn-sm" href="form2">form2</a>
			</div>
		</div>
		
		<div class="card">
			<div class="card-header">
				DTO 객체의 필드 값을 양식의 드롭다운 리스트(checkbox 태그)로 세팅
			</div>
			<div class="card-body">
				<a class="btn btn-info btn-sm" href="form3">form3</a>
			</div>
		</div>
		
		<div class="card">
			<div class="card-header">
				DTO 객체의 필드 값을 양식의 드롭다운 리스트(radio 태그)로 세팅
			</div>
			<div class="card-body">
				<a class="btn btn-info btn-sm" href="form4">form4</a>
			</div>
		</div>
		
		<div class="card">
			<div class="card-header">
				국제화를 적용한 폼
			</div>
			<div class="card-body">
				<a class="btn btn-info btn-sm" href="form5">form5</a>
			</div>
		</div>
		
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>