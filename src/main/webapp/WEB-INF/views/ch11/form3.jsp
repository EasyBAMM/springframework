<%@ page contentType="text/html; charset=UTF-8" %>


<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		DTO 객체의 필드 값을 양식의 드롭다운 리스트(checkbox 태그)로 세팅
	</div>
	<div class="card-body">
		<form method="POST" action="form3">
			<div>
				<c:forEach var="language" items="${languageList}" varStatus="status">
				<span>
					  <input class="ml-2 mr-2" type="checkbox" id="lang${status.count}" name="mlanguage" value="${language}"
					  <c:forEach var="temp" items="${member.mlanguage}">
					  		<c:if test="${temp == language}">checked</c:if>
					  </c:forEach>/>
					  <label for="lang${status.count}">${language}</label>
				</span>
				</c:forEach>
			</div>
			<button type="submit" class="btn btn-primary btn-sm">제출</button>
		</form>
		
		<form:form modelAttribute="member" class="mt-3">
			<div>
				<form:checkboxes items="${languageList}" path="mlanguage" class="ml-2 mr-2"/>
			</div>
			<button type="submit" class="btn btn-primary btn-sm">제출</button>
		</form:form>
		
		<form:form modelAttribute="member" class="mt-3">
			<div>
				<form:checkboxes items="${skillList}" path="mskill" itemValue="code" itemLabel="label"  class="ml-2 mr-2"/>
			</div>
			<button type="submit" class="btn btn-primary btn-sm">제출</button>
		</form:form>
		
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>