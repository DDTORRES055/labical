<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    <!-- Comentario HTML -->
<%@ page  import="java.util.Random"%>			<!-- Import -->

<%! int n = 35; %> 		<!-- Declaracion -->


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>La Bicla</title>
</head>
<body>

<font color="<%= request.getParameter("color") %>">Hola <%= request.getParameter("nombre") %>!</font> <!-- Asignacion de valor -->


<p>Esto es un ejemplo de un Scriptlet</p>

   <% // Es un scriplet
   String valor = request.getParameter("valor");	// comentario Java
   
   
   if(valor != null && !valor.trim().equals(""))
   {
	   n = Integer.parseInt(valor);   
   }
   else
   {
	   Random r = new Random();
	   n = r.nextInt(10);
   }
   

    if(n % 2 == 0)
    {
%>

<h1> Eres un triunfador[a]<h1>

<%	   
   }
    else
    {

   %>
<h1> Eres un perdedor[a]<h1>

<%} %>

</body>
</html>