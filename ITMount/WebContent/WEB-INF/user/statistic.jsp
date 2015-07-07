<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>


<!-- METISMENU SCRIPTS -->
<script src="assets/js/jquery.metisMenu.js"></script>
<!-- MORRIS CHART SCRIPTS -->
<script src="assets/js/raphael-2.1.0.min.js"></script>
<script src="assets/js/morris.js"></script>



<a  href="#" 
	id="openBtn"><t:i18n id="admin.statistic"/></a>

<div id="modal-content" class="modal fade" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3><t:i18n id="admin.statistic"/></h3>
			</div>

			<div class="modal-body">
				<h4><t:i18n id="admin.statistic.CountStudentCompletedCourses"/></h4>

				<select id="selectCourse" class="form-control input-lg">
					<!-- 					<option value="option1">Option 1</option> -->

				</select>

				<div id="morris-bar-chart"></div>
			</div>


			<div class="panel-body">
				<h4><t:i18n id="admin.statistic.CountStudentOnEachCourse"/></h4>
				<div id="morris-donut-chart"></div>
			</div>


			<div class="modal-footer">
				<a href="#" class="btn btn-primary" data-dismiss="modal"><t:i18n id="admin.statistic.close"/></a>

			</div>
		</div>
	</div>
</div>

<script>
	$('#modal-content').on('hidden.bs.modal', function() { //listen for user to open modal
		$(function(e) {
			$("#morris-donut-chart").empty(); //clear chart so it doesn't create multiple if multiple clicks
			$("#morris-bar-chart").empty();
			$("#selectCourse").empty();
			
		});
	});
</script>



<script>
	$('#modal-content')
			.on(
					'shown.bs.modal',
					function() { //listen for user to open modal
						$(function(e) {
							

							// Create a Bar Chart with Morris

							request = $.ajax({
								type : "POST",
								url : "StatisticServlet",
								data : new FormData(this),
								processData : false,
								contentType : false,
								dataType : "json",

								success : function(dataJson) {

									if (dataJson['firstList'].length === 0) {
										$("div#morris-donut-chart").text(
												"No data");
									}

									Morris.Donut({
										element : 'morris-donut-chart',
										data : dataJson['firstList'],
										resize : true
									});

									if (dataJson['secondList'].length === 0) {
										$("div#morris-bar-chart").text(
												"No data");
									}

									Morris.Bar({
										element : 'morris-bar-chart',
										data : dataJson['secondList'],
										xkey : 'y',
										ykeys : [ 'a' ],
										
										hideHover : 'auto',
										resize : true
									});

									var your_object = dataJson['thirdList'];
									$.each(your_object, function(k, item) {

										$('#selectCourse').append(
												$("<option></option>").attr(
														"value", item.id).text(
														item.name));
									});

								},
								error : function() {
									alert("error");

								}
							});

							$(document)
									.ready(
											function() {
												$("#selectCourse").attr(
														"selectedIndex", -1);

												$("#selectCourse")
														.change(
																function() {
																	var selsectID = $(
																			"#selectCourse option:selected")
																			.val();

																	request = $
																			.ajax({
																				type : "POST",
																				url : "ComplitedCourseServlet",
																				data : {
																					"idCourse" : selsectID
																				},
																				dataType : "json",
																				success : function(
																						dataJson) {

																					$(
																							"#morris-bar-chart")
																							.empty();

																					if (dataJson.length === 0) {
																						$(
																								"div#morris-bar-chart")
																								.text(
																										"No data");
																					}

																					Morris
																							.Bar({
																								element : 'morris-bar-chart',
																								data : dataJson,
																								xkey : 'y',
																								ykeys : [ 'a' ],
																								labels : [ 'Series A' ],
																								hideHover : 'auto',
																								resize : true
																							});

																				},
																				error : function() {
																					alert("error");

																				}
																			});

																});
											});

						});
					});
</script>



<script type="text/javascript">
	$('#openBtn').click(function() {
		$('#modal-content').modal({
			show : true
		});

	});
</script>



