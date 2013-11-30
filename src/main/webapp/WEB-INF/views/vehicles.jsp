<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div>
	<h1><spring:message code="vehicles"/> - <spring:message code="${selectedBrand.code }"/></h1>
	
	<div class="row">
		<c:forEach items="${vehicles }" var="vehicle">
        <div class="col-sm-6 col-md-3">
          <a href="/brand/${selectedBrand.shortName }/vehicle/${fn:replace(fn:toLowerCase(vehicle.name), ' ', '-')}/images" class="thumbnail">
            <img src="${vehicle.thumbnailUrl }" style="width:180px">
            <div class="caption">
              <h3>${vehicle.name }</h3>
            </div>
          </a>
        </div>
		</c:forEach>
    </div>
</div>
