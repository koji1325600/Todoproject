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

    const hiddenField1 = document.createElement('input');
    hiddenField1.type = 'hidden';
    hiddenField1.name = 'id';
    hiddenField1.value = params;

    const hiddenField2 = document.createElement('input');
    hiddenField2.type = 'hidden';
    hiddenField2.name = 'dateSortId';
    hiddenField2.value = document.getElementById('dateSortId').value;

    form.appendChild(hiddenField1);
    form.appendChild(hiddenField2);

    document.body.appendChild(form);
    form.submit();
}