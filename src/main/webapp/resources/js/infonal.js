
$(function() {
	User.init();
});

var User = {
	consts : {
		layoutIdentity : "user",
	},

	init : function() {

		this.handleUser.init();

	},

								handleUser : {
									
									handleUserCreate : function() {
										$(document).on('click', '.btn-save', function() {
											var userName=$("#name-input").val();
											var surName=$("#surname-input").val();
											var phoneNumber=$("#telNumber-input").val();
											
											if (userName == "" || surName == ""){
												alert("Please fill all requered fields.")									
											}else{
												$('.load').show(); 
											   $.ajax({
										            url : "/infonal/person/save",
										            type:"post",
										            data: $('.form-save-person').serialize(),
										            
										            success : function(data) {    
										            		location.reload();							            			
										            			$('.load').hide(1000); 	            	
										            }
										        });
											}
										});
									},
									
									handleUserDelete : function() {
										$(document).on('click', '.btn-delete-trigger', function() {
											var $this = $(this)
											var _userId=$this.attr("data-id");
											$('.load').show(); 
											   $.ajax({
										            url : "/infonal/person/delete/"+_userId,
										            type:"delete",
										           data: { id: _userId},

										            
										            success : function(data) {
										            
										            		$("#myModal").modal("hide"); 
										            		$('.load').hide(); 
										            		$(".person-table-list tr[data-id="+_userId+"]").hide("fast");
										           
										            	
										            },
										            error:function(data){
										            	alert(data);
										            }
										        });
										});
									},
									handleUserUpdate : function() {
										$(document).on('click', '.btn-update-trigger', function() {
											$('.load').show(); 
											   $.ajax({
										            url : "/infonal/person/update",
										            type:"post",
										            data: $('.form-update-person').serialize(),
										            
										            success : function(data) {    
										            
									            		 location.reload();
									            
										            }
										        });
											  
										});
									},
									handleUserUpdateModal : function() {
										$(document).on(
												'click',
												'.btn-update-modal',
												function() {
													var $this = $(this);
													var _id= $this.attr("data-id");
													var _name = $this.attr("data-name");
													var _surname = $this.attr("data-surname");
													var _telNumber = $this.attr("data-telNumber");
													document.getElementById('new-id-input').value = _id;
													document.getElementById('new-name-input').value = _name;
													document.getElementById('new-surname-input').value = _surname;
													document.getElementById('new-telNumber-input').value = _telNumber;
													
												});
									},
									handleUserModal : function() {
										$(document).on(
												'click',
												'.btn-delete-modal',
												function() {
													var $this = $(this);
													var _name = $this.attr("data-name");
													var _surname = $this.attr("data-surname");
													$("#myModal .modal-clone-content") 
															.html(
																	"<span>" + _name + " " + _surname 
																			+ "</span>");
													$(".btn-delete-trigger").attr("data-id", 
															$this.attr("data-id"))
													$("#myModal").modal("show"); 
							
												});
									},
									
									inputMask : function() {
										$(document).on(
												'click',
												'#telNumber-input',
												function() {
											$("#telNumber-input").mask("9(999)-999-99-99");
											
												});
									},
					
							
									init : function() {
										this.handleUserDelete();
										this.handleUserModal();
										this.handleUserCreate();
										this.handleUserUpdate();
										this.handleUserUpdateModal();
										this.inputMask();
									}
							
								}
}
