<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags" %>

<%@ page import="mx.ipn.upiicsa.segsw.labicla.valueobject.ProductValueObject"%>
<%@ page import="java.util.List"%>

<%!
	List<ProductValueObject> productList;
	ProductValueObject product;
%>

<templates:nav/>

	<templates:header/>

	<div class="container">
		<div class="row">
	<%
		productList = (List<ProductValueObject>) request.getAttribute("productList");
		if(productList != null && productList.size() > 0)
		{
			for(int i = 0; i < productList.size(); i++ )
			{
				product = productList.get(i);
	%>
				<div class="col-sm-12">
					<div class="thumbnail">
						<div class="caption">
							<h3><%= product.getName() %></h3>
							<p>$<%= product.getPrice() %></p>
							<p><%= product.getDescription() %></p>
							<p>
								<a href="get_product_details.controller?id=<%= product.getId() %>" class="btn btn-info" role="button">Ver Detalle</a>
							</p>
						</div>
					</div>
				</div>
	<%
			}
		}
		else
		{
	%>
			<div class="alert alert-warning" role="alert">
				<span class="glyphicon glyphicon-inbox" aria-hidden="true"></span>
				<span class="sr-only">Error:</span>
				No se encontraron productos
			</div>
	<%
		}
	%>
		</div>
	</div>

<templates:footer/>