<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="java.util.List, java.util.ArrayList, com.rickyaut.gallery.*, org.apache.commons.collections.CollectionUtils"%>
<div id="wrapper">
	<div id="sidebar-wrapper">
	  	<ul class="sidebar-nav">
	    	<li class="sidebar-brand"><a href="/brand/${selectedBrand.shortName }/trucks"><spring:message code="${selectedBrand.code }"/></a></li>
	    	<c:forEach items="${trucks }" var="truck">
	    		<li><c:choose>
		    	<c:when test="${truck.name ne selectedTruck.name }">
					<c:set var="truckName">${fn:toLowerCase(truck.name)}</c:set>
					<c:set var="truckName"><%=GalleryUtils.toStandardName(pageContext.getAttribute("truckName").toString()) %></c:set>
				    <a href="/brand/${selectedBrand.shortName }/truck/${truckName}/images">${truck.name }</a>
		    	</c:when>
		    	<c:otherwise>
				    ${truck.name }
		    	</c:otherwise>
	    		</c:choose></li>
	    	</c:forEach>
	  	</ul>
	</div>
	<div id="page-content-wrapper">
		<div class="page-content inset">
			<ol class="breadcrumb">
			  <li><spring:message code="trucks"/></li>
			  <li><a href="/brand/${selectedBrand.shortName }/trucks"><spring:message code="${selectedBrand.code }"/></a></li>
			  <li class="active">${selectedTruck.name }</li>
			</ol>
			<%
			List<Image> images = new ArrayList<Image>();
			Vehicle truck = (Vehicle)request.getAttribute("selectedTruck");
			if(truck.getImages()!=null){
				CollectionUtils.addAll(images, truck.getImages().iterator());
			}
			if(truck.getExteriorImages()!=null){
				CollectionUtils.addAll(images, truck.getExteriorImages().iterator());
			}
			if(truck.getInteriorImages() != null){
				CollectionUtils.addAll(images, truck.getInteriorImages().iterator());
			}
			pageContext.setAttribute("images", images);
			%>
			<div id="truck-images-carousel" class="carousel slide" data-ride="carousel">
		      <!-- Indicators -->
		      <ol class="carousel-indicators">
		      	<c:forEach items="${images }" var="image" varStatus="status">
			        <li data-target="#truck-images-carousel" data-slide-to="${status.index }" class="${status.index==0?'active':'' }"></li>
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
		      <a class="left carousel-control" href="#truck-images-carousel" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
		      <a class="right carousel-control" href="#truck-images-carousel" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
		    </div>
		</div>
	</div>
</div>
