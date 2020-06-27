$(document).ready(
		function() {

			// SUBMIT FORM
			$("#itemForm").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				// PREPARE FORM DATA
				var formData = {
					name : $("#name").val(),
					amount : $("#amount").val(),
					unit : $("#unit").val()
				}

				var token =  $('input[name="_csrf"]').attr('value');

				// DO POST
				$.ajax({
					type : "POST",
                    headers: {
                                  'X-CSRF-Token': token
                             },
					contentType : "application/json",
					url : "items/save",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {
						if (result.status == "success") {
							$("#postResultDiv").html(result.data.name + " saved!");
						} else {
							$("#postResultDiv").html("<strong>Error</strong>");
						}
						console.log(result);
					},
					error : function(e) {
						alert("Error!")
						console.log("ERROR: ", e);
					}
				});

			}

		})