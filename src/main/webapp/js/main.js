$(function(){
	
	// The root URL for the RESTful services
	var rootURL = "http://localhost:8080/interviewApp-1.0/api/";
	
	var currentClothes;
	var currentClothesDest;
	
	$('#btnDelete').hide();
	
	$('#btnSave').click(function() {
		var destination = $('#dest').val();
		if ($('#clothesId').val() === '') {
			addClothes(destination);
		} else {
			updateClothes(destination);
		}
		return false;
	});
	
	$('#btnDelete').click(function() {
		deleteClothes();
		return false;
	});
	
	$('#btnClear').click(function() {
		refreshForm();
		return false;
	});
	
	$('#clothesShopList').on('click', 'a', function() {
		findById($(this).data('identity'), "shop");
	});
	
	$('#clothesWarehouseList').on('click', 'a', function() {
		findById($(this).data('identity'), "warehouse");
	});
	
	function refreshForm() {
		$('#btnDelete').hide();
		currentClothes = {};
		console.log("refresh form");
		// Display an empty form
		renderDetails(currentClothes, ""); 
	}
	
	function findAll() {
		console.log('findAll');
		$.ajax({
			type: 'GET',
			url: rootURL + "shop",
			dataType: "json",
			success: renderListShop
		});
		
		$.ajax({
			type: 'GET',
			url: rootURL + "warehouse",
			dataType: "json",
			success: renderListWarehouse
		});
	}
	
	function findById(id, destination) {
		console.log('findById: ' + id);
		$.ajax({
			type: 'GET',
			url: rootURL + destination + "/" + id,
			dataType: "json",
			success: function(data){
				$('#btnDelete').show();
				console.log('findById success');
				currentClothes = data;
				currentClothesDest = destination;
				renderDetails(currentClothes, destination);
			}
		});
	}
	
	function addClothes(destination) {
		console.log('add clothes to ' + destination);
		$.ajax({
			type: 'POST',
			contentType: 'application/json',
			url: rootURL + destination,
			data: formToJSON(),
			success: function(data, textStatus, jqXHR){
				console.log('clothes created successfully');
				$('#btnDelete').show();
				findAll();
				refreshForm();
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log('add clothes error: ' + textStatus);
			}
		});
	}
	
	function updateClothes(destination) {
		console.log('updateClothes');
		
		var path = destination;
		if (destination !== currentClothesDest) {
			path = currentClothesDest + "To" + destination;
		}
		
		$.ajax({
			type: 'PUT',
			contentType: 'application/json',
			url: rootURL + path + '/' + $('#clothesId').val(),
			data: formToJSON(),
			success: function(data, textStatus, jqXHR){
				console.log('clothes updated successfully');
				findAll();
				refreshForm();
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log('update clothes error: ' + textStatus);
			}
		});
	}
	
	function deleteClothes() {
		console.log('deleteClothes ' + rootURL + currentClothesDest + '/' + $('#clothesId').val());
		$.ajax({
			type: 'DELETE',
            contentType: 'application/json',
			url: rootURL + currentClothesDest + '/' + $('#clothesId').val(),
			success: function(data, textStatus, jqXHR){
				console.log('clothes deleted successfully');
				findAll();
				refreshForm();
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log('delete clothes error');
			}
		});
	}
	
	function renderListShop(data) {
		// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
		var list = data === null ? [] : (data instanceof Array ? data : [data]);
		$('#clothesShopList').find('li').remove();
		$.each(list, function(index, clothes) {
			$('#clothesShopList').append(
					'<li><a href="#" data-identity="' + clothes.id + '">'
					+ clothes.type + " "
					+ clothes.size + " "
					+ clothes.color + " "
					+ clothes.value + " "
					+ clothes.description
					+ '</a></li>'
			);
		});
	}
	
	function renderListWarehouse(data) {
		var list = data === null ? [] : (data instanceof Array ? data : [data]);
		$('#clothesWarehouseList').find('li').remove();
		$.each(list, function(index, clothes) {
			$('#clothesWarehouseList').append(
					'<li><a href="#" data-identity="' + clothes.id + '">'
					+ clothes.type + " "
					+ clothes.size + " "
					+ clothes.color + " "
					+ clothes.value + " "
					+ clothes.description
					+ '</a></li>'
			);
		});
	}
	
	function renderDetails(clothes, dest) {
		$('#clothesId').val(clothes.id);
		$('#size').val(clothes.size);
		$('#color').val(clothes.color);
		$('#type').val(clothes.type);
		$('#value').val(clothes.value);
		$('#description').val(clothes.description);
		$('#dest').val(dest);
	}
	
	function formToJSON() {
		var id = $('#clothesId').val();
		return JSON.stringify({
			"id": id === "" ? null : id,
			"size": $('#size').val(), 
			"color": $('#color').val(),
			"type": $('#type').val(),
			"value": $('#value').val(),
			"description": $('#description').val()
		});
	}
})

