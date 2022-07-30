<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Home</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="resources/_system/js/jquery-3.6.0.min.js"></script>
        <script type="text/javascript" src="resources/_system/js/common.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/_system/css/jquery.dataTables.min.css"/>
        <script type="text/javascript" src="resources/_system/js/jquery.dataTables.min.js"></script>        
        <link rel="stylesheet" type="text/css" href="resources/_bootstrap/css/bootstrap.min.css">
        <script type="text/javascript" src="resources/_bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/_customize/css/layout.css">
        <script type="text/javascript" src="resources/_customize/js/layout.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/_customize/css/tbl-datatable.css"/>
        <script type="text/javascript" src="resources/_customize/js/tbl-user-datatable.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/_customize/css/user-screen.css"/>
	</head>
	<body>
		<nav class="navbar navbar-light bg-light">
			<a class="navbar-brand" href="#">User Management</a>
			<form class="form-inline">
				<input class="form-control mr-sm-2" type="text" id="key-search" placeholder="Search" aria-label="Search"/>
				<button class="btn btn-outline-success my-2 my-sm-0" type="button">Search</button>
				<button class="btn btn-outline-success my-2 my-sm-0" type="button" data-bs-toggle="modal" data-bs-target="#user-update-modal" data-mode-button="Create New">Add</button>
			</form>
		</nav>
		<div id="msg-container"></div>
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
		<div class="modal fade" id="user-update-modal" tabindex="-1" aria-labelledby="user-update-modal-label" aria-hidden="true">
		  <div class="modal-dialog modal-size-medium">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="user-update-modal-label">@@Mode User Dialog</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        <form id="user-form-update">
		        	<input type="hidden" name="id" id="user-id" value="" />
		            <div class="row mb-3">
					  <label for="first-name" class="col-sm-2 col-form-label col-form-label-sm">First Name</label>
					  <div class="col-sm-10">
					    <input type="text" class="form-control" id="first-name" name="firstName">
					  </div>
					</div>
		            <div class="row mb-3">
					  <label for="last-name" class="col-sm-2 col-form-label col-form-label-sm">Last Name</label>
					  <div class="col-sm-10">
					    <input type="text" class="form-control" id="last-name" name="lastName">
					  </div>
					</div>
		            <div class="row mb-3">
					  <label for="birthday" class="col-sm-2 col-form-label col-form-label-sm">DOB</label>
					  <div class="col-sm-10">
					    <input type="text" class="form-control" id="birthday" name="birthday">
					  </div>
					</div>
		            <div class="row mb-3">
					  <label for="email" class="col-sm-2 col-form-label col-form-label-sm">Login</label>
					  <div class="col-sm-10">
					    <input type="text" class="form-control" id="email" name="email">
					  </div>
					</div>
		            <div class="row mb-3">
					  <label for="phone" class="col-sm-2 col-form-label col-form-label-sm">Mobile No</label>
					  <div class="col-sm-10">
					    <input type="text" class="form-control" id="phone" name="phone">
					  </div>
					</div>
		            <div class="row mb-3">
					  <label for="gender" class="col-sm-2 col-form-label col-form-label-sm">Gender</label>
					  <div class="col-sm-10">
					    <select class="form-select form-select-sm mb-3" id="gender" name="gender" aria-label=".form-select-sm">
						  <option value="true">Nam</option>
						  <option value="false">Nu</option>
						</select>
					  </div>
					</div>
		        </form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-primary modal-btn-execute">@@Mode</button>
		        <button type="button" class="btn btn-light modal-btn-reset">Reset</button>
		      </div>
		    </div>
		  </div>
		</div>
	</body>
</html>