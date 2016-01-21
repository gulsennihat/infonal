<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">	
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<script src='https://www.google.com/recaptcha/api.js'></script>

<script type="text/javascript" src="resources/js/infonal.js"></script>


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Infonal</title>

</head>
<body>

	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6">


			<h1 align="center">
				<span class="label label-default">Infonal JAVA Project</span> <br/>
			</h1>
<br/>
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target=".bs-example-modal-lg1">New Person</button>
			<br />
			<h3>Person List</h3>
			<table class="table table-striped person-table-list">
				<tbody>
				<tr>
						<td><b> Name</b> </td>
						<td><b> Surname</b> </td>
						<td><b> Telephone  Number</b> </td>
						
						</tr>
					<c:forEach var="person" items="${personList}">
						
						<tr data-id="${person.id}">
							<td>${person.name}</td>
							<td>${person.surname}</td>
							<td>${person.telNumber}</td>
							<td><button type="button" class="btn btn btn-warning btn-update-modal" 
							data-toggle="modal" data-target=".bs-example-modal-sm" data-name="${person.name}" 
							data-surname="${person.surname}" data-id="${person.id}" data-telNumber="${person.telNumber}">Update</button></td>
									
							<td><button type="button"
									class="btn btn-danger btn-delete-modal"
									data-name="${person.name}" data-surname="${person.surname}"
									data-id="${person.id}">Delete</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>



		</div>
		<div class="col-md-3"></div>
	</div>

	<!-- sm modal dialog for update record -->


<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="panel-heading">
						<h3 class="panel-title">Update Person</h3>
					</div><div class="user-update-validation" style="display:none;"></div>
      <form class="form-update-person">
							<input type="hidden" id="new-id-input" name="id">
							<table class="table">
								<tr>
									<td><label for="name">Name</label></td>
									<td><input type="text" id="new-name-input" name="name"
										placeholder="Name" /></td>
								</tr>
								<tr>
									<td><label for="name">Surname</label></td>
									<td><input type="text" id="new-surname-input" name="surname"
										placeholder="Surname" /></td>
								</tr>
								<tr>
									<td><label for="name">Phone Number</label></td>
									<td><input type="text" id="new-telNumber-input" name="telNumber"
										placeholder="Phone Number" /></td>
								</tr>

							</table>
							<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary btn-update-trigger"
						data-id="">Save Changes</button>
				</div>	
						</form>
      
    </div>
  </div>
</div>
	<!--  modal dialog for update user end -->


	<!-- modal dialog for new user record -->
	<div class="modal fade bs-example-modal-lg1 new-person-modal" tabindex="-1"
		role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">


				<!-- panel -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Save New Person</h3>
					</div>
					<div class="panel-body">
						<!-- panel body -->
						<div class="user-create-validation" style="display:none;"></div>
						<form class="form-save-person">
							<input type="hidden" name="id">
							<table class="table">
								<tr>
									<td><label for="name">Person Name</label></td>
									<td><input type="text" id="name-input" name="name"
										placeholder="Name" required="required"/></td>
								</tr>
								<tr>
									<td><label for="name">Person Surname</label></td>
									<td><input type="text" id="surname-input" name="surname"
										placeholder="Surname" /></td>
								</tr>
								<tr>
									<td><label for="name">Person Tel Number</label></td>
									<td><input type="text" id="telNumber-input" name="telNumber"
										placeholder="_ ( ___ ) - ___ - __ - __"/></td>
								</tr>
								<tr>
								<td><label>Security</label></td>
								<td><div class="g-recaptcha" data-sitekey="6LcZsxMTAAAAADRQ19x-JsZuooMuK-6LePvZsipt"></div></td>
								</tr>
								<tr>
									<td></td>
									<td><button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button> 
											
												<button type="button" class="btn btn-default btn-save">Save</button>
				</td>
								</tr>
							</table>
						</form>

						<!-- panel body end-->
					</div>
				</div>
				<!-- panel end -->

			</div>
		</div>
	</div>
	<!--  modal dialog for new user end -->

	<!-- confirm dialog for delete -->
	<!-- Button trigger modal -->


	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Are you sure ?</h4>
				</div>
				<div class="modal-body">
					<!-- body -->
					<p>Are you sure to delete this person?</p>
					<div class="modal-clone-content"></div>
					<!-- body end-->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
					<button type="button" class="btn btn-primary btn-delete-trigger"
						data-id="">Yes</button>
				</div>	
			</div>
		</div>
	</div>
	<!-- confirm dialog for delete end -->
	<div align="right"><img src="resources/aload.gif" class="load" style="display: none; width: 80px; height: 80px" /></div>
</body>
</html>