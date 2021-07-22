<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/entrada" var="linkEntradaServlet"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="${linkEntradaServlet }" method="post">
	
		Login: <input type="text" placeholder="login" name="login"  />
		Senha: <input type="password" placeholder="senha" name="senha"  />
		
	    <input type="hidden" name="acao" value="Login">
	    
		<input type="submit" />
	</form>

</body>
</html>