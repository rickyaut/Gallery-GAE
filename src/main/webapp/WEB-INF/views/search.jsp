<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div>
	<ol class="breadcrumb">
	  <li>Search Result</li>
	  <li>${param.q }</li>
	</ol>
	
	<div>
		<gcse:searchresults-only></gcse:searchresults-only>
    </div>
</div>
