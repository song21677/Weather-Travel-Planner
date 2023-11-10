function autoResize(textarea) {
	textarea.style.height = 'auto';
	textarea.style.height = textarea.scrollHeight + 'px';
}

// Ajax 요청 수행
function loadPlannerData(travelPlanNo) {
	travelPlanNo = parseInt(travelPlanNo);
	$.ajax({
		url : '/communities/newes/plan-list/' + travelPlanNo,
		type : 'GET',
		success : function(data) {
			// 받은 데이터를 HTML에 삽입
			$("#write-content").replaceWith(data);
		},
		error : function(error) {
			console.error('Error:', error);
		}
	});
}

function checkPlaceReview(detailPlanNo) {
	if (detailPlanNo != null) {
		const count = getJson('/placeReview-count', {
			detailPlanNo : parseInt(detailPlanNo)
		});
		return count
	}

}

function saveTravelReview(reviewNo, planNo) {
	// 1. 필드 유효성 검사
	const form = document.getElementById('reviewForm');

	// 필드 유효성 검사 함수

	// 제목 필드 유효성 검사
	const title = form.elements.namedItem("title").value;
	if (title === "") {
		alert("제목을 입력해주세요.");
		return;
	}

	// 내용 필드 유효성 검사
	const content = form.elements.namedItem("content").value;
	if (content === "") {
		alert("내용을 입력해주세요.");
		return;
	}

	// 여행기 등록
	if (reviewNo != null) {
		const communityRequest = {
			travelReviewNo : parseInt(reviewNo),
			travelPlanNo : parseInt(planNo),
			plannerReviewTitle : form.elements.namedItem("title").value,
			plannerReviewContent : form.elements.namedItem("content").value
		};
		console.log(communityRequest);
		callApi("/communities", "put", communityRequest);
	}
	// 여행기 수정
	if (reviewNo === null || reviewNo === undefined) {
		const communityRequest = {
			travelPlanNo : parseInt(planNo),
			plannerReviewTitle : form.elements.namedItem("title").value,
			plannerReviewContent : form.elements.namedItem("content").value
		};
		console.log(communityRequest);
		callApi("/communities", "post", communityRequest);
	}

	// detailPlanNo 요소 들을 가져와서 처리
	var detailPlanNoElements = document
			.querySelectorAll('input[name^="detailPlanNo"]');

	// 각 detailPlanNoElement에 대해 반복
	detailPlanNoElements
			.forEach(function(detailPlanNoElement, index) {
				// Rate 및 Content 값들을 저장할 배열
				var placeReviewRequest = [];

				var detailPlanNoSuffix = detailPlanNoElement.getAttribute(
						'name').substring('detailPlanNo'.length);

				// placeNoElements에서 'placeNo' + detailPlanNoSuffix 라는 name을 가진
				// 요소를 선택
				var placeNoElement = document
						.querySelector('input[name="placeNo'
								+ detailPlanNoSuffix + '"]');

				// rateElements 및 contentElements에서도 index를 기준으로 해당하는 요소를 가져옴
				var rateElement = document.querySelector('input[name="rate'
						+ detailPlanNoSuffix + '"]:checked');
				var contentElement = document
						.querySelector('textarea[name="placeContent'
								+ detailPlanNoSuffix + '"]');

				if (placeNoElement && rateElement !== null
						&& rateElement !== undefined) {
					// Rate 및 Content 값을 객체로 묶어 배열에 추가
					placeReviewResponse
							.push({
								detailPlanNo : detailPlanNoElement.value,
								placeNo : placeNoElement.value,
								placeReviewScore : rateElement.value,
								placeReviewContent : contentElement ? contentElement.value
										: ''
							});
					console.log('PlaceReviewRequest:', placeReviewRequest);
					var placeReviewCount = getJson(`/placeReview-count`, { detailPlanNo : detailPlanNoElement.value });
					if (placeReviewCount > 0){
						callApi("/communities", "put", placeReviewRequest);
					}
					if (placeReviewCount==0){
						callApi("/communities", "post", placeReviewRequest);
					}
				}
			});

	var travelReviewNo = getJson(`/travel-Review`, { travelPlanNo : parseInt(planNo) });
	var endUrl = "/communities/"+ travelReviewNo + "/edit";
	callApi(endUrl, "get", null);
}

function callApi(uri, method, params) {

	let json = {}

	$.ajax({
		url : uri,
		type : method,
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		data : (params) ? JSON.stringify(params) : {},
		success : function(response) {
			json = response;
		},
		error : function(request, status, error) {
			console.log(error);
		}
	})

	return json;
}

function getJson(uri, params) {

	let json = {}

	$.ajax({
		url : uri,
		type : 'get',
		dataType : 'json',
		data : params,
		async : false,
		success : function(response) {
			json = response;
		},
		error : function(request, status, error) {
			console.log(error)
		}
	})

	return json;
}