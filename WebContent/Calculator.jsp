<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
function sendAjax() {
	s1 = $("#summand1").val();
	s2 = $("#summand2").val();
	url = "Calculator?summand1=" + s1 + "&summand2=" + s2;
	$.get(url, function(data){
				$("#result").text(data);
			}
	);
	return true;
}
</script>
<title>Calculator First</title>
</head>
<body>
	<div>
		<input type="text" name="summand1" id="summand1"/>
		+
		<input type="text" name="summand2" id="summand2"/>
		=
		<label id="result"></label>
		<br>		
		<button onclick="sendAjax()">Calculate</button>
	</div>

</body>
</html>