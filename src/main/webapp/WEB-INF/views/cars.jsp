<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div>
	<ol class="breadcrumb">
	  <li><spring:message code="cars"/></li>
	  <li><spring:message code="${selectedBrand.code }"/></li>
	</ol>
	
	<div class="row">
		<c:forEach items="${cars }" var="car">
			<c:if test="${not empty car.name }">
		        <div class="col-sm-6 col-md-3">
		          <a href="/brand/${selectedBrand.shortName }/car/${fn:replace(fn:toLowerCase(car.name), ' ', '-')}/images" class="thumbnail">
		            <img src="${car.thumbnailUrl }" style="width:180px">
		            <div class="caption">
		              <h3>${car.name }</h3>
		            </div>
		          </a>
		        </div>
			</c:if>
		</c:forEach>
    </div>
</div>
