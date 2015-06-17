

$(function() {
	
	/*
	 * 
	 * bootpag - jQuery plugin for dynamic pagination
	 * 
	 * Copyright (c) 2015 botmonster@7items.com
	 * 
	 * Licensed under the MIT license:
	 * http://www.opensource.org/licenses/mit-license.php
	 * 
	 * Project home: http://botmonster.com/jquery-bootpag/
	 * 
	 * Version: 1.0.7
	 * 
	 */
	(function(h,q){h.fn.bootpag=function(p){function m(c,b){b=parseInt(b,10);var d,e=0==a.maxVisible?1:a.maxVisible,k=1==a.maxVisible?0:1,n=Math.floor((b-1)/e)*e,f=c.find("li");a.page=b=0>b?0:b>a.total?a.total:b;f.removeClass(a.activeClass);d=1>b-1?1:a.leaps&&b-1>=a.maxVisible?Math.floor((b-1)/e)*e:b-1;a.firstLastUse&&f.first().toggleClass(a.disabledClass,1===b);e=f.first();a.firstLastUse&&(e=e.next());e.toggleClass(a.disabledClass,1===b).attr("data-lp",d).find("a").attr("href",g(d));k=1==a.maxVisible?
	0:1;d=b+1>a.total?a.total:a.leaps&&b+1<a.total-a.maxVisible?n+a.maxVisible+k:b+1;e=f.last();a.firstLastUse&&(e=e.prev());e.toggleClass(a.disabledClass,b===a.total).attr("data-lp",d).find("a").attr("href",g(d));f.last().toggleClass(a.disabledClass,b===a.total);e=f.filter("[data-lp="+b+"]");k="."+[a.nextClass,a.prevClass,a.firstClass,a.lastClass].join(",.");if(!e.not(k).length){var m=b<=n?-a.maxVisible:0;f.not(k).each(function(b){d=b+1+n+m;h(this).attr("data-lp",d).toggle(d<=a.total).find("a").html(d).attr("href",
	g(d))});e=f.filter("[data-lp="+b+"]")}e.not(k).addClass(a.activeClass);l.data("settings",a)}function g(c){return a.href.replace(a.hrefVariable,c)}var l=this,a=h.extend({total:0,page:1,maxVisible:null,leaps:!0,href:"javascript:void(0);",hrefVariable:"{{number}}",next:"&raquo;",prev:"&laquo;",firstLastUse:!1,first:'<span aria-hidden="true">&larr;</span>',last:'<span aria-hidden="true">&rarr;</span>',wrapClass:"pagination",activeClass:"active",disabledClass:"disabled",nextClass:"next",prevClass:"prev",
	lastClass:"last",firstClass:"first"},l.data("settings")||{},p||{});if(0>=a.total)return this;h.isNumeric(a.maxVisible)||a.maxVisible||(a.maxVisible=parseInt(a.total,10));l.data("settings",a);return this.each(function(){var c,b,d=h(this);c=['<ul class="',a.wrapClass,' bootpag">'];a.firstLastUse&&(c=c.concat(['<li data-lp="1" class="',a.firstClass,'"><a href="',g(1),'">',a.first,"</a></li>"]));a.prev&&(c=c.concat(['<li data-lp="1" class="',a.prevClass,'"><a href="',g(1),'">',a.prev,"</a></li>"]));for(b=
	1;b<=Math.min(a.total,a.maxVisible);b++)c=c.concat(['<li data-lp="',b,'"><a href="',g(b),'">',b,"</a></li>"]);a.next&&(b=a.leaps&&a.total>a.maxVisible?Math.min(a.maxVisible+1,a.total):2,c=c.concat(['<li data-lp="',b,'" class="',a.nextClass,'"><a href="',g(b),'">',a.next,"</a></li>"]));a.firstLastUse&&(c=c.concat(['<li data-lp="',a.total,'" class="last"><a href="',g(a.total),'">',a.last,"</a></li>"]));c.push("</ul>");d.find("ul.bootpag").remove();d.append(c.join(""));c=d.find("ul.bootpag");d.find("li").click(function(){var b=
	h(this);if(!b.hasClass(a.disabledClass)&&!b.hasClass(a.activeClass)){var c=parseInt(b.attr("data-lp"),10);l.find("ul.bootpag").each(function(){m(h(this),c)});l.trigger("page",c)}});m(c,a.page)})}})(jQuery,window);
	
	var $pages;
	function handleCourses(response) {
		$("tbody#courses-body").empty();
		var $body;  
		var $tr; 
		var $td1;
		var $td2;
		var $td3;
		var $a;
		
		

		$pages = response.amount;
		
		$('#page-selection').bootpag({
			total : Math.ceil($pages/10)
		})
		
		var $courses = response.courses;

		$.each($courses, function(index, item) {

			$body = $("tbody#courses-body"); 
			$tr = $("<tr class='course-row'>");
			$td1 = $("<td id='course-name'>");
			$td2 = $("<td id='course-active'>");
			$td3 = $("<td id='course-triger'>");
			
			$tr.append($td1);
			$tr.append($td2);
			$tr.append($td3);
			
			$a = $("<a class='btn' id='triger' >");
			$a.attr('name', item['id']);
			if (item['is_active'] === true) {
				$a.text('Close');

			} else {

				$a.text('Open');
			}

			$td1.text(item['name']);
			$td2.text(item['is_active']);

			$td3.append($a);
			$body.append($tr);
			
		});
		
		

	}
	
	
	$('#page-selection').on("page", function(event, page) {
		
			$.get('AdminServlet?action=courses', {
				search : $("input#search-field").val(),
				page : (page-1)
			}, function(response) {
				handleCourses(response);
			});
		
	});
	

	$("a.courses").click(function() {

		$.get('AdminServlet?action=courses', function(response) {

			handleCourses(response);

		});
	});

	$('body').on(
			'click',
			'a#triger',
			function() {
				var object = $(this);
				$.get('AdminServlet?action=triger&course_id='
						+ $(this).attr('name'), function(response) {

					if (response['is_active'] === true) {
						object.text("Close");

					} else {

						object.text("Open");
					}
					object.parent().prev().text(response['is_active']);

				});
			});

	$("input#search-field").keyup(function() {
		
			$.get('AdminServlet?action=courses', {
				search : $(this).val()
			}, function(response) {
				handleCourses(response);
			});
		
	});

	$("input#search-field").focus(function() {
		$(this).val("");
	});

	
	var $groupPages;
	function handleGroups(response){
		$("tbody#groups-body").empty();
		var $body;
		var $tr; 
		var $td1;
		var $td2;
		var $td3;
		var $td4;
		var $td5;
		var $td6;
		
		
		

		$groupPages = response.amount;
		
		$('#group-page-selection').bootpag({
			total : Math.ceil($groupPages/10)
		})
		
		var $groups = response.groups;

		$.each($groups, function(index, item) {

			$body = $("tbody#groups-body"); 
			$tr = $("<tr class='group-row'>");
			$td1 = $("<td id='course-name'>");
			$td2 = $("<td id='group-name'>");
			$td3 = $("<td id='teacher-first-name'>");
			$td4 = $("<td id='teacher-last-name'>");
			$td5 = $("<td id='is-confirmed'>");
			$td6 = $("<td id='is-active'>");
			
			$tr.append($td1);
			$tr.append($td2);
			$tr.append($td3);
			$tr.append($td4);
			$tr.append($td5);
			$tr.append($td6);
			
			$td1.text(item.course['name']);
			$td2.text(item['name']);
			$td3.text(item.teacher['name']);
			$td4.text(item.teacher['surname']);
			$td5.text(item['isConfirmed']);
			$td6.text(item['is_active']);

			
			$body.append($tr);
			
		});
		

	}
	
	
	$('#group-page-selection').on("page", function(event, page) {
		
		$.get('AdminServlet?action=groups', {
			search : $("input#search-groups").val(),
			page : (page-1)
		}, function(response) {
			handleGroups(response);
		});
	
	});

	
	$("input#search-groups").keyup(function() {
		
		$.get('AdminServlet?action=groups', {
			search : $(this).val()
		}, function(response) {
			handleGroups(response);
		});
	
	});
	
	$("a.groups").click(function() {

		$.get('AdminServlet?action=groups', function(response) {

			handleGroups(response);

		});
	});
	
	$("input#search-groups").focus(function() {
		$(this).val("");
	});

});
