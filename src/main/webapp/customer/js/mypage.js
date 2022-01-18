/**
휴대 전화 수정
*/
$(document).on("keyup", ".phone_InputValue", function() { $(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/([0-9]+)?([0-9]{4})$/,"$1-$2").replace("--", "-") ); });

$('.phoneFixBtn').on('click',function(e){
  e.preventDefault();
  if($('.mypage_phoneModiWrap').css('display')=='none'){
	//1번 수정을 끝낸 후, 다시 수정하려고 할 때 처음 수정완료했던 메시지 숨겨주기
	if($('.phoneOutput3').css('display')!='none'){
		$('.phoneOutput3').hide();
	}

    //숨겨진 수정과 관련된 div를 보이게 한다.
    $('.mypage_phoneModiWrap').show();
    //클릭한 버튼은 안보이게 만들어준다.
    $('.phoneFixBtn').hide();
  }
})
//수정취소를 누르면 다시 해당 td의 길이를 줄이고, 숨겼던 수정버튼을 보이게한다.
$('.phoneFixCancelBtn').on('click',function(e){
  e.preventDefault();
  if($('.phoneFixBtn').css('display')=='none'){
    //펼쳤던 div 접어주기
    $('.mypage_phoneModiWrap').hide();
    //수정버튼을 다시 보이게 한다.
    $('.phoneFixBtn').show();
    }
})
//수정완료
$('.phoneFixSuccessBtn').on('click',function(e){
	e.preventDefault();
	$.ajax({
	            url:'../customer/mypage/mypage.do',
	            type:'post',
	            data:{type:'phone',phone_InputValue:$('.phone_InputValue').val()},
				dataType: 'TEXT', 
	            success:function(data){
					//펼쳤던 div 접어주기
				    $('.mypage_phoneModiWrap').hide();
				    //수정버튼을 다시 보이게 한다.
				    $('.phoneFixBtn').show();
					//휴대전화번호 수정된 걸로 갱신해주기
					$('.phoneOutput1').text(data);
					//수정완료 메시지 띄어주기
					$('.phoneOutput3').show();	
					//입력한 수정된 번호로 클리어
					$('.phone_InputValue').val('');	
				}
        })
})
	

/**
비밀번호 수정
*/
function chkPW(){

 

}
$('.passwordFixBtn').on('click',function(e){
  e.preventDefault();
  if($('.mypage_passwordModiWrap').css('display')=='none'){
	
	//1번 수정을 끝낸 후, 다시 수정하려고 할 때 처음 수정완료했던 메시지 숨겨주기
	if($('.passwordOutput3').css('display')!='none'){
		$('.passwordOutput3').hide();
	}    

	//숨겨진 수정과 관련된 div를 보이게 한다.
    $('.mypage_passwordModiWrap').show();
    //클릭한 버튼은 안보이게 만들어준다.
    $('.passwordFixBtn').hide();
  }
  
  
})
//수정취소를 누르면  숨겼던 수정버튼을 보이게한다.
$('.passwordFixCancelBtn').on('click',function(e){
  e.preventDefault();
  if($('.passwordFixBtn').css('display')=='none'){
    //펼쳤던 div 접어주기
    $('.mypage_passwordModiWrap').hide();
    //수정버튼을 다시 보이게 한다.
    $('.passwordFixBtn').show();
  }
})

