<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="com.rickyaut.gallery.*"%>
<div>
	<ol class="breadcrumb">
	  <li><spring:message code="cars"/></li>
	  <li><spring:message code="${selectedBrand.code }"/></li>
	</ol>
	
	<div class="row">
		<c:forEach items="${cars }" var="car">
			<c:if test="${not empty car.name }">
				<c:set var="carName">${fn:toLowerCase(car.name)}</c:set>
				<c:set var="carName"><%=GalleryUtils.toStandardName(pageContext.getAttribute("carName").toString()) %></c:set>
		        <div class="col-sm-6 col-md-3">
		          <a href="/brand/${selectedBrand.shortName }/car/${carName}/images" class="thumbnail">
		            <img src="${car.thumbnailUrl }" style="width:180px" alt="${carName} model image">
		            <div class="caption">
		              <h3>${car.name }</h3>
		            </div>
		          </a>
		        </div>
			</c:if>
		</c:forEach>
    </div>
</div>
