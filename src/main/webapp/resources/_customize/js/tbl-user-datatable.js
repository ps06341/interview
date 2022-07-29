$(function() {
	var callApiApplicationJson = function(url, method, dataJson, successCallback, errorCallback) {
		$.ajax({
			url: url,
			method: method,
			data: dataJson,
			success: successCallback,
			error: errorCallback
		});
	}

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
				html += '<span class="badge rounded-pill bg-success">' + datas[k].gender + '</span>';
				html += '</td><td>';
				html += '<button type="button" class="btn btn-sm btn-success">Update</button>';
				html += '<button type="button" class="btn btn-sm btn-danger" style="margin-left: 5px;">Delete</button>';
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
		var keySearch = '';
		if (typeof $('#key-search').val() != 'undefined' && $('#key-search').val() != '') {
			keySearch = $('#key-search').val();
		}

		var data = {
			'keySearch': keySearch
		}

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
					'gender': 'Nam'
				},
				{
					'id': '2',
					'firstName': 'ngo van',
					'lastName': 'B',
					'birthday': '11/07/2021',
					'email': 'Bnv@gmail.com',
					'phone': '121212',
					'gender': 'Nu'
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

	loadUser();
});