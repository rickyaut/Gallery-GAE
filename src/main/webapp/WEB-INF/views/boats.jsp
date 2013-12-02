<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div>
	<ol class="breadcrumb">
	  <li><spring:message code="boats"/></li>
	  <li><spring:message code="${selectedBrand.code }"/></li>
	</ol>
	
	<div class="row">
		<c:forEach items="${boats }" var="boat">
			<c:if test="${not empty boat.name }">
		        <div class="col-sm-6 col-md-3">
		          <a href="/brand/${selectedBrand.shortName }/boat/${fn:replace(fn:toLowerCase(boat.name), ' ', '-')}/images" class="thumbnail">
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
