GET: $(document).ready(
		function() {

			// GET REQUEST
			$("#getAllItems").click(function(event) {
				event.preventDefault();
				ajaxGet();
			});

			// DO GET
			function ajaxGet() {
				$.ajax({
					type : "GET",
					url : "getItems",
					success : function(result) {
						if (result.status == "success") {
							$('#getResultDiv ul').empty();
							$.each(result.data,
									function(i, item) {
										var newItem = item.amount+" "+item.unit+" "+item.name+"<br>";
										$('#getResultDiv .list-group').append(newItem)
									});
							console.log("Success: ", result);
						} else {
							$("#getResultDiv").html("<strong>Error</strong>");
							console.log("Fail: ", result);
						}
					},
					error : function(e) {
						$("#getResultDiv").html("<strong>Error</strong>");
						console.log("ERROR: ", e);
					}
				});
			}
		})