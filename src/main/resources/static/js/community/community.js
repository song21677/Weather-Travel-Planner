function autoResize(textarea) {
	textarea.style.height = 'auto';
	textarea.style.height = textarea.scrollHeight + 'px';
}

// Ajax 요청 수행
function loadPlannerData(travelPlanNo) {
	console.log(travelPlanNo, typeof travelPlanNo);
	travelPlanNo = parseInt(travelPlanNo);
	console.log(travelPlanNo, typeof travelPlanNo);
    $.ajax({
        url: '/communities/newes/plan-list/' + travelPlanNo,
        type: 'GET',
        success: function(data) {
            // 받은 데이터를 HTML에 삽입
        	$("#write-content").replaceWith(data);
        },
        error: function(error) {
            console.error('Error:', error);
        }
    });
}
