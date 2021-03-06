<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>

<div class="container">
	<div class="hero-unit">

		<div id="asd"></div>

		<div class="form-group">
			<label><t:i18n id='article.subject'/></label> <input type="text"
				class='form-control' value="${article.header }" id='art-header'>
			<label><t:i18n id='article.tag'/></label> <select class='form-control'
				value="${article.course_id }" id="article-course">
				<c:forEach items="${courses }" var="course">
					<option value="${course.id }"><c:out
							value="${course.name }" />
					</option>
				</c:forEach>
			</select>
		</div>




		<div id="alerts"></div>
		<div class="btn-toolbar" data-role="editor-toolbar"
			data-target="#editor">
			<div class="btn-group">
				<a class="btn dropdown-toggle" data-toggle="dropdown" title="Font"><i
					class="icon-font"></i><b class="caret"></b></a>
				<ul class="dropdown-menu">
				</ul>
			</div>
			<div class="btn-group">
				<a class="btn dropdown-toggle" data-toggle="dropdown"
					title="Font Size"><i class="icon-text-height"></i>&nbsp;<b
					class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a data-edit="fontSize 5"><font size="5">Huge</font></a></li>
					<li><a data-edit="fontSize 3"><font size="3">Normal</font></a></li>
					<li><a data-edit="fontSize 1"><font size="1">Small</font></a></li>
				</ul>
			</div>
			<div class="btn-group">
				<a class="btn" data-edit="bold" title="Bold (Ctrl/Cmd+B)"><i
					class="icon-bold"></i></a> <a class="btn" data-edit="italic"
					title="Italic (Ctrl/Cmd+I)"><i class="icon-italic"></i></a> <a
					class="btn" data-edit="strikethrough" title="Strikethrough"><i
					class="icon-strikethrough"></i></a> <a class="btn"
					data-edit="underline" title="Underline (Ctrl/Cmd+U)"><i
					class="icon-underline"></i></a>
			</div>
			<div class="btn-group">
				<a class="btn" data-edit="insertunorderedlist" title="Bullet list"><i
					class="icon-list-ul"></i></a> <a class="btn"
					data-edit="insertorderedlist" title="Number list"><i
					class="icon-list-ol"></i></a> <a class="btn" data-edit="outdent"
					title="Reduce indent (Shift+Tab)"><i class="icon-indent-left"></i></a>
				<a class="btn" data-edit="indent" title="Indent (Tab)"><i
					class="icon-indent-right"></i></a>
			</div>
			<div class="btn-group">
				<a class="btn" data-edit="justifyleft"
					title="Align Left (Ctrl/Cmd+L)"><i class="icon-align-left"></i></a>
				<a class="btn" data-edit="justifycenter" title="Center (Ctrl/Cmd+E)"><i
					class="icon-align-center"></i></a> <a class="btn"
					data-edit="justifyright" title="Align Right (Ctrl/Cmd+R)"><i
					class="icon-align-right"></i></a> <a class="btn"
					data-edit="justifyfull" title="Justify (Ctrl/Cmd+J)"><i
					class="icon-align-justify"></i></a>
			</div>
			<div class="btn-group">
				<a class="btn dropdown-toggle" data-toggle="dropdown"
					title="Hyperlink"><i class="icon-link"></i></a>
				<div class="dropdown-menu input-append">
					<input class="span2" placeholder="URL" type="text"
						data-edit="createLink" />
					<button class="btn" type="button"><t:i18n id='article.link.add'/></button>
				</div>
				<a class="btn" data-edit="unlink" title="Remove Hyperlink"><i
					class="icon-cut"></i></a>

			</div>


			<div class="btn-group">
				<a class="btn" data-edit="undo" title="Undo (Ctrl/Cmd+Z)"><i
					class="icon-undo"></i></a> <a class="btn" data-edit="redo"
					title="Redo (Ctrl/Cmd+Y)"><i class="icon-repeat"></i></a> <a
					class="btn" data-edit="formatBlock pre" title="Code"><i
					class="icon-cog"></i></a>
			</div>
			<div class="btn-group pull-right">
				<a class="btn" id="create-article"><t:i18n id='article.update'/></a>
			</div>
			<input type="text" data-edit="inserttext" id="voiceBtn"
				x-webkit-speech="">
		</div>
		<div id="editor">${article.data }</div>

	</div>


</div>
<script type="text/javascript">
	$("a#create-article")
			.click(
					function() {

						$
								.post(
										"ArticleServlet?action=update&article_id=${article.id}",
										{
											article : $("div#editor").html(),
											header : $("input#art-header")
													.val(),
											course_id : $('#article-course')
													.val()
										},
										function(response) {
											$("div#editor").html(response);
											$('div#asd').append(response);
											window.location.href = '/ITMount/ArticleServlet?action=show&article_id='
													+ response.id;
										});
					});
