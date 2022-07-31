$(function() {
	let dataTable = undefined;
	var renderTable = function(datas) {
		if(typeof dataTable != 'undefined'){
			dataTable.destroy();
		}
		var html = '';
		if (datas.length > 0) {
			$(datas).each(function(k, v) {
				html += '<tr data-user-id="';
				html += datas[k].id;
				html += '"><td class="name" >';
				html += datas[k].firstName + ' ' + datas[k].lastName;
				html += '</td><td class="dob">';
				html += datas[k].birthday;
				html += '</td><td class="mail">';
				html += datas[k].email;
				html += '</td><td class="phone">';
				html += datas[k].phone;
				html += '</td><td class="gender" style="vertical-align: middle;">';
				var genderStr = datas[k].gender ? 'Nam' : 'Nu';
				html += '<span class="badge rounded-pill bg-success">' + genderStr + '</span>';
				html += '</td><td class="row-control">';
				html += '<button type="button" class="btn btn-sm btn-success" data-mode-button="Update" data-bs-toggle="modal" data-bs-target="#user-update-modal"';
				html += ' data-user-id="';
				html += datas[k].id;
				html += '" data-firstName="';
				html += datas[k].firstName;
				html += '" data-lastName="';
				html += datas[k].lastName;
				html += '" data-birthday="';
				html += datas[k].birthday;
				html += '" data-email="';
				html += datas[k].email;
				html += '" data-phone="';
				html += datas[k].phone;
				html += '" data-gender="';
				html += datas[k].gender;
				html += '">Update</button>';
				html += '<button type="button" class="btn btn-sm btn-danger btn-del-user" data-user-id="'+ datas[k].id +'">Delete</button>';
				html += '</td></tr>';
			});
		}

		$('#user-tbl-tbody').html(html);
		dataTable = $('#user-tbl').DataTable({
			'columns': [
				{ 'width': '28%' },
				{ 'width': '15%' },
				{ 'width': '20%' },
				{ 'width': '15%' },
				{ 'width': '10%' },
				{ 'width': '12%' }
			],
			dom: 'Plfrtip',
			language: {
				searchPanes: {
					emptyPanes: 'There are no panes to display. :/'
				}
			}
		});
	}

	var loadUser = function() {
		var keySearch = '';
		if (typeof $('#key-search').val() != 'undefined' && $('#key-search').val() != '') {
			keySearch = $('#key-search').val();
		}

		var data = {
			'keySearch': keySearch
		}

		callApiApplicationJson(window.location.href + 'user/load', 'get', data, loadUserSuccess, undefined);
	}

	var loadUserSuccess = function(result) {
		renderTable(result);
	}

	var loadEvent = function() {
		$('.btn-search-user').on('click', function(e) {
			loadUser();
		});

		$(document).on('click', '.btn-del-user', function(e) {
			var result = confirm("Do u want to delete?");
			if (result) {
				var data = {
					"id": $(this).attr('data-user-id')
				}
				callApiApplicationJson(window.location.href + 'user/del', 'post', JSON.stringify(data), function(result){
					showMessage(MSG_TYPE_SUCCESS, "Delete Successfully!!!");
					loadUser();
				}, undefined);
			}
		});

		bindingModalEvent('#user-update-modal'
			, function(event) {
				cleanForm('#user-update-modal');
				var button = event.relatedTarget;
				var recipient = button.getAttribute('data-mode-button');
				var btnExeVal = $('.modal-btn-execute').html();
				$('.modal-btn-execute').html(btnExeVal.replace('@@Mode', recipient));

				var userData = button.getAttribute('data-user-id');
				if (typeof userData != 'undefined' && userData != null) {
					$('.modal-btn-reset').css('display', 'none');
					$('#user-id').val(button.getAttribute('data-user-id'));
					$('#first-name').val(button.getAttribute('data-firstName'));
					$('#last-name').val(button.getAttribute('data-lastName'));
					$('#birthday').val(button.getAttribute('data-birthday'));
					$('#email').val(button.getAttribute('data-email'));
					$('#phone').val(button.getAttribute('data-phone'));
					$('#gender').val(button.getAttribute('data-gender'));

				}
			}
			, function(event) {
			});

		bindingModalBtnEvent(
			'#user-update-modal'
			, '.modal-btn-execute'
			, function(event, modalId) {
				$('#msg-container').html('');
				var data = {
					"firstName": $('#first-name').val(),
					"lastName": $('#last-name').val(),
					"birthday": $('#birthday').val(),
					"email": $('#email').val(),
					"phone": $('#phone').val(),
					"gender": $('#gender').val()
				}
				var mode = 'Save';
				if($('#user-id').val() != ''){
					mode = "Update";
					data['id'] = $('#user-id').val();
				}
				callApiApplicationJson(window.location.href + 'user/update', 'post', JSON.stringify(data), function(result){
					showMessage(MSG_TYPE_SUCCESS, mode + " Successfully!!!");
					loadUser();
					$('#user-update-modal').modal('hide')
				}, undefined);
			}
			, '.modal-btn-reset'
			, function(event) {

			}
		);
	}

	loadUser();
	loadEvent();
});