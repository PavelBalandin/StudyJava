
//==============//Global variables//==============//

var buttonSeller = document.getElementById("SendSeller");
var buttonProduct = document.getElementById("SendProduct");
var tablename = "";
var upbuttons;
var dlbuttons;
var insSeller;
var insProduct;

function ajaxPost(params, servletname){
	var request = new XMLHttpRequest();
	
	request.onreadystatechange = function(){
		if(request.readyState == 4){
			document.getElementById('result').innerHTML = request.responseText;
			buttons_events();
			ajaxPostS(`table=${tablename}`,"main.jsp");

		}
	}

	request.open('POST', servletname);
	request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	request.send(params);
		
}

function ajaxPostS(params, servletname){
	var request = new XMLHttpRequest();
	
	request.onreadystatechange = function(){
		if(request.readyState == 4){
			document.getElementById('result').innerHTML = request.responseText;
			buttons_events();
		}
	}

	request.open('POST', servletname);
	request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	request.send(params);
		
}

//==============//Show table function//==============//

buttonSeller.addEventListener('click', function(){
	tablename = "seller";
	ajaxPost(`table=${tablename}`,"main.jsp");
});

buttonProduct.addEventListener('click', function(){
	tablename = "product";
	ajaxPost(`table=${tablename}`,"main.jsp");
});

//==============//Buttons events //==============//
function buttons_events(){
	upbuttons = document.getElementsByClassName("upbutton");
	dlbuttons = document.getElementsByClassName("dlbutton");
	insProduct = document.getElementById("insertProduct");
	insSeller = document.getElementById("insertSeller");

	if (insSeller != null){
	insSeller.addEventListener('click', function() {
		var sellername = document.getElementById("sellername").value;
		var sellerspec = document.getElementById("sellerspec").value;		
		var params = "table=seller&operation=insertSeller&name="+ sellername +"&description=" + sellerspec;
		ajaxPost(params, "InsertServ");
	});
	}
	if (insProduct != null){
	insProduct.addEventListener('click', function() {
		var productname = document.getElementById("productname").value;
		var productprice = document.getElementById("productprice").value;
		var id_seller = document.getElementById("id_seller").value;			
		var params = "table=product&operation=insertProduct&name="+ productname +"&price=" + productprice +"&id_seller=" + id_seller;
		ajaxPost(params, "InsertServ");
	});
	}	

	for (var i = 0; i < dlbuttons.length; i++) {
		dlbuttons[i].addEventListener('click', function(){
			var id_to_delete = this.id;	
			var params = `table=${tablename}&operation=delete&id=${id_to_delete}`;
			ajaxPost(params, "DeleteServ");
		});
	}

	for (var i = 0; i < upbuttons.length; i++) {
		upbuttons[i].addEventListener('click', function(){
			var id_to_update = this.id;	
			var tds = document.getElementsByClassName(id_to_update);

			var name = tds[0].value;
			var price = tds[1].value;
			try{
				var id_seller = tds[2].value;
			}catch(e){
				
			}

			var params = `table=${tablename}&operation=update&id=${id_to_update}&name=${name}&price=${price}&id_seller=${id_seller}`;
			ajaxPost(params, "UpdateServ");
		});

	}

}

