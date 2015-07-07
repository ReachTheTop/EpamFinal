  <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/WEB-INF/teg.tld"%>
  <div class="col-lg-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-content mailbox-content">
                        <div class="file-manager">
                            <a class="btn btn-block btn-primary compose-mail" href="<c:url value='FeedbackServlet?action=compose' />"><t:i18n id="Feedback.menu.ComposeMail"/></a>
                            <div class="space-25"></div>
                            <h5><t:i18n id="Feedback.menu.Folders"/></h5>
                            <ul class="folder-list m-b-md" style="padding: 0">

                                <li><a href="<c:url value='FeedbackServlet?action=inbox' />"> <i class="fa fa-inbox "></i> <t:i18n id="Feedback.menu.Inbox"/> <c:if test="${newMessage>0}">
                                <span class="label label-warning pull-right">${newMessage}</span>
                               </c:if> </a></li>
                                <li><a href="<c:url value='FeedbackServlet?action=send' />"> <i class="fa fa-envelope-o"></i> <t:i18n id="Feedback.menu.SendMail"/></a></li>
                                <li><a href="<c:url value='FeedbackServlet?action=important' />"> <i class="fa fa-certificate"></i> <t:i18n id="Feedback.menu.Important"/></a></li>

                             
                                <li><a href="<c:url value='FeedbackServlet?action=trash' />"> <i class="fa fa-trash-o"></i> <t:i18n id="Feedback.menu.Trash"/></a></li>
                            </ul>
                            <h5><t:i18n id="Feedback.menu.Categories"/></h5>
                            <ul class="category-list" style="padding: 0">
                                <li><a href="<c:url value='FeedbackServlet?action=${type }&filter=Message' />"> <i class="fa fa-circle text-info"></i> <t:i18n id="Feedback.menu.Message"/> </a></li>
                                <li><a href="<c:url value='FeedbackServlet?action=${type }&filter=Bug' />"> <i class="fa fa-circle text-danger"></i> <t:i18n id="Feedback.menu.Bug"/></a></li>
                                <li><a href="<c:url value='FeedbackServlet?action=${type }&filter=Сomplaint' />"> <i class="fa fa-circle text-warning"></i> <t:i18n id="Feedback.menu.Complaint"/></a></li>
                            </ul>

                           
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>
           

