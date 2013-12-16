<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="java.util.List, java.util.ArrayList, com.rickyaut.gallery.Vehicle, com.rickyaut.gallery.Image, org.apache.commons.collections.CollectionUtils"%>
<div id="wrapper">
	<div id="sidebar-wrapper">
	  	<ul class="sidebar-nav">
	    	<li class="sidebar-brand"><a href="/brand/${selectedBrand.shortName }/boats"><spring:message code="${selectedBrand.code }"/></a></li>
	    	<c:forEach items="${boats }" var="boat">
	    		<li><c:choose>
		    	<c:when test="${boat.name ne selectedBoat.name }">
				    <a href="/brand/${selectedBrand.shortName }/boat/${fn:replace(fn:toLowerCase(boat.name), ' ', '-')}/images">${boat.name }</a>
		    	</c:when>
		    	<c:otherwise>
				    ${boat.name }
		    	</c:otherwise>
	    		</c:choose></li>
	    	</c:forEach>
	  	</ul>
	</div>
	<div id="page-content-wrapper">
		<div class="page-content inset">
			<ol class="breadcrumb">
			  <li><spring:message code="boats"/></li>
			  <li><a href="/brand/${selectedBrand.shortName }/boats"><spring:message code="${selectedBrand.code }"/></a></li>
			  <li class="active">${selectedBoat.name }</li>
			</ol>
			<%
			List<Image> images = new ArrayList<Image>();
			Vehicle boat = (Vehicle)request.getAttribute("selectedBoat");
			if(boat.getImages()!=null){
				CollectionUtils.addAll(images, boat.getImages().iterator());
			}
			if(boat.getExteriorImages()!=null){
				CollectionUtils.addAll(images, boat.getExteriorImages().iterator());
			}
			if(boat.getInteriorImages() != null){
				CollectionUtils.addAll(images, boat.getInteriorImages().iterator());
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
	</div>
</div>