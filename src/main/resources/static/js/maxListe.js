$(document).ready( function () {
	
	loadDatatable();
	
   var table = $('#tableListe').DataTable();
   
   $("#btn-post").click(function() {
		tache_ajout($("#btn-post"), "POST", table);
	});

	$("#btn-put").click(function() {
		tache_ajout($("#btn-put"), "PUT", table);
	});
	
	$("#btn-refresh").click(function() {
		setTimeout( function () {
	        table.ajax.reload();
	    }, 500 );
	});
	
	$("#btn-delete").click(function() {
		tache_supprime();		
	});
    
} );


function loadDatatable() {
	$('#tableListe').DataTable( {
	"ajax": {
        url: '/api/liste',
        dataSrc: ''
    },
    
    "columns": [
        { "data": "idListe" },
        { "data": "nomListe" },
        { "data": "decritListe" },
        { "data": "evolListe" }
      ]
} );		
}


function tache_ajout(button, httpVerb, table) {
	
	var tache = {};
	
	tache["idListe"] = $("#numtache").val();
	tache["nomListe"] = $("#nomtache").val();
	tache["decritListe"] = $("#decrittache").val();
	tache["evolListe"] = $("#evoltache").val();
	
	var url = "/api/ajouter";
	
	if(httpVerb == "PUT")
		url += "/" + tache["idListe"];
	
	button.prop("disabled", true);
	
	$.ajax({
		type : httpVerb,						
		contentType : "application/json",		
		url : url,								
		data : JSON.stringify(tache),		
		dataType : 'json',						
		cache : false,							
		timeout : 600000,						
		success : function(data) {
			
			var json = "<h3>Server Response au format JSON</h3><pre>tache (modifié/ajouté) :<br>" 
				+ JSON.stringify(data, null, 4) + "</pre>";
			
			$('#retourtache').html(json);
			
			console.log("SUCCESS : ", data);
			button.prop("disabled", false);

			resetForm();
		},
		});
	
	setTimeout( function () {
        table.ajax.reload();
    }, 500 );

}

function tache_supprime() {

	var idtache = $("#numtache").val();
	

	$.ajax({
		type : "DELETE",
		contentType : "application/json",
		url : "/api/supprime/" + idtache,
		data : JSON.stringify(idtache),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {

			var json = "<h3>Server Response</h3><pre>tache " + JSON.stringify(data, null, 1)  + " supprimée.</pre>";
			
			$('#retourtache').html(json);
			
			console.log("SUCCESS : ", data);

			resetForm();
		},
		
	});
	
}	

function resetForm() {
	$('#tache-form')[0].reset();
}
	
