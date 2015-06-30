$('input').on('ifChanged', function(event) {
	// alert(event.target.name);
	// alert(event.target.checked);
	/*
	 * помилка у відсиланні запиту :\
	 */
	var arr = { id: event.target.name, visit: event.target.checked };
	$.ajax({
	    type: 'post',
	    url: './att-record',
	    data: JSON.stringify(arr),
	    contentType: 'application/json; charset=utf-8',
	    dataType: 'json',
	    async: false,
	    success: function(msg) {
	        alert(msg);
	    }
	});
});