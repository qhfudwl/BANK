console.log("record account.js in");
//계좌 지우기 물음 1차
/*
function acctDelfunc() {
	console.log("account delete function in");

	let accNum = $('.accNum').text();
	let balance = $('.allBalance').text();
	let accMsg = "[계좌번호] " + accNum + " [잔액] " + balance + "\n";
	let msg = accMsg + "계좌 삭제 전, 잔액을 확인해주세요.";
	let result = confirm(msg);
	if (result) {
		//확인을 누르면

	}
	else {
		return false;
	}

}
//계좌 지우기 물음 2차
function accDelfunc2() {
	console.log("account delete function2 in");

	let msg = "정말로 계좌를 지우시겠습니까? ";
	let result = confirm(msg);
	if (result) {
		let documents = document.accDelPopup;
		documents.submit();
	}
	else {
		return false;
	}

}
*/

function acctDelfunc() {
	let accNum = $('.accNum').text();
	let balance = $('.allBalance').text();
	let accEndMsg = "[계좌번호] " + accNum;
	let accMsg = "[계좌번호] " + accNum + " [잔액] " + balance + "";
	//let msg = accMsg + "계좌 삭제 전, 잔액을 확인해주세요.";
	Swal.fire({
		title: '계좌를 삭제하시겠습니까?',
		text: accMsg,
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#7AB53F',
		confirmButtonText: '삭제',
		cancelButtonText:'취소',
		reverseButtons:'true'
	}).then((result) => {
		if (result.isConfirmed) {
			Swal.fire({
				title:'삭제 완료',
				text:accEndMsg,
				icon:'success',
				confirmButtonColor: '#7AB53F',
				confirmButtonText: '확인'
			}).then((result) => {
				if(result.isConfirmed){
					let documents = document.accDelPopup;
					documents.submit();
				}
			})
			
		}
	})
}

