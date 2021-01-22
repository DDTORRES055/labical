<%@ tag description="Main Wrapper Tag" pageEncoding="UTF-8" import="mx.ipn.upiicsa.segsw.labicla.valueobject.UserValueObject"%>
<%!UserValueObject user;%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
	<head>
		<meta charset="UTF-8">
		<title>La Bicla</title>
		<link rel="stylesheet" href="css/bootstrap.min.css">
	</head>
	<body class="bg-info">
	
		

	<%
		user = (UserValueObject) session.getAttribute("user");

		if (user == null) // NO hay usuario firmado
		{
	%>

			<nav class="navbar navbar-inverse navbar-fixed-top">
				<div class="container">
					<div class="navbar-header">
						<a href="/LaBicla" class="navbar-brand">La Bicla</a>
					</div>
					<div id="navbar" class="navbar-collapse collapse">
						<form class="navbar-form navbar-right" action="authenticate.controller" method="GET">
							<div class="form-group">
								<input type="email" class="form-control" name="email" placeholder="Email">
							</div>
							<div class="form-group">
								<input type="password" pattern="^[^-']*$" class="form-control" name="password" placeholder="Contraseña">
							</div>
							<input type="submit" value="Entrar" class="btn btn-success">
						</form>
					</div>
				</div>
			</nav>

	<%
		} 
		else // Hay usuario firmado
		{
	%>

			<nav class="navbar navbar-inverse navbar-fixed-top">
				<div class="container">
					<div class="navbar-header">
						<a href="/LaBicla" class="navbar-brand">La Bicla</a>
					</div>
					<div id="navbar" class="navbar-collapse collapse">
						<a href="logout.controller" class="btn btn-danger navbar-btn navbar-right">Salir</a>
						<span class="navbar-right">&nbsp;&nbsp;&nbsp;</span>
						<p class="navbar-text navbar-right"><%=user.getName()%> </p>
					</div>
				</div>
			</nav>
		
	<%
		}
	%>
		
		<br/>
		<br/>
		<br/>
		<br/>