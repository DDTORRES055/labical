<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags" %>

<%@ page import="mx.ipn.upiicsa.segsw.labicla.valueobject.ProductValueObject"%>
<%@ page import="java.util.List"%>

<%!

	ProductValueObject product;
%>

<templates:nav/>

	<templates:header/>

	<div class="container">
		<div class="row">
	<%
		product = (ProductValueObject) request.getAttribute("product");
		if (product != null) 
		{
	%>
		<div class="col-md-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h1 class="panel-title"><%= product.getName() %></h1>
				</div>
				<div class="panel-body">
					<p class="text-center"><img src="images/<%= product.getImage() %>" width="300px" alt="imagen" class="img-thumbnail"><p>
					<p>$<%= product.getPrice() %></p>
					<p><%= product.getDescription() %></p>
				</div>
				<div class="panel-footer">
				<%= product.getBrand() %>
				</div>
			</div>
		</div>

	<%
		}
		else 
		{
	%>

		<div class="alert alert-warning" role="alert">
			<span class="glyphicon glyphicon-inbox" aria-hidden="true"></span>
			<span class="sr-only">Error:</span>
			No se encontro el producto indicado
		</div>

	<%
		}
	%>
		</div>
	</div>
	
<templates:footer/>