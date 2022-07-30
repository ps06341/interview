const MSG_TYPE_SUCCESS = 'success';
const MSG_TYPE_INFO = 'danger';
const MSG_TYPE_WARNING = 'warning';
const MSG_TYPE_DANGER = 'danger';
const MSG_CONTAINER_DEFAULT = 'msg-container';

var callApiApplicationJson = function(url, method, dataJson, successCallback, errorCallback) {
	if (typeof errorCallback == 'undefined') {
		errorCallback = loadUserError;
	}

	$.ajax({
		url: url,
		method: method,
		data: dataJson,
		success: successCallback,
		error: errorCallback
	});
}

var bindingModalEvent = function(modalId, showCallback, hideCallback) {
	var selectorVal = '.modal';
	if (typeof modalId != 'undefined' || modalId != '') {
		selectorVal = modalId;
		if (selectorVal[0] != '#') {
			selectorVal = '#' + selectorVal;
		}
	}

	$(selectorVal).on('show.bs.modal', function(event) {
		// Button that triggered the modal
		var button = event.relatedTarget;
		// Extract info from data-bs-* attributes
		var recipient = button.getAttribute('data-mode-button');
		// Update the modal's content.
		var modalTitle = this.querySelector('.modal-title');
		modalTitle.textContent = modalTitle.textContent.replace('@@Mode', recipient);

		//set model mode
		$(this).attr('modal-mode', recipient);
		if (typeof showCallback != 'undefined') {
			showCallback(event);
		}
	});

	$(selectorVal).on('hidden.bs.modal', function(event) {
		var modalTitle = this.querySelector('.modal-title');
		var modalTitleVal = $(modalTitle).html();
		modalTitleVal = modalTitleVal.replace(modalTitleVal, '@@Mode User Dialog');
		modalTitle.textContent = modalTitleVal;

		var btnExeVal = $('.modal-btn-execute').html();
		$('.modal-btn-execute').html(btnExeVal.replace(btnExeVal, '@@Mode'));
		$('.modal-btn-reset').css('display', 'block');

		if (typeof showCallback != 'undefined') {
			hideCallback(event);
		}
	});
}

var bindingModalBtnEvent = function(modalId, btnExecute, executeCallback, btnReset, resetCallback) {
	var modalSelectorId = '.modal';
	if (typeof modalId != 'undefined' || modalId != '') {
		modalSelectorId = modalId;
		if (modalSelectorId[0] != '#') {
			modalSelectorId = '#' + modalSelectorId;
		}
	}

	if (typeof btnExecute != 'undefined' || btnExecute != '') {
		btnExecute = '.modal-btn-execute';
	}
	if (btnExecute[0] != '#' && btnExecute[0] != '.') {
		btnExecute = '#' + btnExecute;
	}

	if (typeof btnReset != 'undefined' || btnReset != '') {
		btnReset = '.modal-btn-reset';
	}
	if (btnReset[0] != '#' && btnReset[0] != '.') {
		btnReset = '#' + btnReset;
	}

	$(btnExecute).on('click', function(event) {
		if (typeof executeCallback != 'undefined') {
			executeCallback(event, modalSelectorId);
		}
	});

	$(btnReset).on('click', function(event) {
		if (typeof resetCallback != 'undefined') {
			cleanForm(modalSelectorId);
			resetCallback(event);
		}
	});
}

var cleanForm = function(modalId) {
	if (typeof modalId != 'undefined' || modalId != '') {
		selectorVal = modalId;
		if (selectorVal[0] != '#') {
			selectorVal = '#' + selectorVal;
		}
	}

	$(selectorVal + ' input').each(function(k, v) {
		$(this).val('');
	});

	$(selectorVal + ' select').each(function(k, v) {
		$(this).val("true");
	});
}

var showMessage = function(msgType, msgBody, containerId) {
	if (typeof containerId == 'undefined' || containerId == '') {
		containerId = MSG_CONTAINER_DEFAULT;
	}
	containerId = containerId.replace('#', '');

	var wrapper = document.createElement('div')
	switch (msgType) {
		case MSG_TYPE_SUCCESS:
			// code block
			break;
		case MSG_TYPE_INFO:
			// code block
			break;
		case MSG_TYPE_WARNING:
			// code block
			break;
		case MSG_TYPE_DANGER:
			// code block
			break;
		default:
			msgType = MSG_TYPE_INFO;
	}
	wrapper.innerHTML = '<div class="alert alert-' + msgType + ' alert-dismissible" role="alert">' + msgBody + '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>'

	$('#' + containerId).append(wrapper)
}

var loadUserError = function(jqXHR, exception) {
	var error_msg = '';
	if (jqXHR.status === 0) {
		error_msg = 'Not connect.\n Verify Network.';
	} else if (jqXHR.status == 404) {
		// 404 page error
		error_msg = 'Requested page not found. [404]';
	} else if (jqXHR.status == 500) {
		// 500 Internal Server error
		error_msg = 'Internal Server Error [500].';
	} else if (exception === 'parsererror') {
		// Requested JSON parse
		error_msg = 'Requested JSON parse failed.';
	} else if (exception === 'timeout') {
		// Time out error
		error_msg = 'Time out error.';
	} else if (exception === 'abort') {
		// request aborte
		error_msg = 'Ajax request aborted.';
	} else {
		error_msg = 'Uncaught Error.\n' + jqXHR.responseText;
	}

	showMessage(MSG_TYPE_DANGER, error_msg);
}