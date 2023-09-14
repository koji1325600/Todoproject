function dateCheck(date) {
    // 入力値取得
    var dateVal = date.value;
    // エラーチェック
    if (dateVal < dateToStr24HPad0(new Date(), 'YYYY-MM-DD')) {
        date.setCustomValidity("現在の日付以降で入力してください");
    } else {
        date.setCustomValidity('');
    }
}

function titleCheck(title) {
    // 入力値取得
    var titleVal = title.value;
    // エラーチェック
    if (titleVal.length >= 80) {
        title.setCustomValidity("件名は80文字以内で入力してください");
    } else {
        title.setCustomValidity('');
    }
}

function bodyCheck(body) {
    // 入力値取得
    var bodyVal = body.value;
    // エラーチェック
    if (bodyVal.length >= 2000) {
        body.setCustomValidity("内容は2000文字以内で入力してください");
    } else {
        body.setCustomValidity('');
    }
}

function dateToStr24HPad0(date, format) {

    if (!format) {
        // デフォルト値
        format = 'YYYY-MM-DD hh:mm:ss'
    }

    // フォーマット文字列内のキーワードを日付に置換する
    format = format.replace(/YYYY/g, date.getFullYear());
    format = format.replace(/MM/g, ('0' + (date.getMonth() + 1)).slice(-2));
    format = format.replace(/DD/g, ('0' + date.getDate()).slice(-2));
    format = format.replace(/hh/g, ('0' + date.getHours()).slice(-2));
    format = format.replace(/mm/g, ('0' + date.getMinutes()).slice(-2));
    format = format.replace(/ss/g, ('0' + date.getSeconds()).slice(-2));

    return format;
}