$(function() {
	var renderTable = function(datas) {
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
				html += '<button type="button" class="btn btn-sm btn-danger">Delete</button>';
				html += '</td></tr>';
			});
		}

		$('#user-tbl-tbody').html(html);
		$('#user-tbl').DataTable({
			'columns': [
				{ 'width': '28%' },
				{ 'width': '15%' },
				{ 'width': '20%' },
				{ 'width': '15%' },
				{ 'width': '10%' },
				{ 'width': '12%' }
			]
		});
	}

	var loadUser = function() {
		//var keySearch = '';
		//if (typeof $('#key-search').val() != 'undefined' && $('#key-search').val() != '') {
		//	keySearch = $('#key-search').val();
		//}

		//var data = {
		//	'keySearch': keySearch
		//}

		//callApiApplicationJson('/search', 'get', data, loadUserSuccess, undefined);
		var result = {
			'statusCode': 200,
			'data': [
				{
					'id': '1',
					'firstName': 'nguyen van',
					'lastName': 'A',
					'birthday': '21/07/2020',
					'email': 'Anv@gmail.com',
					'phone': '090909',
					'gender': true
				},
				{
					'id': '2',
					'firstName': 'ngo thi',
					'lastName': 'B',
					'birthday': '11/07/2021',
					'email': 'Bnv@gmail.com',
					'phone': '121212',
					'gender': false
				}
			]
		}
		loadUserSuccess(result);
	}

	var loadUserSuccess = function(result) {
		if (result.statusCode == 200) {
			renderTable = renderTable(result.data);
		}
	}

	var loadEvent = function() {
		bindingModalEvent('#user-update-modal'
			, function(event) {
				cleanForm('#user-update-modal');
				var button = event.relatedTarget;
				var recipient = button.getAttribute('data-mode-button');
				var btnExeVal = $('.modal-btn-execute').html();
				$('.modal-btn-execute').html(btnExeVal.replace('@@Mode', recipient));
				
				var userData = button.getAttribute('data-user-id');
				if (typeof userData != 'undefined') {
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
			,'.modal-btn-execute'
			, function(event, modalId){
				
			}
			, '.modal-btn-reset'
			, function(event){
				
			}
		);
	}

	loadUser();
	loadEvent();
});