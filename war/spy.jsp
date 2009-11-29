<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>RequestSpy - ${ requestScope.spiedPath }</title>
		<link href="/main.css" media="screen" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
	</head>
	<body>
		<h1><span class="accent">${ requestScope.spiedPath }</span><br />Spied Requests</h1>
		<c:choose>
			<c:when test="${ not empty requestScope.spiedRequests }">
			<table class="spiedRequests">
				<tr>
					<th>METHOD</th>
					<th>CALLER</th>
					<th>WHEN</th>
				</tr>
			<c:forEach var="spiedRequest" items="${ requestScope.spiedRequests }" varStatus="status">
				<tr id="spiedRequest_${ status.index }" class="${ status.index % 2 == 0 ? 'one' : 'two' } spiedRequest">
					<td>${ spiedRequest.method }</td>
					<td>${ spiedRequest.caller }</td>
					<td>${ spiedRequest.when }</td>
				</tr>
				<tr id="spiedRequest_${ status.index }_details" class="${ status.index % 2 == 0 ? 'one' : 'two' } spiedRequestsDetail">
					<td colspan="3">
						<table class="detailTable">
							<tr>
								<th>Header Key</th>
								<th>Value</th>
							</tr>
							<tr>
								<td>User-Agent</td>
								<td>DUMMY</td>
							</tr>
							<tr>
								<td>Accept</td>
								<td>DUMMY</td>
							</tr>
							<tr>
								<th>Request Parameter</th>
								<th>Value</th>
							</tr>
							<tr>
								<td>UserId</td>
								<td>DUMMY</td>
							</tr>
							<tr>
								<td>JSESSIONID</td>
								<td>DUMMY</td>
							</tr>
						</table>
					</td>
				</tr>
			</c:forEach>
			</table>
			<p class="info">Click on a row to see request details.</p>
			</c:when>
			<c:otherwise>
				<strong>NONE FOUND</strong>
			</c:otherwise>
		</c:choose>
		<script type="text/javascript">
			jQuery(".spiedRequest td").live("click", function() {
				jQuery("#" + $(this).parent().attr("id") + "_details").toggle();
			});
		</script>
	</body>
</html>