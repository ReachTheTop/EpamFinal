
		$(document).ready(function() {
			$('#modal').on('hidden.bs.modal', function (e) {
				document.getElementById("form1").reset();
				$("#incorectData").hide();
				
			});
		});



		var form = $('#form1');
		form.submit(function() {

			request = $.ajax({
				type : form.attr('method'),
				url : form.attr('action'),
				data : form.serialize(),
				success : function(text) {

					$("#incorectData").hide();
					document.getElementById("form1").reset();
					$('#myModal').modal('hide');
					alert("Task was successfully created");

				},
				error : function() {
					$("#incorectData").show();
				}
			});

			return false;
		});
