<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>

<div class="container">

	<div class="hero-unit">


		<div class="form-group">
			<label><t:i18n id="faq.question"/></label> <input type="text" class='form-control'
				id='art-header' value="${qa.header }"> <label><t:i18n id="faq.category"/></label>
			<div class="input-group">
				<select class='form-control' id="article-course">
					<c:forEach items="${categories }" var="category">
						<option value="${category.id }"><c:out
								value="${category.category}" />
						</option>
					</c:forEach>
				</select> <span class="input-group-btn">

					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<span class="caret"></span> <span class="sr-only">Toggle
							Dropdown</span>
					</button>
					<ul class="dropdown-menu">

						<li><a data-toggle="modal" href="#addCategory"><t:i18n id="faq.category.add"/></a></li>
						<li><a data-toggle="modal" id="editCat" href="#editCategory"><t:i18n id="faq.category.edit"/></a></li>


					</ul>
				</span>
			</div>
		</div>
		
		<script>
	$("a#editCat").click(function(){
		$("input#category_id").val($("#article-course").val());
		$("input#categoryName").val($.trim( $("select option:selected").text()));
	});
</script>

		<script type="text/javascript">
			<c:choose>
			<c:when test="${empty qa.course_id }">
			$("#article-course").val($("#article-course option:first").val());
			</c:when>
			<c:otherwise>
			$("#article-course").val("${qa.course_id }");
			</c:otherwise>
			</c:choose>
		</script>





		<div id="alerts"></div>
		<div class="btn-toolbar" data-role="editor-toolbar"
			data-target="#editor">
			<div class="btn-group">
				<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
					title="Font"><i class="icon-font"></i><b class="caret"></b></a>
				<ul class="dropdown-menu">
				</ul>
			</div>
			<div class="btn-group">
				<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
					title="Font Size"><i class="icon-text-height"></i>&nbsp;<b
					class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a data-edit="fontSize 5"><font size="5">Huge</font></a></li>
					<li><a data-edit="fontSize 3"><font size="3">Normal</font></a></li>
					<li><a data-edit="fontSize 1"><font size="1">Small</font></a></li>
				</ul>
			</div>
			<div class="btn-group">
				<a class="btn btn-primary" data-edit="bold"
					title="Bold (Ctrl/Cmd+B)"><i class="icon-bold"></i></a> <a
					class="btn btn-primary" data-edit="italic"
					title="Italic (Ctrl/Cmd+I)"><i class="icon-italic"></i></a> <a
					class="btn btn-primary" data-edit="strikethrough"
					title="Strikethrough"><i class="icon-strikethrough"></i></a> <a
					class="btn btn-primary" data-edit="underline"
					title="Underline (Ctrl/Cmd+U)"><i class="icon-underline"></i></a>
			</div>
			<div class="btn-group">
				<a class="btn btn-primary" data-edit="insertunorderedlist"
					title="Bullet list"><i class="icon-list-ul"></i></a> <a
					class="btn btn-primary" data-edit="insertorderedlist"
					title="Number list"><i class="icon-list-ol"></i></a> <a
					class="btn btn-primary" data-edit="outdent"
					title="Reduce indent (Shift+Tab)"><i class="icon-indent-left"></i></a>
				<a class="btn btn-primary" data-edit="indent" title="Indent (Tab)"><i
					class="icon-indent-right"></i></a>
			</div>
			<div class="btn-group">
				<a class="btn btn-primary" data-edit="justifyleft"
					title="Align Left (Ctrl/Cmd+L)"><i class="icon-align-left"></i></a>
				<a class="btn btn-primary" data-edit="justifycenter"
					title="Center (Ctrl/Cmd+E)"><i class="icon-align-center"></i></a> <a
					class="btn btn-primary" data-edit="justifyright"
					title="Align Right (Ctrl/Cmd+R)"><i class="icon-align-right"></i></a>
				<a class="btn btn-primary" data-edit="justifyfull"
					title="Justify (Ctrl/Cmd+J)"><i class="icon-align-justify"></i></a>
			</div>
			<div class="btn-group">
				<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
					title="Hyperlink"><i class="icon-link"></i></a>
				<div class="dropdown-menu input-append">

					<input class="form-control" placeholder="URL" type="text"
						data-edit="createLink" />
					<button class="btn btn-primary" type="button">
						<t:i18n id='article.link.add' />
					</button>
				</div>
				<a class="btn btn-primary" data-edit="unlink"
					title="Remove Hyperlink"><i class="icon-cut"></i></a>

			</div>


			<div class="btn-group">
				<a class="btn btn-primary" data-edit="undo"
					title="Undo (Ctrl/Cmd+Z)"><i class="icon-undo"></i></a> <a
					class="btn btn-primary" data-edit="redo" title="Redo (Ctrl/Cmd+Y)"><i
					class="icon-repeat"></i></a> <a class="btn btn-primary"
					data-edit="formatBlock pre" title="Code"><i class="icon-cog"></i></a>
			</div>
			<div class="btn-group pull-right">
				<a class="btn btn-primary" id="create-article"><t:i18n id="faq.question.confirm"/></a>
			</div>
			<input type="text" data-edit="inserttext" id="voiceBtn"
				x-webkit-speech="">
		</div>
		<div id="editor">${qa.data }</div>


	</div>


</div>



<script type="text/javascript">
	$("a#create-article").click(function() {
		if (!$('#art-header').val()) {
			showToaast("<t:i18n id='faq.error.title'/>", 0);
		} else {
			if ($('div#editor').html().length <= 88000) {
				$.post("FaqServlet?action=${action}", {
					article : $("div#editor").html(),
					header : $("input#art-header").val(),
					category_id : $('#article-course').val()
				}, function(response) {
					$("div#editor").html(response);
					$('div#asd').append(response);
					window.location.href = '/ITMount/FaqServlet';
				});
			} else {
				showToaast("<t:i18n id='faq.error.long'/>", 0);
			}
		}
	});
</script>

<script>
	$(function() {
		function initToolbarBootstrapBindings() {
			var fonts = [ 'Serif', 'Arial', 'Arial Black', 'Courier',
					'Courier New', 'Comic Sans MS', 'Lucida Grande',
					'Lucida Sans', 'Tahoma', 'Times', 'Times New Roman',
					'Verdana' ], fontTarget = $('[title=Font]').siblings(
					'.dropdown-menu');
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
							+ '<strong>'+"<t:i18n id='faq.editor.file.error'/>"+'</strong> '
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



