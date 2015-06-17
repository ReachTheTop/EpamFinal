$(function() {
	var availableTags = [];

	$("#myAutocomplete").keyup(
			function() {

				if ($(this).val().length >= 3) {
					availableTags.length = 0;
					$.get("GroupServlet?action=users&group_id="
							+ $("#group").val() + "&token=" + $(this).val(),
							function(res) {

								$.each(res, function(index, item) {
									availableTags.push(item["email"]);

								});

							});
				}
			});

	$('#myAutocomplete').autocomplete({
		source : availableTags,
		multiselect : true
	});
});