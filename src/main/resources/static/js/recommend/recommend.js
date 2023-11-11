$(document).ready(function() {
    $('.ageFilter').click(function() {
        var age = $(this).data('age');
        // 활성 버튼 스타일 변경
        $('.ageFilter').removeClass('active');
        $(this).addClass('active');
        // 필터링
        $('.item').hide();
        $('.item.' + age).show();
    });
    // 초기에 "20대" 버튼을 활성화
    $('.ageFilter[data-age="age20"]').trigger('click');
});


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






$(document).ready(function () {
    var initialLocate = "shop";

    // 모든 location 숨김
    $('.location').hide();
    $('.location.' + initialLocate).show();

    // 초기에 "카페" 버튼을 활성화
    $('.locateFilter[data-locate="' + initialLocate + '"]').addClass('active');
    // 초기 AJAX 호출
    sendAjaxRequest(initialLocate);

    $('.locateFilter').click(function () {
        var locate = $(this).data('locate');

        // 활성 버튼 스타일 변경
        $('.locateFilter').removeClass('active');
        $(this).addClass('active');

        // 모든 location 숨김
        $('.location').hide();
        $('.location.' + locate).show();

        // AJAX 호출
        sendAjaxRequest(locate);
    });

    function handleResponse(response,locate) {

    	  // response가 유효하며 body와 list 속성이 있는 경우에만 처리
    	  if (response && response.body && response.body.list) {
    		  const locateClass = locate;
              console.log('Locate Class:', locateClass);

    	    // 현재 위치에 해당하는 목록을 선택
    	    const $locationList = $(`.location.${locateClass}`);
    	    console.log('Location List:', $locationList);

    	    // 현재 위치의 목록을 비움
    	    $locationList.empty(); // 기존 목록 초기화

    	    // 각 항목에 대한 텍스트를 생성하여 현재 위치의 목록에 추가
    	    $.each(response.body.list, function(_, item) {
    	    
    	      const items1 = item.place_NAME;
    	      const items2 = item.road_NAME_ADR;
    	      const items3 = item.distance;
    	      const listItem = `<ul><li>${items1}</li><li>${items2}</li><li>${items3}km</li></ul>`;

    	      $locationList.append(listItem);
    	    });
    	  }
    	}

    // Ajax 요청 보내는 함수
    function sendAjaxRequest(locate) {
      $.ajax({
        type: 'POST',
        url: `/get-additional-data2`,
        data: { locate: locate },
        success: function(response) {
          // 서버에서 반환된 데이터를 처리
          console.log('Additional data:', response);

          // 받은 데이터를 처리하는 함수 호출
          handleResponse(response,locate);
        },
        error: function(error) {
          console.error('Error fetching additional data:', error);
        }
      });
    };
 })