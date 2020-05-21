/**
 * 
 */

function loginConfirm() {
	var userId = document.frm_login.userId.value;
	var userPw = document.frm_login.userPw.value;
	
	//var blank_pattern = /^\s+|\s+$/g;(/\s/g
	var blank_pattern = /[\s]/g;
	
	if(userId.search(blank_pattern) != -1){
	    alert(' 공백은 사용할 수 없습니다. ');
	    frm_login.userId.focus();
	    return;
	}
	
	
	if (userId.length == 0 ) {
		alert("아이디를 입력하세요");
		frm_login.userId.focus();
		return;
	}
	if (userPw.length == 0) {
		alert("비밀번호를 입력하세요");
		frm_login.userPw.focus();
		return;
	}

	
	document.frm_login.submit();
}