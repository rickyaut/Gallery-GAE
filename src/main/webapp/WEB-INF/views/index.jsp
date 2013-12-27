<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div>
	<h1>Motor brands</h1>
	<h2>Cars</h2>
	<ul>
       	<c:forEach items="${carBrands }" var="brand">
        <li><a href="/brand/${brand.shortName }/cars"><spring:message code="${brand.code }"/></a></li>
       	</c:forEach>
	</ul>
	<hr/>
	<h2>Trucks</h2>
	<ul>
       	<c:forEach items="${truckBrands }" var="brand">
        <li><a href="/brand/${brand.shortName }/trucks"><spring:message code="${brand.code }"/></a></li>
       	</c:forEach>
	</ul>
	<hr/>
	<h2>Boats</h2>
	<ul>
       	<c:forEach items="${boatBrands }" var="brand">
        <li><a href="/brand/${brand.shortName }/boats"><spring:message code="${brand.code }"/></a></li>
       	</c:forEach>
	</ul>
	<hr/>
</div>
