<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="com.rickyaut.gallery.*"%>
<div>
	<ol class="breadcrumb">
	  <li><spring:message code="trucks"/></li>
	  <li><spring:message code="${selectedBrand.code }"/></li>
	</ol>
	
	<div class="row">
		<c:forEach items="${trucks }" var="truck">
			<c:if test="${not empty truck.name }">
				<c:set var="truckName">${fn:toLowerCase(truck.name)}</c:set>
				<c:set var="truckName"><%=GalleryUtils.toStandardName(pageContext.getAttribute("truckName").toString()) %></c:set>
		        <div class="col-sm-6 col-md-3">
		          <a href="/brand/${selectedBrand.shortName }/truck/${truckName}/images" class="thumbnail">
		            <img src="${truck.thumbnailUrl }" style="width:180px">
		            <div class="caption">
		              <h3>${truck.name }</h3>
		            </div>
		          </a>
		        </div>
			</c:if>
		</c:forEach>
    </div>
</div>
