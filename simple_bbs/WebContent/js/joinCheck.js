/**
 * 
 */

function joinConfirm() {
	if (document.frm_join.userId.value.length == 0) {
		alert("아이디는 필수 사항입니다.");
		frm_join.userId.focus();
		return;
	}
	if (document.frm_join.userPw.value.length == 0) {
		alert("비밀번호는 필수 사항입니다.");
		frm_join.userPw.focus();
		return;
	}
	if (document.frm_join.userPw.value != document.frm_join.userPw_check.value) {
		alert("비밀번호가 일치하지 않습니다.");
		frm_join.userPw_check.focus();
		return;
	}
	if (document.frm_join.userName.value.length == 0) {
		alert("이름은 필수 사항입니다.");
		frm_join.userName.focus();
		return;
	}
	if (document.frm_join.userEmail.value.length == 0) {
		alert("이메일은 필수 사항입니다.");
		frm_join.userEmail.focus();
		return;
	}
	
	document.frm_join.submit();
}