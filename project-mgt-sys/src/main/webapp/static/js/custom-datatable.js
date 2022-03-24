$(document).ready(function() {
    $('#dtBasicExample').DataTable({
    	 responsive: true,
		"pageLength" : 25
	});
	$(".dataTables_filter input").addClass("form-control-sm rounded-0");
	$(".dataTables_length select").addClass("form-control-sm rounded-0");
	$(".dataTables_paginate").addClass("form-control-sm rounded-0");
	$(".page-link").addClass("rounded-0");
} );