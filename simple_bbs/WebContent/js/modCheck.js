/**
 * 
 */

function modConfirm() {
	if (document.frm_mod.userPw.value.length == 0) {
		alert("비밀번호는 필수 사항입니다.");
		frm_mod.userPw.focus();
		return;
	}
	if (document.frm_mod.userPw.value != document.frm_mod.userPw_check.value) {
		alert("비밀번호가 일치하지 않습니다.");
		frm_mod.userPw_check.focus();
		return;
	}
	if (document.frm_mod.userName.value.length == 0) {
		alert("이름은 필수 사항입니다.");
		frm_mod.userName.focus();
		return;
	}
	if (document.frm_mod.userEmail.value.length == 0) {
		alert("이메일은 필수 사항입니다.");
		frm_mod.userEmail.focus();
		return;
	}
	
	document.frm_mod.submit();
}