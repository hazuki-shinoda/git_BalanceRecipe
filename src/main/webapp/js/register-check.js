/* register.jsp に適用
 * function validateNumber(el, label), function isBlank(el)で各パラメーターのバリデーションチェック
 */
document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector("form");
    
    form.addEventListener("submit", function(event) {
        let canSubmit = true;
        let errorMessages = [];

        const id = document.getElementById("ID_id");
        const pw = document.getElementById("ID_password");
        const name = document.getElementById("ID_name");
        const birthday = document.getElementById("ID_birthday");
        const height = document.getElementById("ID_height");
        const weight = document.getElementById("ID_weight");

		function isBlank(el) {
		    if (!el) {
		        return true;
		    }
		    let value = el.value.trim();
		    if (value === "") {
		        return true;
		    }
		    return false;
		}

        if (isBlank(id)) {
            errorMessages.push("IDを入力してください。");
            canSubmit = false;
        }
        if (isBlank(pw)) {
            errorMessages.push("パスワードを入力してください。");
            canSubmit = false;
        }
        if (isBlank(name)) {
            errorMessages.push("氏名を入力してください。");
            canSubmit = false;
        }
        if (isBlank(birthday)) {
            errorMessages.push("誕生日を選択してください。");
            canSubmit = false;
        }
        
        function validateNumber(el, label) {
		    if (isBlank(el)) {
	        errorMessages.push(label + "を入力してください。");
	        canSubmit = false;
	        return; 
	    	}
		    let num = parseFloat(el.value);
		    if (num <= 0) {
		        errorMessages.push(label + "には0より大きい数値を入力してください。");
		        canSubmit = false;
		    }
		}
        validateNumber(height, "身長");
        validateNumber(weight, "現在の体重");

        if (!canSubmit) {
            alert(errorMessages.join("\n"));
            event.preventDefault();
        }
    });
});