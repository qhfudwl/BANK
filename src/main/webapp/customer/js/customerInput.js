/**
 * 
 */
// 취소 버튼 클릭시 로그인 화면으로 이동
        function goLoginForm() {
            location.href="../login/login.jsp";
        }
        //Form 값을 받아 제출 할 때 미입력 값 그리고 유효성 검사 하기
        function checkValue() 
        {
        	var form = document.CustomerInfo;
			if(!form.userId.value){
        		alert("아이디를 입력하세요.");
        		return false;
        	}
			let pw = $("#passwd").val();
			let numExp =pw.search(/[0-9]/g);
			let engExp = pw.search(/[a-z]/ig);
        	if(!form.passwd.value){
        		alert("비밀번호를 입력하세요.");
        		return false;
        	}
			if(pw.length<8 || pw.length>20){
	            alert("비밀번호를 8자리~20자리로 입력 하세요.");
	            return false;
			}else if(pw.search(/\s/) != -1){
				alert("비밀번호를 공백없이 입력 해주세요.");
				return false;
			}else if(numExp < 0 || engExp < 0){
				alert("비밀번호를 영문,숫자를 혼합해 입력해주세요.");
				return false;
			}
        	if(form.passwd.value != form.passwdcheck.value){
                alert("비밀번호를 동일하게 입력하세요.");
                return false;
            }
        	let nameExp = /^[가-힣a-zA-Z]+$/gm;
			if(form.userName.value.match(nameExp) == null){
				alert("올바른 이름 형식이 아닙니다.");
				return false;
			}
        	let ssnExp = /^\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|[3][0-1])$/gm;
        	if(form.ssn1.value.match(ssnExp) == null){
        		alert("올바른 주민등록번호 형식이 아닙니다.");
                return false;
        	}
        	if(isNaN(form.ssn2.value)){
                alert("주민등록번호는 숫자만 입력 가능합니다.");
                return false;
            }
            let phoneExp = /^(01[0-9])$/gm;
        	if(form.phone1.value.match(phoneExp) == null){
                alert("올바른 휴대전화 번호 공식이 아닙니다.");
                return false;
            }
        	
        	if(isNaN(form.phone2.value)){
                alert("휴대전화 번호는 숫자만 입력 가능합니다.");
                return false;
            }
        	if(isNaN(form.phone3.value)){
                alert("휴대전화 번호는 숫자만 입력 가능합니다.");
                return false;
            }
        	if(!form.email1.value){
        		alert("이메일 아이디를 입력 해주세요.");
        		return false;
        	}
        	if(form.email2.value == "null"){
        		alert("이메일 @경로를 선택 해주세요.");
        		return false;
        	}
        	if(!form.addr.value){
        		alert("주소를 입력 해주세요.");
        		return false;
        	}
			if(form.idcheck2.value == "idUncheck"){
				alert("중복체크를 진행 해주세요.")
				return false;
			}
        }
		let isUser = false;
		function idCheckFunc() {
			$.ajax({
					url:'customer_id_check.do',
					type:'post',//전송방식
					data: {type:'id',id_InputValue:$('#userId').val()},
					dataType : 'TEXT',
					success:function(data){
						
						let idExp = /^[a-zA-Z0-9]+$/gm;
						if(data=="false"){
							isUser = true;
							console.log($('#userId').val());
							$('#userId').val("중복된 값입니다.");
							$('#idcheck2').val("idUncheck");
						}else if($('#userId').val().match(idExp) == null){
							$('#userId').val("영문,숫자만 입력 가능합니다.");
							$('#idcheck2').val("idUncheck");
							}else{
							isUser = false;
							$('#idcheck2').val("idcheck");
							alert("아이디를 사용 할 수 있습니다.");
						}
					}
			})
		}
		$("#userId").on("click",function(){
			$('#idcheck2').val("idUncheck");
			if(isUser==true){
				$('#userId').val("");
			}
		})

		$("#passwdShow").on("click", function() {
  		if ($("#passwd").attr("type") == "password") {
    		$("#passwd").attr("type", "text");
    		$($(this)).text("HIDE");
  		} else {
    		$("#passwd").attr("type", "password");
    		$($(this)).text("SHOW");
  		}
		});
		$("#passwdShow2").on("click", function() {
  		if ($("#passwdcheck").attr("type") == "password") {
    		$("#passwdcheck").attr("type", "text");
    		$($(this)).text("HIDE");
  		} else {
    		$("#passwdcheck").attr("type", "password");
    		$($(this)).text("SHOW");
  		}
		});
		function InputIdCheck(){
        	document.CustomerInfo.idDuplication.value = "idUncheck";
        }
		