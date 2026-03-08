/* savemeal.jspに適用
 *	クリックされて goToSearch() が呼び出される
 * goToSearch() : ID_MEALがNULLなら警告
 * 				  ！NULLならaction = search, MEAL, WEIGHT をパラメーターに設定してSaveMealSurveyへdoGet
 * 食事登録ボタンクリック時に体重、食事名チェック 
 */

function goToSearch() {
	let mealName = document.getElementById("ID_MEAL").value;
    let weight = document.getElementById("ID_WEIGHT").value; 
    const mealInput = document.getElementById("ID_MEAL");
    const keyword = mealInput.value.trim();

    if (keyword === "") {
        alert("検索するキーワード（今日のメニュー）を入力してください");
        return;
    }
    location.href = "SaveMealSurvey?action=search&MEAL=" + encodeURIComponent(mealName) + "&weight=" + weight;
}

document.getElementById("ID_SUBMIT").addEventListener("click", function(event) {
    var elmweight = document.getElementById("ID_WEIGHT");
    var elmmeal = document.getElementById("ID_MEAL");
    var valweight = elmweight.value;
    var valmeal = elmmeal.value;
    
    if (valweight === "" || isNaN(valweight) || parseFloat(valweight) <= 0) {
        alert("体重は0より大きい半角数字で正しく入力してください。");
        event.preventDefault(); 
        return; 
    }

    if (valmeal.trim() === "") {
        alert("本日の食事が未入力です。");
        event.preventDefault(); 
        return;
    }
});