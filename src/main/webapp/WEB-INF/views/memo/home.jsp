<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<table class="memo">
		<tr>
			<th>순번</th>
			<th>작성일자</th>
			<th>작성시각</th>
			<th>메모</th>
			<th>작성자</th>
			<th>접속중인 아이디</th>
		</tr>
		<c:forEach items="${MEMOS}" var="MEMO" varStatus="INDEX">
			<tr data-seq="${MEMO.m_seq}">
				<td>${INDEX.count}</td>
				<td>${MEMO.m_date}</td>
				<td>${MEMO.m_time}</td>
				<td>${MEMO.m_memo}</td>
				<td>${MEMO.m_author}</td>
				<td>${MEMO.username}</td>
			</tr>
		</c:forEach>
	</table>
	<a href="${rootPath}/memo/insert">메모작성하기</a>
</body>
</html>