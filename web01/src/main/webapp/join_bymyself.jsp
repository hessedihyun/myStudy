<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Join us! Just for One Minutes! **</title>
</head>
<body>
<h2>** Join us! Just for One Minutes! **</h2>
<pre><b>
<h3>
If you want to join us, you have to write down 
your information on membership page.
</h3>
</b></pre>
<form action="/web01/JoinBymyself" method="post"><table>
<tr>
	<td><label for="name">Name</label></td>
	<td><input type="text" id="name" name="name"></td>
</tr>
<tr>
	<td><label for="age">Age</label></td>
	<td><input type="text" id="age" name="age"></td>
</tr>
<tr>
	<td><label for="jno">Jno</label></td>
	<td><input type="text" id="jno" name="jno"></td>
</tr>
<tr>
	<td><label for="info">Info</label></td>
	<td><input type="text" id="info" name="info"></td>
</tr>
<tr>
	<td><input type="submit"></td>
</tr>
</table></form>

</body>
</html>