//수정완료
$('.passwordFixSuccessBtn').on('click',function(e){
	e.preventDefault();
	let pw1 = $('.password_InputValue2').val();
	let pw2 = $('.password_InputValue3').val();
	if(pw1==pw2){
	 let pw = pw1;
	 let num = pw.search(/[0-9]/g);
	 let eng = pw.search(/[a-z]/ig);
	 let spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
	
	 if(pw.length < 8 || pw.length > 20){
	
	  alert("8자리 ~ 20자리 이내로 입력해주세요.");
	  return false;
	 }else if(pw.search(/\s/) != -1){
	  alert("비밀번호는 공백 없이 입력해주세요.");
	  return false;
	 }else if(num < 0 || eng < 0 ){
	  alert("영문,숫자를 혼합하여 입력해주세요.");
	  return false;
	 }else {
		$.ajax({
	            url:'../customer/mypage/mypage.do',
	            type:'post',
	            data:{type:'password',
					  password_InputValue1:$('.password_InputValue1').val(),
					  password_InputValue2:$('.password_InputValue2').val(),
					  password_InputValue3:$('.password_InputValue3').val()
				},
				dataType: 'TEXT', 
	            success:function(data){
					//펼쳤던 div 접어주기
				    $('.mypage_passwordModiWrap').hide();
				    //수정버튼을 다시 보이게 한다.
				    $('.passwordFixBtn').show();
					//비밀번호 수정된 걸로 갱신해주기
					$('.passwordOutput3').text(data);
					//수정완료 메시지 띄어주기
					$('.passwordOutput3').show();	
					//입력한 수정된 번호칸 클리어
					$('.password_InputValue1').val('');	
					$('.password_InputValue2').val('');
					$('.password_InputValue3').val('');
				}
        })
	    return true;
	 }
	}
	 
	
})
/**
  이메일 수정
*/
$('.emailFixBtn').on('click',function(e){
  e.preventDefault();
  if($('.mypage_emailModiWrap').css('display')=='none'){

	//1번 수정을 끝낸 후, 다시 수정하려고 할 때 처음 수정완료했던 메시지 숨겨주기
	if($('.emailOutput3').css('display')!='none'){
		$('.emailOutput3').hide();
	}     

	//숨겨진 수정과 관련된 div를 보이게 한다.
    $('.mypage_emailModiWrap').show();
    //클릭한 버튼은 안보이게 만들어준다.
    $('.emailFixBtn').hide();
  }
  
  
})
//수정취소를 누르면  숨겼던 수정버튼을 보이게한다.
$('.emailFixCancelBtn').on('click',function(e){
  e.preventDefault();
  if($('.emailFixBtn').css('display')=='none'){
    //펼쳤던 div 접어주기
    $('.mypage_emailModiWrap').hide();
    //수정버튼을 다시 보이게 한다.
    $('.emailFixBtn').show();
  }
})

//이메일 리스트 변경이 있으면
$('.emailList').change(function(){
  //이메일 리스트가 inputtype 나오게 표시하기
  
  if($('.emailList option:selected').val()=='etc'){
    $('.email_InputValue2').val('');
    $(".email_InputValue2").attr("disabled",false);
  }
  else{
    $('.email_InputValue2').val($('.emailList option:selected').text());
    $(".email_InputValue2").attr("disabled",true);
  }
  
})
//이메일 수정완료
$('.emailFixSuccessBtn').on('click',function(e){
	e.preventDefault();

	 let email = $('.email_InputValue1').val();
	 let num = email.search(/[0-9]/g);
	 let eng = email.search(/[a-z]/ig);
	 let spe = email.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
	 let hangulcheck = email.search(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/);
	 if(email.length > 30){
	  alert("30자리 이내로 입력해주세요.");
	  return false;
	 }else if(email.search(/\s/) != -1){
	  alert("이메일은 공백 없이 입력해주세요.");
	  return false;
	 }else if(spe > 0 || hangulcheck > 0 ){
	  alert("영문,숫자만 입력해주세요.");
	  return false;
	 }else{
		$.ajax({
	            url:'../customer/mypage/mypage.do',
	            type:'post',
	            data:{type:'email',
					  email_InputValue1:$('.email_InputValue1').val(),
					  email_InputValue2:$('.email_InputValue2').val(),
				},
				dataType: 'TEXT', 
	            success:function(data){
					//펼쳤던 div 접어주기
				    $('.mypage_emailModiWrap').hide();
				    //수정버튼을 다시 보이게 한다.
				    $('.emailFixBtn').show();
					//이메일 수정된 걸로 갱신해주기
					$('.emailOutput1').text(data);
					//수정완료 메시지 띄어주기
					$('.emailOutput3').show();	
					//입력한 수정된 번호칸 클리어
					$('.email_InputValue1').val('');	
				}
        })

		return true;
	 }
		
	

	
})

