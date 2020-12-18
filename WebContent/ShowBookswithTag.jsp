<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table class="table-bordered">
				<thead>
					<tr>
						<td>ISBN-13:</td>
						<td>Title:</td>
						<td>Author:</td>
						<td>Publish Date:</td>
						<td>Price:</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${tags}">
						<tr>
							<td><c:out value="${item.isbn13}" /></td>
							<td><c:out value="${item.title}" /></td>
							<td><c:out value="${item.author}" /></td>
							<td><c:out value="${item.publishDate}" /></td>
							<td><c:out value="${item.price}" /></td>
						</tr>
					</c:forEach>
				</tbody>
	</table>
					
</body>
</html>