<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="java.util.List, java.util.ArrayList, com.rickyaut.gallery.*, org.apache.commons.collections.CollectionUtils"%>
<div id="wrapper">
	<div id="sidebar-wrapper">
	  	<ul class="sidebar-nav">
	    	<li class="sidebar-brand"><a href="/brand/${selectedBrand.shortName }/cars"><spring:message code="${selectedBrand.code }"/></a></li>
	    	<c:forEach items="${cars }" var="car">
	    		<li><c:choose>
		    	<c:when test="${car.name ne selectedCar.name }">
					<c:set var="carName">${fn:toLowerCase(car.name)}</c:set>
					<c:set var="carName"><%=GalleryUtils.toStandardName(pageContext.getAttribute("carName").toString()) %></c:set>
				    <a href="/brand/${selectedBrand.shortName }/car/${carName}/videos">${car.name }</a>
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
				<c:set var="carName">${fn:toLowerCase(selectedCar.name)}</c:set>
				<c:set var="carName"><%=GalleryUtils.toStandardName(pageContext.getAttribute("carName").toString()) %></c:set>
			  	<li><a href="/brand/${selectedBrand.shortName }/car/${carName}/images">Photos</a></li>
			  	<li class="active"><a href="javascript:;">Videos</a></li>
			  	<li><a href="/brand/${selectedBrand.shortName }/car/${carName}/stories">Stories</a></li>
			  	<li style="float:right"><%@include file="../includes/socialmedia.in.top.jsp" %></li>
			</ul>
			<div id="video-player">
			<c:if test="${fn:length(selectedCar.videos)>0 }">
				<iframe id="player" type="text/html" width="640" height="360" src="https://www.youtube.com/embed/${selectedCar.videos[0].youtubeID }" frameborder="0" allowfullscreen="true"></iframe>
				<%-- <script type="text/javascript" src="/js/swfobject.js"></script>    
				<div id="ytapiplayer">
				    You need Flash player 8+ and JavaScript enabled to view this video.
				</div>
				
				<script type="text/javascript">
					embedSWF("${param.youtubeID }")
				
				</script>--%>
			</c:if> 
			</div>
			<h3><spring:message code="other.videos" arguments="${selectedBrand.shortName }, ${selectedCar.name }"/></h3>
			<div id="video-thumbnails">
				<c:forEach items="${selectedCar.videos }" var="video" varStatus="status">
					<c:if test="${param.youtubeID ne video.youtubeID }">
						<a href="video?youtubeID=${video.youtubeID }">
							<img src="${video.thumbnailURL }" alt="${video.title }" title="${video.title }"/>
						</a>
					</c:if>
				</c:forEach>
			</div>
		</div>
		<%@include file="../includes/chitika.jsp" %>
	</div>
</div>