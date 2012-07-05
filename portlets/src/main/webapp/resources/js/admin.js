$(document).ready(function(){
	button();
	bootstrapDropdown();
	bootstrapTooltip();
	bootstrapAlert();
	bootstrapModal();
	toggleFieldset();
	selectPermission();
	
	accessPermissionButton();
	accessPermissionTable();
	feedback();
});


// Do not move screen when clicking in a button
button = function(){
	$('button').click(function() {
		return false;
	});
};

// Enabling dropdown
bootstrapDropdown = function() {
	$('.dropdown-toggle').dropdown();
};

// Enabling tooltip
bootstrapTooltip = function() {
	$('[rel=tooltip]').tooltip({
		placement: 'bottom'
	});
};

// Enabling alert - NOT WORKING
bootstrapAlert = function() {
	$(".alert").alert();
};

// Modal
bootstrapModal = function() {
	$('a.edit-permission').click(function() {
		$('#modal-permission').modal({
			keyboard: true
		});
	});
};

// Hide / Show Fieldset 
toggleFieldset = function() {
	$('legend').click(function() {
		$(this).find('i').toggleClass('icon-closed');
		$(this).find('i').toggleClass('icon-opened');
		$(this).parent().find('.control-group').toggleClass('hidden-element');
	});
};

// Select permission in the list

selectPermission = function() {
	$('.select li span').click(function() {
		$(this).parent().parent().find('li').find('span').removeClass('selected');
		$(this).toggleClass('selected');
		$('#modal-permission .pull-right .select').addClass('enabled');
	});
	$('.select .second li span').click(function() {
		$(this).parent().parent().parent().parent().find('li').find('span').removeClass('selected');
		$(this).toggleClass('selected');
	});
	$('.select .third li span').click(function() {
		$(this).parent().parent().parent().parent().parent().parent().find('li').find('span').removeClass('selected');
		$(this).toggleClass('selected');
	});
	$('.select i').click(function() {
		$(this).toggleClass('icon-closed');
		$(this).toggleClass('icon-opened');
		$(this).next().next('ul').toggleClass('hidden-element');
	});
};


// CODE BELOW HERE ARE PROVISIONAL //


// Show and Hide button "Add Permission" in "Permission to Access"
accessPermissionButton = function() {
	$('#private').click(function() {
		$(this).parent().next('a').removeClass('hidden-element');
		$(this).parent().parent().find('.alert').removeClass('hidden-element');
	});
	$('#public').click(function() {
		$(this).parent().parent().find('a').addClass('hidden-element');
		$(this).parent().parent().find('table').addClass('hidden-element');
		$(this).parent().parent().find('.alert').addClass('hidden-element');
	});
};	
			
// Show table in "Permission to Access"

accessPermissionTable = function() {
	$('#access-permission a').click(function() {
		$(this).parent().find('table').removeClass('hidden-element');
		$(this).parent().find('.alert').addClass('hidden-element');
	});
};


// Show Feedback
feedback = function() {
	$('.form-actions .btn-primary').click(function() {
		$('.alert-container').removeClass('hidden-element');
	});
};


