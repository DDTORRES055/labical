<%@ tag description="Main Wrapper Tag" pageEncoding="UTF-8"%>

	<div id="main-title">
		<h1 class="text-center"><i>La Bicla</i></h1>	
	</div>

	<div class="container">
		<div class="row">
			<div class="col-xs-6 col-xs-offset-3">
				<form action="search_products.controller" method="GET">
					<div class="input-group">
						<input type="text" pattern="^[^-']*$" class="form-control" name="criterio" placeholder="Buscar productos">
						<span class="input-group-btn">
							<input type="submit" class="btn btn-primary" value="Buscar">
						</span>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<br/>
	<br/>
	<br/>
	<br/>