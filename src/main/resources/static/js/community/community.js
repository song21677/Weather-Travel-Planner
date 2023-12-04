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


// 파일 선택(input[type="file"]) 이벤트 핸들러
document.getElementById('imageFiles').addEventListener('change', function (e) {
    const imagePreview = document.getElementById('imagePreview');
    const fileInput = e.target;

    // 선택한 파일들을 반복하여 미리 로드하고 화면에 표시
    for (const file of fileInput.files) {
        const reader = new FileReader();
        reader.onload = function (event) {
            const img = document.createElement('img');
            img.src = event.target.result; // 이미지 데이터 설정
            imagePreview.appendChild(img); // 이미지를 화면에 추가
        };
        reader.readAsDataURL(file); // 이미지 파일을 읽어서 데이터 URL로 변환
    }
    
    imagePreview.style.display = 'block'; // 이미지 미리보기 표시
});


function checkPlaceReview(detailPlanNo) {
	if (detailPlanNo != null) {
		const count = getJson('/placeReview-count', {
			detailPlanNo : parseInt(detailPlanNo)
		});
		return count
	}

}

// 이미지 업로드 API 호출 (여러 이미지 업로드)
function uploadImages(uri, method, imageFiles) {
    const formData = new FormData();

    for (let i = 0; i < imageFiles.length; i++) {
        formData.append('imageFiles', imageFiles[i]);
    }
    
    $.ajax({
		url : uri,
		type : method,
		dataType : 'json',
		contentType: false,
		processData: false,
		data : formData,
		success : function(response) {
			json = response;
		},
		error : function(request, status, error) {
			console.log(error);
		}
	})
}

async function saveTravelReview(reviewNo, planNo) {
	// 1. 필드 유효성 검사
	const form = document.getElementById('reviewForm');
	// 이미지 파일 가져오기
	const imageFiles = form.querySelector('input[type="file"]').files;
	
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
	
	// 2. 이미지 업로드
    let reviewID;
    let fileUrl;
    
 // 여행기 등록 또는 수정 시 필요한 데이터 설정
    const communityRequest = {
        travelReviewNo: parseInt(reviewNo),
        travelPlanNo: parseInt(planNo),
        plannerReviewTitle: form.elements.namedItem("title").value,
        plannerReviewContent: form.elements.namedItem("content").value,
    };
	
	// 여행기 등록
	if (reviewNo != null) {
		console.log("여행기 등록" + communityRequest.travelReviewNo);
		reviewID = callApi("/communities", "put", communityRequest);
	}
	// 여행기 수정
	if (reviewNo === null || reviewNo === undefined) {
		console.log("여행기 등록" + communityRequest.travelReviewNo);
		reviewID = callApi("/communities", "post", communityRequest);
	}
	console.log("reviewID" + reviewID);
	fileUrl = "/communities/" + reviewID + "/files";
	console.log("fileUrl" + fileUrl);
	var travelReviewFileCount = getJson(`/travelReviewFile-count/`+ reviewID, {	
	});
	console.log("travelReviewFileCount : " + travelReviewFileCount);
	if (travelReviewFileCount > 0) {
		await uploadImages(fileUrl, "post", imageFiles);
	}
	if (travelReviewFileCount == 0) {
		await uploadImages(fileUrl, "post", imageFiles);
	}
	
	// detailPlanNo 요소 들을 가져와서 처리
	var detailPlanNoElements = document
			.querySelectorAll('input[name^="detailPlanNo"]');

	// 각 detailPlanNoElement에 대해 반복
	detailPlanNoElements
			.forEach(function(detailPlanNoElement, index) {
				// Rate 및 Content 값들을 저장할 배열
				var placeReviewRequest = {};

				var detailPlanNoSuffix = detailPlanNoElement.getAttribute(
						'name').substring('detailPlanNo'.length);
				console.log("detailPlanNoSuffix" + detailPlanNoSuffix);
				// placeNoElements에서 'placeNo' + detailPlanNoSuffix 라는 name을 가진
				// 요소를 선택
				var placeNoElement = document
						.querySelector('input[name="placeNo'
								+ detailPlanNoSuffix + '"]');

				console.log("placeNoElement" + placeNoElement.value);
				// rateElements 및 contentElements에서도 index를 기준으로 해당하는 요소를 가져옴
				var rateElement = document.querySelector('input[name="rate'
						+ detailPlanNoSuffix + '"]:checked');
				console.log("rateElement" + rateElement.value);
				var contentElement = document
						.querySelector('textarea[name="placeContent'
								+ detailPlanNoSuffix + '"]');
				console.log("contentElement" + contentElement.value);

				if (placeNoElement !== null && rateElement !== null
						&& rateElement !== undefined) {
					// Rate 및 Content 값을 객체로 묶어 배열에 추가
					placeReviewRequest.detailPlanNo = parseInt(detailPlanNoElement.value);
			        placeReviewRequest.placeNo = parseInt(placeNoElement.value);
			        placeReviewRequest.placeReviewContent = contentElement ? contentElement.value : '';
			        placeReviewRequest.placeReviewScore = parseInt(rateElement.value);
			        
					console.log("detailPlanNo : " + parseInt(detailPlanNoElement.value));
					console.log("placeNo : " + parseInt(placeNoElement.value));
					console.log("placeReviewScore : " + parseInt(rateElement.value));
					console.log("placeReviewContent : " + contentElement ? contentElement.value : '');
					var placeReviewCount = getJson(`/placeReview-count/`+ detailPlanNoElement.value , {
					});
					console.log("placeReviewCount : " + placeReviewCount);
					if (placeReviewCount > 0) {
						console.log("js put : " + placeReviewCount);
						callApi("/placeReview", "put", placeReviewRequest);
					}
					if (placeReviewCount == 0) {
						console.log("js post : " + placeReviewCount);
						callApi("/placeReview", "post", placeReviewRequest);
					}
				}
			});

	var travelReviewNo = getJson(`/travel-Review/` + parseInt(planNo), {
	});
	console.log("travelReviewNo : " + travelReviewNo);
	var endUrl = "/communities/" + travelReviewNo;
//	window.location.href = endUrl;
	
}
function callApi(uri, method, params) {

	let json = {}

	$.ajax({
		url : uri,
		type : method,
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		data : (params) ? JSON.stringify(params) : {},
		async : false,
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