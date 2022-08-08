<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Insert title here</title>
</head>
<body>
	<form>
		<input name="m_seq" type="hidden"
			value='<c:out value="${MEMO.m_seq}" default ="0"/>'> <input
			name="m_memo" placeholder="메모를 입력하세요" value="${MEMO.m_memo}">
		<button>메모기록</button>
	</form>
</body>
</html>