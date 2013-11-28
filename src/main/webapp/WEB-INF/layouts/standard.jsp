<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Spring Travel: Spring MVC and Web Flow Reference Application</title>
	<link rel="stylesheet" href="<c:url value="/css/bootstrap/bootstrap.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/css/bootstrap/bootstrap-theme.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/css/jquery-ui/jquery-ui.css" />" type="text/css" media="screen, projection" />

    <script type="text/javascript" src="<c:url value="/js/jquery-2.0.3.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/jquery-ui/jquery-ui.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/bootstrap/bootstrap.min.js" />"></script>
</head>
<body>
<div class="container">
	<div class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            	<span class="sr-only">Toggle navigation</span>
            	<span class="icon-bar"></span>
            	<span class="icon-bar"></span>
            	<span class="icon-bar"></span>
          	</button>
          	<a class="navbar-brand" href="/">Home</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
	            <li class="active"><a href="#">Link</a></li>
	            <li><a href="#">Link</a></li>
	            <li><a href="#">Link</a></li>
	            <li class="dropdown">
		              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Vehicles <b class="caret"></b></a>
		              <ul class="dropdown-menu">
			                <li><a href="/audi/vehicles">Audi</a></li>
			                <li><a href="/benz/vehicles">Mercedes Benz</a></li>
			                <li><a href="/bmw/vehicles">BMW</a></li>
			                <li><a href="/ford/vehicles">Ford</a></li>
			                <li><a href="/gm/vehicles">GM</a></li>
			                <li><a href="/honda/vehicles">Honda</a></li>
			                <li><a href="/jaguar/vehicles">Jaguar</a></li>
			                <li><a href="/lamborghini/vehicles">Lamborghini</a></li>
			                <li><a href="/Lexus/vehicles">Lexus</a></li>
			                <li><a href="/maserati/vehicles">Maserati</a></li>
			                <li><a href="/mazda/vehicles">Mazda</a></li>
			                <li><a href="/porsche/vehicles">Porsche</a></li>
			                <li><a href="/toyota/vehicles">Toyota</a></li>
		              </ul>
	            </li>
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
		<div id="local" class="span-6">
			<p>
				<a href="http://www.thespringexperience.com">
					<img src="<c:url value="/resources/images/diplomat.jpg"/>" alt="generic hotel" />
				</a>
			</p>
			<p>
				<a href="http://www.thespringexperience.com">
					<img src="<c:url value="/resources/images/springone2gx.jpeg"/>" alt="SpringOne 2GX" />
				</a>
			</p>
		</div>
		<div id="main" class="span-18 last">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<hr />
	<div id="footer">
		<div class="container"></div>
	</div>
</div>
</body>
</html>