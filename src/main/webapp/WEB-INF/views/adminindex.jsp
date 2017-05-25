
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<html>
<head>
<title></title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous"/>

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous"/>

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
<meta name="keywords" content="" />
<meta name="description" content="" />
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script>var myContextPath = "${pageContext.request.contextPath}"</script>
<script type="text/javascript" src="resources/js/adminIndex.js"></script>
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="resources/css/default.css" media="all" />
<link rel="stylesheet" type="text/css" href="resources/css/fonts.css" media="all" /></head>

<!--[if IE 6]><link href="default_ie6.css" rel="stylesheet" type="text/css" /><![endif]-->

<body>
<div id="page" class="container">
	<div id="header">
		<div id="logo">
			<!--img src="resources/images/pic02.jpg" alt="" /-->
			<h1><a href="#">Hi ${user.firstname}</a></h1>
		</div>
		<div id="menu">
			<ul>
				<li class="current_page_item"><a href="#" accesskey="1" title="" id="homepage">Homepage</a></li>
				<li><a href="#" accesskey="2" title="" id="manageUsers">Manage Users</a></li>
				<li><a href="#" accesskey="3" title="" id="myList">My List</a></li>
				<!--li><a href="#" accesskey="4" title="" id="videos">Videos</a></li>
				<li><a href="#" accesskey="5" title="" id="music">Music</a></li>
				<li><a href="#" accesskey="6" title="" id="images">Images</a></li-->
				<li><a href="#" accesskey="4" title="" id="mymessages">Messages</a></li>
				<li><a href="signout.htm" accesskey="5" title="" id="signout">Sign Out</a></li>
			</ul>
		</div>
	</div>
	<div id="main">
		<div id="banner">
			<!--img src="resources/images/pic01.jpg" alt="" class="image-full" /-->
			
		</div>
		<div id="welcome">
			<div class="title">
				<h2>Welcome to Read It Later!</h2>
				<span class="byline">Donec leo, vivamus fermentum nibh in augue praesent a lacus at urna congue</span>
			</div>
			<div id="insertfile">
			<form action="content.htm" method="post" enctype="multipart/form-data">
			<p>Select the file to upload</p>
			<input type="file" name="file"/><input type="submit" value="Upload"/>
			<input type="text" name="contentstatus"/>
			</form>
		</div>
		</div>
		<div id="featured">
			<div class="title">
				<h2>Maecenas lectus sapien</h2>
				<span class="byline">Integer sit amet aliquet pretium</span>
			</div>
			<ul class="style1">
				<li class="first">
					<p class="date"><a href="#">Jan<b>05</b></a></p>
					<h3>Amet sed volutpat mauris</h3>
					<p><a href="#">Consectetuer adipiscing elit. Nam pede erat, porta eu, lobortis eget, tempus et, tellus. Etiam neque. Vivamus consequat lorem at nisl. Nullam non wisi a sem semper eleifend. Etiam non felis. Donec ut ante.</a></p>
				</li>
				<li>
					<p class="date"><a href="#">Jan<b>03</b></a></p>
					<h3>Sagittis diam dolor amet</h3>
					<p><a href="#">Etiam non felis. Donec ut ante. In id eros. Suspendisse lacus turpis, cursus egestas at sem. Mauris quam enim, molestie. Donec leo, vivamus fermentum nibh in augue praesent congue rutrum.</a></p>
				</li>
				<li>
					<p class="date"><a href="#">Jan<b>01</b></a></p>
					<h3>Amet sed volutpat mauris</h3>
					<p><a href="#">Consectetuer adipiscing elit. Nam pede erat, porta eu, lobortis eget, tempus et, tellus. Etiam neque. Vivamus consequat lorem at nisl. Nullam non wisi a sem semper eleifend. Etiam non felis. Donec ut ante.</a></p>
				</li>
				<li>
					<p class="date"><a href="#">Dec<b>31</b></a></p>
					<h3>Sagittis diam dolor amet</h3>
					<p><a href="#">Etiam non felis. Donec ut ante. In id eros. Suspendisse lacus turpis, cursus egestas at sem. Mauris quam enim, molestie. Donec leo, vivamus fermentum nibh in augue praesent congue rutrum.</a></p>
				</li>
			</ul>
		</div>
		<div id="showdata" style="display:none;">
				<div class="form-group">
          <Select class="form-control form-group" name="users" size="1" id="users">
          <option value="default">Select email id to check User</option>
          <c:forEach items="${list}" var="st">
                <option value="${st.personID}"><c:out value="${st.emailId}"/></option>
          </c:forEach>
          </select>
				</div>
		</div>
		<div id="userdetails">
		<table class="table table-bordered" id="myTable"></table>
		
		</div>
		<div id="copyright">
			<span>&copy; Untitled. All rights reserved. | Photos by <a href="http://fotogrph.com/">Fotogrph</a></span>
			<span>Design by <a href="http://templated.co" rel="nofollow">TEMPLATED</a>.</span>
		</div>
	
</div>
</div>

</body>
</html>