</script>

<script>
	$(function() {
		function initToolbarBootstrapBindings() {
			var fonts = [ 'Serif', 'Sans', 'Arial', 'Arial Black', 'Courier',
					'Courier New', 'Comic Sans MS', 'Helvetica', 'Impact',
					'Lucida Grande', 'Lucida Sans', 'Tahoma', 'Times',
					'Times New Roman', 'Verdana' ], fontTarget = $(
					'[title=Font]').siblings('.dropdown-menu');
			$
					.each(
							fonts,
							function(idx, fontName) {
								fontTarget
										.append($('<li><a data-edit="fontName ' + fontName +'" style="font-family:\''+ fontName +'\'">'
												+ fontName + '</a></li>'));
							});
			$('a[title]').tooltip({
				container : 'body'
			});
			$('.dropdown-menu input').click(function() {
				return false;
			}).change(
					function() {
						$(this).parent('.dropdown-menu').siblings(
								'.dropdown-toggle').dropdown('toggle');
					}).keydown('esc', function() {
				this.value = '';
				$(this).change();
			});

			$('[data-role=magic-overlay]').each(
					function() {
						var overlay = $(this), target = $(overlay
								.data('target'));
						overlay.css('opacity', 0).css('position', 'absolute')
								.offset(target.offset()).width(
										target.outerWidth()).height(
										target.outerHeight());
					});
			if ("onwebkitspeechchange" in document.createElement("input")) {
				var editorOffset = $('#editor').offset();
				$('#voiceBtn').css('position', 'absolute').offset({
					top : editorOffset.top,
					left : editorOffset.left + $('#editor').innerWidth() - 35
				});
			} else {
				$('#voiceBtn').hide();
			}
		}
		;
		function showErrorAlert(reason, detail) {
			var msg = '';
			if (reason === 'unsupported-file-type') {
				msg = "Unsupported format " + detail;
			} else {
				console.log("error uploading file", reason, detail);
			}
			$(
					'<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'
							+ '<strong>File upload error</strong> '
							+ msg
							+ ' </div>').prependTo('#alerts');
		}
		;
		initToolbarBootstrapBindings();
		$('#editor').wysiwyg({
			fileUploadError : showErrorAlert
		});
		window.prettyPrint && prettyPrint();
	});
</script>

<script type="text/javascript">
	$("a#create-article")
			.click(
					function() {
						if(!$('#art-header').val()){
							showToaast("<t:i18n id='article.error.header'/>", 0);
						}else{
						if($('div#editor').html().length <= 88000){
						$.post("ArticleServlet?action=create",
										{
											article : $("div#editor").html(),
											header : $("input#art-header")
													.val(),
											course_id : $('#article-course')
													.val()
										},
										function(response) {
											$("div#editor").html(response);
											$('div#asd').append(response);
											window.location.href = '/ITMount/ArticleServlet?action=show&article_id='
													+ response.id;
						});
						}else{
							showToaast("<t:i18n id='article.error.long'/>", 0);
						}
					}
					});
</script>


<script type="text/javascript">
		function showToaast(message, issucces) {
			var i = -1;
			var toastCount = 0;
			var $toastlast;

			var shortCutFunction;
			if (issucces == 1) {
				shortCutFunction = "success";
			}

			if (issucces == 0) {
				shortCutFunction = "error";
			}

			var msg = $('#message').val();
			var title = $('#title').val() || '';
			var $showDuration = $('#showDuration');
			var $hideDuration = $('#hideDuration');
			var $timeOut = $('#timeOut');
			var $extendedTimeOut = $('#extendedTimeOut');
			var $showEasing = $('#showEasing');
			var $hideEasing = $('#hideEasing');
			var $showMethod = $('#showMethod');
			var $hideMethod = $('#hideMethod');
			var toastIndex = toastCount++;

			toastr.options = {

				closeButton : true,
				debug : true,
				newestOnTop : false,
				progressBar : false,
				positionClass : "toast-top-right",
				preventDuplicates : false,
				onclick : null,
				timeOut : 10000,
				showDuration : 300,
				hideDuration : 1000,
				extendedTimeOut : 1000,

				showEasing : "swing",
				hideEasing : "linear",
				showMethod : "fadeIn",
				hideMethod : "fadeOut"

			};

			msg = message;

			$('#toastrOptions').text(
					'Command: toastr["' + shortCutFunction + '"]("' + msg
							+ (title ? '", "' + title : '')
							+ '")\n\ntoastr.options = '
							+ JSON.stringify(toastr.options, null, 2));

			var $toast = toastr[shortCutFunction](msg, title); // Wire up an event handler to a button in the toast, if it exists
			$toastlast = $toast;

			if (typeof $toast === 'undefined') {
				return;
			}

		}
	</script>

