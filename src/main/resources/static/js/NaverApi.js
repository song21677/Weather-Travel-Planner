



$(document).ready(function() {
	
    // 위치 정보를 가져옵니다.
    fetchAddress();


    function fetchAddress() {
        if ("geolocation" in navigator) {
            navigator.geolocation.getCurrentPosition(successCallback, errorCallback);
        } else {
            alert("이 브라우저는 위치 서비스를 지원하지 않습니다.");
        }
    }

    function successCallback(position) {
        const latitude = position.coords.latitude;
        const longitude = position.coords.longitude;

        const storedLat = parseFloat(localStorage.getItem("lastLat"));
        const storedLon = parseFloat(localStorage.getItem("lastLon"));
       
        // 현재 좌표를 로컬 스토리지에 저장
        localStorage.setItem("lastLat", latitude.toString());
        localStorage.setItem("lastLon", longitude.toString());

        // AJAX를 사용하여 좌표를 서버의 컨트롤러로 전송
        $.post(`/get-address?lat=${latitude}&lon=${longitude}`, function(data) {
            if (data && data.address) {
                const address = data.address;
                if (window.location.pathname === '/recommend') {
                	$.ajax({
                	    type: "POST",
                	    url: `/get-additional-data`,
                	    data: {
                	        lat: latitude,
                	        lon: longitude,
                	        address: address
                	    },
                	   
                	    success: function(additionalData) {
                	        if (additionalData) {
                	            // 추가 데이터를 사용하여 작업 수행
                	            console.log("성공: 추가 데이터 성공적으로 검색됨.", additionalData);
                	        } else {
                	            console.log("실패: 추가 데이터를 가져올 수 없음.");
                	        }
                	    },
                	    error: function() {
                	        console.log("실패: 추가 데이터를 가져오기 위한 AJAX 요청이 실패했습니다.");
                	    }
                    });
                }
                else{ $("#resultLocation").text(address); } // 주소를 웹 페이지에 표시
            } else {
            	
            	$("#resultLocation").text("현재 위치를 알 수가 없습니다.");
                alert("서버로부터 주소 정보를 가져오는 데 실패했습니다.");
            }
        })
        .fail(function() {
        	$("#resultLocation").text("현재 위치를 알 수가 없습니다.");
            alert("서버 요청 중 에러가 발생했습니다. 다시 시도해주세요.");
        });
    }

    function errorCallback(error) {
        alert("위치 정보를 가져오는데 오류가 발생했습니다.");
        $("#resultLocation").text("현재 위치를 알 수가 없습니다.");
    }
});
