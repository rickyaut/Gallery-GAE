<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="com.rickyaut.gallery.*"%>
<div>
	<ol class="breadcrumb">
	  <li><spring:message code="boats"/></li>
	  <li><spring:message code="${selectedBrand.code }"/></li>
	</ol>
	
	<div class="row">
		<c:forEach items="${boats }" var="boat">
			<c:if test="${not empty boat.name }">
				<c:set var="boatName">${fn:toLowerCase(boat.name)}</c:set>
				<c:set var="boatName"><%=GalleryUtils.toStandardName(pageContext.getAttribute("boatName").toString()) %></c:set>
		        <div class="col-sm-6 col-md-3">
		          <a href="/brand/${selectedBrand.shortName }/boat/${boatName}/images" class="thumbnail">
		            <img src="${boat.thumbnailUrl }" style="width:180px">
		            <div class="caption">
		              <h3>${boat.name }</h3>
		            </div>
		          </a>
		        </div>
			</c:if>
		</c:forEach>
    </div>
</div>
