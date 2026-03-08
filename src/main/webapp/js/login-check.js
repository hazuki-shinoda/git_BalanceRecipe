/*　
	login.jspに適用
	ID_ID, ID_PWが空でないかチェックする
*/
var elmSubmit = document.getElementById("ID_SUBMIT");
elmSubmit.onclick = function(){
  var elmUserId   = document.getElementById("ID_ID");
  var elmPassword = document.getElementById("ID_PW");
  var canSubmit = true;
  if(elmUserId.value == "" || elmPassword.value == ""){
    alert("入力漏れの項目があります。");
    canSubmit = false;
  }
  return canSubmit;
}
