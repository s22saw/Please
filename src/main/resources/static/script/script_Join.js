$(function(){

	// 회원가입 버튼 클릭 시 Action (/member/memberJoin)
	$("#joinSbmBtn").click(function(){
        let userID = $("input#userID").val();
        let userPW = $("input#userPW").val();
        let userName = $("input#userName").val();
        let userEmail = $("input#userEmail").val();
        let interest1 = $("select#interest1").val();
        let interest2 = $("select#interest2").val();
        let userContact = $("input#userContact").val();
        let validateNum = /[0-9]/;
        let validateChar = /[a-zA-Z]/;
        let validateSpc = /[~!@#$%^&*()_+|<>?:{}\-]/;
        let validateKor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;

        if(userID == "") {
            alert("아이디를 입력해 주세요.")
            $("#userID").focus();
        } else if(userPW == "") {
            alert("비밀번호를 입력해 주세요.")
            $("#userPW").focus();
        } else if(userName == "") {
            alert("이름을 입력해 주세요.")
            $("#userName").focus();
        } else if(userEmail == "") {
            alert("이메일을 입력해 주세요.")
            $("#userEmail").focus();
        } else if(interest1 == "") {
           alert("관심분야1을 선택해 주세요.")
           $("#interest1").focus();
        } else if (interest2 == "") {
            alert("관심분야2를 선택해 주세요.")
            $("#interest2").focus();
        } else if (!(validateNum.test(userContact)) || (validateChar.test(userContact)) || (validateSpc.test(userContact)) || (validateKor.test(userContact))) {
            alert("연락처는 숫자만 기입해 주세요.");
            $("#userContact").focus();
        } else {
            $("#regFrm").attr("action","/memberJoin");
            $("#regFrm").submit();
            alert("회원가입이 완료되었습니다.\n메인페이지로 이동합니다.");
		}
	});

	// 회원 정보 수정 버튼 클릭 시 Action (/member/memberDetail)
	$("#memberModifyBtn").click(function(){		
		let url = "/member/memberModify.html";
		location.href = url;
	});

	// 회원 정보 수정 완료 버튼 클릭 시 Action (/member/memberModify)
    $("#regUpdateBtn").click(function(){
        let modInterest1 = $("select#modInterest1").val();
        let modInterest2 = $("select#modInterest2").val();
        let modUserContact = $("input#modUserContact").val();
        let validateNum = /[0-9]/;
        let validateChar = /[a-zA-Z]/;
        let validateSpc = /[~!@#$%^&*()_+|<>?:{}\-]/;
        let validateKor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;

        if(modInterest1 == "") {
            alert("관심분야1 을 선택해 주세요.")
            $("#modInterest1").focus();
        } else if (modInterest2 == "") {
            alert("관심분야2 를 선택해 주세요.")
            $("#modInterest2").focus();
        } else if (!(validateNum.test(modUserContact)) || (validateChar.test(modUserContact)) || (validateSpc.test(modUserContact)) || (validateKor.test(modUserContact))) {
            alert("연락처는 숫자만 기입해 주세요.");
            $("#modUserContact").focus();
        } else {
            $("#regUpdateFrm").attr("action","/memberModify");
            $("#regUpdateFrm").submit();
            alert("회원 정보 수정이 완료되었습니다.");
        }
    });

	// 회원 탈퇴 버튼 클릭 시 Action (/member/memberDetail)
	$("#memberDeleteBtn").click(function(){		
		let choice = confirm('정말 회원 탈퇴 하시겠습니까?');
		
		if(choice) {
			let url = "/memberDelete";
			window.location = url;
			alert('정상적으로 탈퇴되었습니다.');
		} else {
		}
	});

});


