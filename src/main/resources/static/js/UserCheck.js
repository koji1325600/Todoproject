function nameCheck(userName) {
    // 入力値取得
    var name = userName.value;
    // エラーチェック
    if (name.length >= 50) {
        userName.setCustomValidity("名前は50文字以内で入力してください");
    } else {
        userName.setCustomValidity('');
    }
}

function passwordCheck(password) {
    // 入力値取得
    var pass = password.value;
    // エラーチェック
    if (pass.length >= 100) {
        password.setCustomValidity("パスワードは100桁以内で入力してください");
    } else if (pass.length < 8) {
        password.setCustomValidity('パスワードは8桁以上で入力してください');
    } else {
        password.setCustomValidity('');
    }
}