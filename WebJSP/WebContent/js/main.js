
// Global variables
var buttonSeller = document.getElementById("SendSeller");
var buttonProduct = document.getElementById("SendProduct");
var tablename = "";

var upbuttons;
var dlbuttons;
var insSeller;
var insProduct;

function ajaxPost(params){
	var request = new XMLHttpRequest();
	
	request.onreadystatechange = function(){
		if(request.readyState == 4){
			document.getElementById('result').innerHTML = request.responseText;
			buttons_events();
		}
	}

	request.open('POST', 'main.jsp');
	request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	request.send(params);
		
}

function ajaxPostToDelete(params){
	var request = new XMLHttpRequest();
	
	request.onreadystatechange = function(){
		if(request.readyState == 4){
			document.getElementById('result').innerHTML = request.responseText;
			buttons_events();
		}
	}

	request.open('POST', 'DeleteServ');
	request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	request.send(params);
		
}

function ajaxPostToUpdate(params){
	var request = new XMLHttpRequest();
	
	request.onreadystatechange = function(){
		if(request.readyState == 4){
			document.getElementById('result').innerHTML = request.responseText;
			buttons_events();
		}
	}

	request.open('POST', 'UpdateServ');
	request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	request.send(params);
		
}

function ajaxPostToInsert(params){
	var request = new XMLHttpRequest();
	
	request.onreadystatechange = function(){
		if(request.readyState == 4){
			document.getElementById('result').innerHTML = request.responseText;
			buttons_events();
		}
	}

	request.open('POST', 'InsertServ');
	request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	request.send(params);
		
}

//Show table eventListener 
buttonSeller.addEventListener('click', function(){
	tablename = "seller";
	ajaxPost(`table=${tablename}`);
});

buttonProduct.addEventListener('click', function(){
	tablename = "product";
	ajaxPost(`table=${tablename}`);
});

function buttons_events(){
	upbuttons = document.getElementsByClassName("upbutton");
	dlbuttons = document.getElementsByClassName("dlbutton");
	console.log(dlbuttons.length);
	insProduct = document.getElementById("insertProduct");
	insSeller = document.getElementById("insertSeller");

	if (insSeller != null){
	insSeller.addEventListener('click', function() {
		alert(insSeller.value);
		var sellername = document.getElementById("sellername").value;
		var sellerspec = document.getElementById("sellerspec").value;		
		var params = "table=seller&operation=insertSeller&name="+ sellername +"&description=" + sellerspec;
		ajaxPost(params);
	});
	}
	if (insProduct != null){
	insProduct.addEventListener('click', function() {
		alert(insProduct.value);
		var productname = document.getElementById("productname").value;
		var productprice = document.getElementById("productprice").value;
		var id_seller = document.getElementById("id_seller").value;			
		var params = "table=product&operation=insertProduct&name="+ productname +"&price=" + productprice +"&id_seller=" + id_seller;
		ajaxPostToInsert(params);
	});
	}	

	for (var i = 0; i < dlbuttons.length; i++) {
		dlbuttons[i].addEventListener('click', function(){
			alert("delete");
			var id_to_delete = this.id;	
			var params = `table=${tablename}&operation=delete&id=${id_to_delete}`;
			ajaxPostToDelete(params);
		});
	}

	for (var i = 0; i < upbuttons.length; i++) {
		upbuttons[i].addEventListener('click', function(){
			alert("update");
			var id_to_update = this.id;	
			var tds = document.getElementsByClassName(id_to_update);

			var name = tds[0].value;
			var price = tds[1].value;
			var id_seller = tds[2].value;

			var params = `table=${tablename}&operation=update&id=${id_to_update}&name=${name}&price=${price}&id_seller=${id_seller}`;
			ajaxPostToUpdate(params);
		});

	}

}

