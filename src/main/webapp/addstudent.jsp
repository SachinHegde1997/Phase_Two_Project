<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Student Form</title>
</head>
<body>
	<h1>Add Student Form</h1>
	<form action="add-student" method="post">
		<fieldset>



			<legend>Add Student Details</legend>
			Name: <input type="text" name="name" id="name" /> Address: <input
				type="text" name="address" id="address" />


			<p>
				<label for="name">Class</label> <select name="class_id">
					<c:forEach items="${listCategory}" var="category">
						<option value="${category.class_id}">
							${category.class_name}</option>
					</c:forEach>
				</select>
			</p>


			<input type="submit" value="Add Student">
		</fieldset>

	</form>

</body>
</html>