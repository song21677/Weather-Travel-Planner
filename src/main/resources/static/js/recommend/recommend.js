$(document).ready(function() {
    // 페이지 경로가 '/recommend'인지 확인
    if (window.location.pathname === '/recommend') {
        // Ajax 요청 보내기
        sendAjaxRequest(0, 0, 0);
    }
});

// Ajax 요청을 보내는 함수 정의
function sendAjaxRequest(lat, lon, address) {
    $.ajax({
        type: "POST",
        url: `/get-additional-data`,
        data: {
            lat: lat,
            lon: lon,
            address: address
        },
        success: function(response) {
            // 성공적으로 처리된 경우의 동작
            console.log("성공했습니다.");
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("실패했습니다.");
            // 오류가 발생한 경우의 동작
            console.log("HTTP 상태 코드: " + jqXHR.status);
            console.log("서버 응답: " + jqXHR.responseText);
        }
    });
}