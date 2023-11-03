// 회원가입 팝업 open
function openSignupPopup() {
	layerPop('signupPopup')
}


// 회원가입 팝업 close
function closeSignupPopup() {
	const form = document.getElementById('signupForm');
	form.memberId.readOnly = false;
	form.querySelector('#idCheckBtn').disabled = false;
	form.reset();
	layerPopClose();
}


// 아이디 중복 체크
function checkLoginId() {
	const loginId = document.querySelector('#signupForm input[name="memberId"]');
	isValid(loginId, '아이디');
	const count = getJson(`/member-count`, { memberId : loginId.value });

	if (count > 0) {
		alert('이미 가입된 아이디가 있습니다.');
		loginId.focus();
		return false;
	}

	if (confirm('사용 가능한 아이디입니다.\n입력하신 아이디로 결정하시겠어요?')) {
		loginId.readOnly = true;
		document.getElementById('idCheckBtn').disabled = true;
	}
}


// 회원 정보 유효성 검사
function validationMemberInfo(form) {
	const fields = form.querySelectorAll('input:not([type="radio"])');
	const fieldNames = ['아이디', '비밀번호', '비밀번호 재입력', '이름', '나이', '이메일'];

	for (let i = 0, len = fields.length; i < len; i++) {
		isValid(fields[i], fieldNames[i]);
	}

	if (form.memberId.readOnly === false) {
		alert('아이디 중복 체크를 완료해 주세요.');
		throw new Error();
	}

	if (form.password.value !== form.passwordCheck.value) {
		alert('비밀번호가 일치하지 않습니다.');
			form.passwordCheck.focus();
			throw new Error();
		}
	}


// 회원 정보 저장 (회원가입)
function saveMember() {
	// 1. 필드 유효성 검사
	const form = document.getElementById('signupForm');
	
	validationMemberInfo(form);
	
	// 2. 파라미터 세팅
	const params = {}
	new FormData(form).forEach((value, key) => params[key] = value.trim());
	params.age = parseInt(params.age, 10);
	
	
	// 3. Save API 호출
	callApi('/members', 'post', params);
	alert('가입을 축하드립니다!\n로그인 후 서비스를 이용해 주세요.');
		closeSignupPopup();
}

// Enter 로그인 이벤트 바인딩
window.onload = () => {
	document.querySelectorAll('#memberId, #password').forEach(element => {
		element.addEventListener('keyup', (e) => {
			if (e.keyCode === 13) {
				login();
			}
		})
	})
}


// 로그인
function login() {

	const form = document.getElementById('loginForm');

	if ( !form.memberId.value || !form.password.value ) {
		alert('아이디와 비밀번호를 모두 입력해 주세요.');
		form.memberId.focus();
		return false;
	}

	$.ajax({
		url : '/login',
		type : 'POST',
		dataType : 'json',
		data : {
			loginId: form.memberId.value,
			password: form.password.value
		},
		async : false,
		success : function (response) {
			location.href = '/communities';
		},
		error : function (request, status, error) {
			alert('아이디와 비밀번호를 확인해 주세요.');
		}
	})
}