$(function(){
	$('a#history').click(function(e){
		var object = $(this).parent();
		var last_message = $('ul.chat  li:eq(1)').attr("data-id");
		var group_id = $("#room").val();
		$.get("GroupServlet?action=chatHistory&group_id="+group_id,{last_id: last_message}, function(response){
			$.each(response, function(index,item){
				
		        var date = new Date(item.sendDate);
		        var year = date.getFullYear().toString();
		        var month = addZero(date.getMonth() + 1).toString();
		        var day = addZero(date.getDate()).toString();
		        var hours = addZero(date.getHours()).toString();
		        var minutes = addZero(date.getMinutes()).toString();

		        var correctDate = year.concat("-", month, "-", day, " ",
		          hours, ":", minutes);

				
				
				object.after(
		        		
				        '<li  data-id="'+item.id+'" id="top" class="left clearfix"><span class="chat-img pull-left">'+
				        '<img src="upload/'+item.sender.image+'" alt="User Avatar"  style="border-radius: 50%;"  width="50" height="50" /></span>'+
				        '<div class="chat-body clearfix" style="word-wrap: break-word"><div class="header">'+
				        '<strong class="primary-font"><a href="/ITMount/UserServlet?user_id='+item.sender.id+'">'+item.sender.name +" "+ item.sender.surname+'</a></strong><small class="pull-right text-muted">'+
				        '<span class="glyphicon glyphicon-time"></span>'+ correctDate +'</small></div><p>'+
				        item.content + '</p></div></li>');
			});
			
		});
	});
	
    function addZero(i) {
  	  if (i < 10) {
  	   i = "0" + i;
  	  }
  	  return i;
  	 }
});