/**
  주소 수정
*/
$('.addrFixBtn').on('click',function(e){
  e.preventDefault();
  if($('.mypage_addrModiWrap').css('display')=='none'){
    
	//1번 수정을 끝낸 후, 다시 수정하려고 할 때 처음 수정완료했던 메시지 숨겨주기
	if($('.addrOutput3').css('display')!='none'){
		$('.addrOutput3').hide();
	}
	
	//숨겨진 수정과 관련된 div를 보이게 한다.
    $('.mypage_addrModiWrap').show();
    //클릭한 버튼은 안보이게 만들어준다.
    $('.addrFixBtn').hide();
  }
  
  
})
//수정취소를 누르면  숨겼던 수정버튼을 보이게한다.
$('.addrFixCancelBtn').on('click',function(e){
  e.preventDefault();
  if($('.addrFixBtn').css('display')=='none'){
    //펼쳤던 div 접어주기
    $('.mypage_addrModiWrap').hide();
    //수정버튼을 다시 보이게 한다.
    $('.addrFixBtn').show();
  }
})
//주소 수정완료
$('.addrFixSuccessBtn').on('click',function(e){
	e.preventDefault();
	let address = $('.addr_InputValue1').val()+$('.addr_InputValue2').val();
	 let num = address.search(/[0-9]/g);
	 let eng = address.search(/[a-z]/ig);
	 let spe = address.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
	 let hangulcheck = address.search(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/);
	 if(email.length > 60){
	  alert("60자리 이내로 입력해주세요.");
	  return false;
	 }else if(spe > 0 || eng > 0 || spe > 0){
	  alert("한글,숫자로만 입력해주세요.");
	  return false;
	 }else{
		$.ajax({
		            url:'../customer/mypage/mypage.do',
		            type:'post',
		            data:{type:'address',
						  addr_InputValue1:$('.addr_InputValue1').val(),
						  addr_InputValue2:$('.addr_InputValue2').val(),
					},
					dataType: 'TEXT', 
		            success:function(data){
						//펼쳤던 div 접어주기
					    $('.mypage_addrModiWrap').hide();
					    //수정버튼을 다시 보이게 한다.
					    $('.addrFixBtn').show();
						//주소 수정된 걸로 갱신해주기
						$('.addrOutput1').text(data);
						//수정완료 메시지 띄어주기
						$('.addrOutput3').show();	
						//입력한 수정된 번호칸 클리어
						$('.addr_InputValue1').val('');	
					}
	        })
	}
})

//회원탈퇴버튼
function rmCustFunc(){
	Swal.fire({
		title: '회원 탈퇴를 원하십니까?',
		text: '모든 계좌도 같이 삭제 됩니다.',
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#7AB53F',
		confirmButtonText: '탈퇴',
		cancelButtonText:'취소',
		reverseButtons:'true'
	}).then((result) => {
		if (result.isConfirmed) {
			Swal.fire({
				title:'탈퇴 완료',
				text:'지금까지 이용해주셔서 감사합니다.',
				icon:'success',
				confirmButtonColor: '#7AB53F',
				confirmButtonText: '확인'
			}).then((result) => {
				if(result.isConfirmed){
					let documents = document.rmCustForm;
					documents.submit();
				}
			})
			
		}
	})
}


//기본 ajax 통신
	/*
	function startAjax() {
		
		
		let xhr = new XMLHttpRequest();
		xhr.open("POST", "mypage.do");
		xhr.onreadystatechange = function(){
			if (xhr.readyState==4 && xhr.status==200){
			//responseText : 서버가 응답한 데이터
			alert("응답");
		}
		};
		let data = 'modifiedPhoneNumber='+$('.phone_InputValue').val();
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.send(data);
		
		 
	}
	*/


