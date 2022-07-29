<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Home</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="resources/_system/js/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/_system/css/jquery.dataTables.min.css"/>
        <script type="text/javascript" src="resources/_system/js/jquery.dataTables.min.js"></script>        
        <link rel="stylesheet" type="text/css" href="resources/_bootstrap/css/bootstrap.min.css">
        <script type="text/javascript" src="resources/_bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/_customize/css/layout.css">
        <script type="text/javascript" src="resources/_customize/js/layout.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/_customize/css/tbl-datatable.css"/>
        <script type="text/javascript" src="resources/_customize/js/tbl-user-datatable.js"></script>
	</head>
	<body>
		<nav class="navbar navbar-light bg-light">
			<a class="navbar-brand" href="#">User Management</a>
			<form class="form-inline">
				<input class="form-control mr-sm-2" type="search" id="key-search" placeholder="Search" aria-label="Search"/>
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</nav>
		<div class="navbar user-tbl-container">
			<table id="user-tbl" class="table table-striped" style="width:100%">
				<thead id="user-tbl-thead">
					<tr>
						<th>Name</th>
						<th>Date of Birthday</th>
						<th>Login</th>
						<th>Mobile No.</th>
						<th>Gender</th>
						<th></th>
					</tr>
				</thead>
				<tbody id="user-tbl-tbody">
				</tbody>
			</table>
		</div>
	</body>
</html>