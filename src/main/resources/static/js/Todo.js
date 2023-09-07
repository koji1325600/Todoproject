window.onload = function () {
    var checkElements = document.getElementsByClassName('ECM_CheckboxInput-Input');
    for (var i = 0; i < checkElements.length; i++) {
        if (checkElements[i].value) {
            checkElements[i].checked = true;
        }
    }
    var dateSortId = document.getElementById('dateSortId').value;
    if (dateSortId == 0) {
        document.getElementById('dateSortButton').textContent = '↓';
    } else {
        document.getElementById('dateSortButton').textContent = '↑';
    }
}



 function post (params) {
    const form = document.createElement('form');
    form.method = 'post';
    form.action = '/todo/check';

    const hiddenField = document.createElement('input');
    hiddenField.type = 'hidden';
    hiddenField.name = 'id';
    hiddenField.value = params;

    form.appendChild(hiddenField);

    document.body.appendChild(form);
    form.submit();
}