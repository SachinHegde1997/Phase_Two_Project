<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Class Details Form</title>
</head>
<body>
	<h1>Add Class Details Form</h1>
	<form action="manage-class" method="post">
		<fieldset>



			<legend>Add Class Details</legend>
			<p>
				<label for="class_id">Class</label> <select name="class_id">
					<c:forEach items="${clsCategory}" var="category">
						<option value="${category.class_id}">
							${category.class_name}</option>
					</c:forEach>
				</select>
			</p>


			<p>
				<label for="tid">Tacher</label> <select name="tid">
					<c:forEach items="${tchCatagory}" var="tchCatagory">
						<option value="${tchCatagory.teacher_id}">
							${tchCatagory.teacher_name}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label for="sid">Subject</label> <select name="sid">
					<c:forEach items="${subsCategory}" var="subsCategory">
						<option value="${subsCategory.subject_id}">
							${subsCategory.subject_name}</option>
					</c:forEach>
				</select>
			</p>


			<input type="submit" value="Add Details">
		</fieldset>

	</form>

</body>
</html>