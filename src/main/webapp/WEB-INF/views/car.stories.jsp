<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="java.util.List, java.util.ArrayList, com.rickyaut.gallery.Vehicle, com.rickyaut.gallery.Image, org.apache.commons.collections.CollectionUtils"%>
<div id="wrapper">
	<div id="sidebar-wrapper">
	  	<ul class="sidebar-nav">
	    	<li class="sidebar-brand"><a href="/brand/${selectedBrand.shortName }/cars"><spring:message code="${selectedBrand.code }"/></a></li>
	    	<c:forEach items="${cars }" var="car">
	    		<li><c:choose>
		    	<c:when test="${car.name ne selectedCar.name }">
				    <a href="/brand/${selectedBrand.shortName }/car/${fn:replace(fn:toLowerCase(car.name), ' ', '-')}/images">${car.name }</a>
		    	</c:when>
		    	<c:otherwise>
				    ${car.name }
		    	</c:otherwise>
	    		</c:choose></li>
	    	</c:forEach>
	  	</ul>
	</div>
	<div id="page-content-wrapper">
		<div class="page-content inset">
			<%--<a id="menu-toggle" href="#" class="btn btn-default"><i class="icon-reorder"></i></a> --%>
			<ol class="breadcrumb">
			  <li><spring:message code="cars"/></li>
			  <li><a href="/brand/${selectedBrand.shortName }/cars"><spring:message code="${selectedBrand.code }"/></a></li>
			  <li class="active">${selectedCar.name }</li>
			</ol>
			<ul class="nav nav-tabs">
			  	<li><a href="/brand/${selectedBrand.shortName }/car/${fn:replace(fn:toLowerCase(selectedCar.name), ' ', '-')}/images">Photos</a></li>
			  	<li><a href="/brand/${selectedBrand.shortName }/car/${fn:replace(fn:toLowerCase(selectedCar.name), ' ', '-')}/videos">Videos</a></li>
			  	<li class="active"><a href="javascript:;">Stories</a></li>
			</ul>
			<div id="stories">
				<c:forEach items="${selectedCar.stories }" var="story" varStatus="status">
				<div class="story">
					<span class="title"><b>${story.title}</b></span>
					<span class="pubdate">[<fmt:formatDate pattern="EEE MMM dd HH:mm yyyy" value="${story.date}" />]</span>
					<div>${story.description}</div>
				</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>