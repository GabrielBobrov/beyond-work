function searchRest(categoriaId){
		var t = document.getElementById("searchType");
		if (categoriaId == null){
			t.value = "Texto";
			
		}else{
			t.value = "Categoria";
			document.getElementById("categoriaId").value = categoriaId;
		}
		document.getElementById("form").submit();
	}