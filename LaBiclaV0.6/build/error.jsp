<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags" %>

<%@ page import="mx.ipn.upiicsa.segsw.labicla.valueobject.ErrorValueObject"%>
<%@ page import="java.util.List"%>

<%!
	ErrorValueObject error;
%>

<templates:nav/>

	<templates:header/>

	<div class="container">
		<div class="row">
<%
	error = (ErrorValueObject) request.getAttribute("error");
	if( error != null)
	{
%>


<div class="alert alert-danger" role="alert" style="overflow: auto;">
	
	<span class="sr-only"><h1><%=error.getMessage() %></h1></span>
	<p><%=error.getDescription() %><p>

<%
		if( error.getException() != null)
		{
%>
		
			<span class="sr-only"><h3>Exception</h3></span>
			<p>Causa: <%=error.getException().getCause() %></p>
			<span class="sr-only"><h4>StackTrace</h4></span>
			<p><%=error.getException() %></p>
<%
			StackTraceElement[] stArray = error.getException().getStackTrace();
			for(int i=0; i < stArray.length; i++)
			{
%>
				<p><%=stArray[i] %><p>
<%
			}
%>
</div>
<%
		}	
	}
	else
	{
%>
		
		<div class="alert alert-warning" role="alert">
			<span class="glyphicon glyphicon-alert" aria-hidden="true"></span>
			<span class="sr-only">Error:</span>
			No se encontro informacion del error a mostrar
		</div>
<% 
	}
%>
		</div>
	</div>
<templates:footer/>