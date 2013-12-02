<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="java.util.List, java.util.ArrayList, com.rickyaut.gallery.Vehicle, com.rickyaut.gallery.Image, org.apache.commons.collections.CollectionUtils"%>
<div>
	<ol class="breadcrumb">
	  <li><spring:message code="cars"/></li>
	  <li><a href="/brand/${selectedBrand.shortName }/cars"><spring:message code="${selectedBrand.code }"/></a></li>
	  <li class="active">${selectedCar.name }</li>
	</ol>
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
	              <h1></h1>
	              <p>${image.description }</p>
	            </div>
	          </div>
	        </div>
        </c:forEach>
      </div>
      <a class="left carousel-control" href="#myCarousel" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
      <a class="right carousel-control" href="#myCarousel" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
    </div>
</div>
