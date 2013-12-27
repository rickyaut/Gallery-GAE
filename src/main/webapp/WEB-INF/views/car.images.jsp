<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="java.util.List, java.util.ArrayList, com.rickyaut.gallery.Vehicle, com.rickyaut.gallery.Image, org.apache.commons.collections.CollectionUtils, org.apache.commons.lang.StringUtils"%>
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
			<div>
				<ul class="nav nav-tabs">
				  	<li class="active"><a href="javascript:;">Photos</a></li>
				  	<li><a href="/brand/${selectedBrand.shortName }/car/${fn:replace(fn:toLowerCase(selectedCar.name), ' ', '-')}/videos">Videos</a></li>
				  	<li><a href="/brand/${selectedBrand.shortName }/car/${fn:replace(fn:toLowerCase(selectedCar.name), ' ', '-')}/stories">Stories</a></li>
				  	<li style="float:right"><%@include file="../includes/socialmedia.in.top.jsp" %></li>
				</ul>
			</div>
			<%
			List<Image> images = new ArrayList<Image>();
			Vehicle car = (Vehicle)request.getAttribute("selectedCar");
			if(car.getImages()!=null){
				CollectionUtils.addAll(images, car.getImages().iterator());
			}
			if(car.getExteriorImages()!=null){
				CollectionUtils.addAll(images, car.getExteriorImages().iterator());
			}
			if(car.getInteriorImages() != null){
				CollectionUtils.addAll(images, car.getInteriorImages().iterator());
			}
			pageContext.setAttribute("images", images);
			%>
			<div id="car-images-carousel" class="carousel slide" data-ride="carousel">
		      <!-- Indicators -->
		      <ol class="carousel-indicators">
		      	<c:forEach items="${images }" var="image" varStatus="status">
			        <li data-target="#car-images-carousel" data-slide-to="${status.index }" class="${status.index==0?'active':'' }"></li>
		      	</c:forEach>
		      </ol>
		      <div class="carousel-inner">
		      	<c:forEach items="${images }" var="image" varStatus="status">
			        <div class="item ${status.index==0?'active':'' }">
			          <img src="${image.imageUrl }">
			          <div class="container">
			            <div class="carousel-caption">
			              <h1></h1>
			              <p>${image.description }</p>
			            </div>
			          </div>
			        </div>
		        </c:forEach>
		      </div>
		      <a class="left carousel-control" href="#car-images-carousel" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
		      <a class="right carousel-control" href="#car-images-carousel" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
		    </div>
		</div>
	</div>
</div>