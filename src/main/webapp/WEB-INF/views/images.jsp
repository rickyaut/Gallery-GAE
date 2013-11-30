<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="java.util.List, java.util.ArrayList, com.rickyaut.gallery.Vehicle, com.rickyaut.gallery.Image, org.apache.commons.collections.CollectionUtils"%>
<div>
	<ol class="breadcrumb">
	  <li><spring:message code="vehicles"/></li>
	  <li><a href="/brand/${selectedBrand.shortName }/vehicles"><spring:message code="${selectedBrand.code }"/></a></li>
	  <li class="active">${selectedVehicle.name }</li>
	</ol>
	<%
	List<Image> images = new ArrayList<Image>();
	Vehicle vehicle = (Vehicle)request.getAttribute("selectedVehicle");
	if(vehicle.getImages()!=null){
		CollectionUtils.addAll(images, vehicle.getImages().iterator());
	}
	if(vehicle.getExteriorImages()!=null){
		CollectionUtils.addAll(images, vehicle.getExteriorImages().iterator());
	}
	if(vehicle.getInteriorImages() != null){
		CollectionUtils.addAll(images, vehicle.getInteriorImages().iterator());
	}
	System.out.println("number of images: "+images.size());
	pageContext.setAttribute("images", images);
	%>
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
      <!-- Indicators -->
      <ol class="carousel-indicators">
      	<c:forEach items="${images }" var="image" varStatus="status">
	        <li data-target="#myCarousel" data-slide-to="${status.index }" class="${status.index==0?'active':'' }"></li>
      	</c:forEach>
      </ol>
      <div class="carousel-inner">
      	<c:forEach items="${images }" var="image" varStatus="status">
	        <div class="item ${status.index==0?'active':'' }">
	          <img src="${image.imageUrl }">
	          <div class="container">
	            <div class="carousel-caption">
	              <h1>${image.description }</h1>
	            </div>
	          </div>
	        </div>
        </c:forEach>
      </div>
      <a class="left carousel-control" href="#myCarousel" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
      <a class="right carousel-control" href="#myCarousel" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
    </div>
</div>
