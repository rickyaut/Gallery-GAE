<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<c:set var="carBrands" value="<%=com.rickyaut.gallery.CarBrand.values()%>" scope="request"/>
<c:set var="truckBrands" value="<%=com.rickyaut.gallery.TruckBrand.values()%>" scope="request"/>
<c:set var="boatBrands" value="<%=com.rickyaut.gallery.BoatBrand.values()%>" scope="request"/>
<c:set var="requestURI" scope="request">${requestScope['javax.servlet.forward.request_uri']}</c:set>
<c:set var="serverDomain" scope="request">allmotorsgallery.appspot.com</c:set>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>${meta_title }</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="${meta_description }">
	<meta property="og:title" content="${meta_title }"/>
	<meta property="og:url" content="http://${serverDomain }${requestURI }"/>
	<meta property="og:site_name" content="All Motors Gallery"/>
	<c:if test='${not empty ogImage }'><meta property="og:image" content="${ogImage }"/></c:if>
	<link rel="stylesheet" href="<c:url value="/css/bootstrap/bootstrap.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/css/bootstrap/simple-sidebar.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/css/bootstrap/bootstrap-theme.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/css/jquery-ui/jquery-ui.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/css/application.css" />" type="text/css" media="screen, projection" />

    <script type="text/javascript" src="<c:url value="/js/jquery-2.0.3.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/jquery-ui/jquery-ui.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/jquery-form/jquery.form.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/jquery-validate/jquery.validate.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/bootstrap/bootstrap.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/gallery.js" />"></script>
<%@include file="../includes/google.analytics.jsp" %>
</head>
<body>
<%@include file="../includes/facebook.sdk.jsp" %>
	<div class="container"><div class="page-header"><img src="<c:url value="/images/banner.png" />"/></div></div>
<div class="container">
	<div class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            	<span class="sr-only">Toggle navigation</span>
            	<span class="icon-bar"></span>
            	<span class="icon-bar"></span>
            	<span class="icon-bar"></span>
          	</button>
          	<a class="navbar-brand ${requestURI eq '/home' ? 'active':''}" href="/">Home</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
	            <li class="dropdown ${fn:contains(requestURI, '/car') ? 'active':''}">
		            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cars <b class="caret"></b></a>
		            <ul class="dropdown-menu">
		            	<c:forEach items="${carBrands }" var="brand">
			            <li><a href="/brand/${brand.shortName }/cars"><spring:message code="${brand.code }"/></a></li>
		            	</c:forEach>
		            </ul>
	            </li>
	            <li class="dropdown ${fn:contains(requestURI, '/truck') ? 'active':''}">
		            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Truck <b class="caret"></b></a>
		            <ul class="dropdown-menu">
		            	<c:forEach items="${truckBrands }" var="brand">
			            <li><a href="/brand/${brand.shortName }/trucks"><spring:message code="${brand.code }"/></a></li>
		            	</c:forEach>
		            </ul>
	            </li>
	            <li class="dropdown ${fn:contains(requestURI, '/boat') ? 'active':''}">
		            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Boat <b class="caret"></b></a>
		            <ul class="dropdown-menu">
		            	<c:forEach items="${boatBrands }" var="brand">
			            <li><a href="/brand/${brand.shortName }/boats"><spring:message code="${brand.code }"/></a></li>
		            	</c:forEach>
		            </ul>
	            </li>
	            <li class="${requestURI eq '/contact-us' ? 'active':''}"><a href="/contact-us"><spring:message code="contact.us"/></a></li>
	        </ul>
	        <%--
	        <ul class="nav navbar-nav navbar-right">
	            <li class="active"><a href="./">Default</a></li>
	            <li><a href="../navbar-static-top/">Static top</a></li>
	            <li><a href="../navbar-fixed-top/">Fixed top</a></li>
          	</ul>
          	 --%>
		</div>
	</div>
	<div id="content">
		<div id="main" class="span-18 last">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<hr />
	<div id="footer">
		<div class="container">You are accessing from <%=request.getHeader("X-AppEngine-City") %>, <%=request.getHeader("X-AppEngine-Region") %>, <%=request.getHeader("X-AppEngine-Country") %></div>
	</div>
</div>
</body>
</html>