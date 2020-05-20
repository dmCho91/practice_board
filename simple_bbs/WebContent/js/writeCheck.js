/**
 * 
 */

function writeConfirm() {
	
	if (document.frm_write.bTitle.value.length == 0) {
		alert("제목은 필수 사항입니다.");
		frm_write.bTitle.focus();
		return;
	}
	if (document.frm_write.bContent.value.length == 0) {
		alert("내용은 필수 사항입니다.");
		frm_write.bContent.focus();
		return;
	}
	
	document.frm_write.submit();
}