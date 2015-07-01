$(function () {
    "use strict";

    var content = $('ul.chat');
    var input = $('#input');
    var status = $('#status');
    var myName = false;
    var author = null;
    var logged = false;
    var socket = $.atmosphere;
    var request = { url: (location.protocol + '//' + location.host + location.pathname) + '/meteor/'+$("input#room").val(),
                    contentType : "application/json",
                    logLevel : 'debug',
                    transport : 'websocket' ,
                    reconnectInterval : 5000,
                    fallbackTransport: 'long-polling'};

    
    request.onOpen = function(response) {
        
        input.removeAttr('disabled').focus();
        status.text($("#author").val());
    };

    request.onMessage = function (response) {
        var message = response.responseBody;
        try {
            var json = jQuery.parseJSON(message);
        } catch (e) {
            console.log('This doesn\'t look like a valid JSON: ', message);
            return;
        }

        input.removeAttr('disabled').focus();
       /*
		 * if (!logged) { logged = true; status.text($("#author").val() + ':
		 * ').css('color', 'blue'); } else {
		 */
            var me = json.author == author;
            addMessage(json.message_id, json.id, json.image, json.author, json.text, me ? 'blue' : 'black', new Date(json.time));
       /* } */
    };

    request.onError = function(response) {
        content.html($('<p>', { text: 'Sorry, but there\'s some problem with your '
            + 'socket or the server is down' }));
    };

    request.onClose = function(response) {
        logged = false;
    }

    var subSocket = socket.subscribe(request);

    input.keydown(function(e) {
        if (e.keyCode === 13) {
            var msg = $(this).val();
            author = $("#author").val();
            var image = $("#image").val();
            var group = $("#room").val();
            // First message is always the author's name
            if (author == null) {
                author = msg;
            }

            subSocket.push(jQuery.stringifyJSON({ author: author, message: msg, image: image, group_id: group}));
            $(this).val('');

            input.attr('disabled', 'disabled');
            if (myName === false) {
                myName = msg;
            }
        }
    });

    function addMessage(message_id, id, image, author, message, color, datetime) {
        $("li#top").removeAttr('id');
        
        var date = new Date(datetime);
        var year = date.getFullYear().toString();
        var month = addZero(date.getMonth() + 1).toString();
        var day = addZero(date.getDate()).toString();
        var hours = addZero(date.getHours()).toString();
        var minutes = addZero(date.getMinutes()).toString();

        var correctDate = year.concat("-", month, "-", day, " ",
          hours, ":", minutes);

        
        
    	content.append(
        		
        '<li  data-id="'+message_id+'" id="top" class="left clearfix"><span class="chat-img pull-left">'+
        '<img src="upload/'+image+'" alt="User Avatar"  style="border-radius: 50%;"  width="50" height="50" /></span>'+
        '<div class="chat-body clearfix" style="word-wrap: break-word"><div class="header">'+
        '<strong class="primary-font"><a href="/ITMount/UserServlet?user_id='+id+'">'+author+'</a></strong><small class="pull-right text-muted">'+
        '<span class="glyphicon glyphicon-time"></span>'+ correctDate +'</small></div><p>'+
        message + '</p></div></li>');
        
    	$("div.panel-body").delay(200).animate({
            scrollTop : $("#top").offset().top

        });
    }
    
    function addZero(i) {
    	  if (i < 10) {
    	   i = "0" + i;
    	  }
    	  return i;
    	 }
});

