var isSelected = false;
var categoryBtns = document.querySelectorAll('.cat');
  
categoryBtns.forEach((categoryBtn) => {
   categoryBtn.addEventListener('click', function() {

    // area에는 지역 선택 부분이 없어도 현재 위치의 위경도가 들어가게 할것임.
    const area = document.getElementById('area').value; 
    const category = categoryBtn.value;
    const date = document.getElementById('date').value;
    const startDate = document.getElementById('startDate').value;
    const endDate = document.getElementById('endDate').value;
    const title = document.getElementById('title').value;
                              
    fetch(`/search?area=${area}&category=${category}&startDate=${startDate}&endDate=${endDate}&date=${date}&title=${title}`)
        .then(response => response.text())
        .then(html => {
            document.querySelector('.frame').innerHTML = html;
            addEventSelectButton();
        });
    });
});  

function addEventSelectButton() {
    var selectButtons = document.querySelectorAll(".select");
    
    selectButtons.forEach(function(button) {
        button.addEventListener("click", function() {

            if (!isSelected) {
                isSelected = true;
                  
                makeInputTime();
                deleteTimePickerScript();
                loadTimePickerScript();
                pastePlaceAndMakeCancelButton(this);
                addEventApplyButton();
               }
           });
       });
}

 function makeInputTime() {
    // 선택한 장소
    var selectedPlace = document.createElement("div");
    selectedPlace.setAttribute("class", "container");
    selectedPlace.setAttribute("id", "selectedPlace");
    
    // 시작 시간 + 끝 시간
    let inputTime = document.createElement('div');
    inputTime.setAttribute("class", "row inputtime");
    inputTime.setAttribute("style", "padding: 10px;")

    // 시작 시간
    let startTime = document.createElement('div');
    startTime.setAttribute("class", "col-md-4 starttime");
    
    let startLabel = document.createElement("label");
    startLabel.textContent = '시작 시간';
    
    var startTimeInput = document.createElement("input");
    startTimeInput.setAttribute("type", "text");
    startTimeInput.setAttribute("class", "timepicker form-control");
    startTimeInput.setAttribute("id", "startTime");
    startTimeInput.setAttribute("style", "height: 40px; border: dotted; border-color: #E5F2FF;");
    
    startTime.appendChild(startLabel);
    startTime.appendChild(startTimeInput);
    inputTime.appendChild(startTime);
    
    // 끝 시간
    let endTime = document.createElement('div');
    endTime.setAttribute("class", "col-md-4 endtime");
    
    let endLabel = document.createElement("label");
    endLabel.textContent = '끝 시간';
    
    var endTimeInput = document.createElement("input");
    endTimeInput.setAttribute("type", "text");
    endTimeInput.setAttribute("class", "timepicker form-control");
    endTimeInput.setAttribute("id", "endTime");
    endTimeInput.setAttribute("style", "height:40px; border: dotted; border-color: #E5F2FF;");
    
    endTime.appendChild(endLabel);
    endTime.appendChild(endTimeInput);
    inputTime.appendChild(endTime);
    
    selectedPlace.append(inputTime);
    
    var frame = document.querySelector(".frame");
    frame.insertBefore(selectedPlace, frame.firstElementChild.nextSibling);
}
 
function deleteTimePickerScript() {
   // test 속성을 가지고 있는 스크립트를 모두 삭제
   var elementsToRemove = document.querySelectorAll('.test');

   // 가져온 요소를 순회하면서 삭제
   elementsToRemove.forEach(function(element) {
      element.remove();
   });
      
   // 타임피커 스크립트가 로드되면서 같이 생기는 태그 또한 삭제
   var gg = document.querySelector('.ui-timepicker-container');
       
   if (gg) {
      gg.remove();
   }
}

function loadTimePickerScript() {
   // 타임피커에 필요한 스크립트 로드
    const scriptsToLoad = [
	'https://code.jquery.com/ui/1.12.1/jquery-ui.js',
	'js/plan/jquery.timepicker.min.js',
	'js/plan/timepicker.js'
    ];

    loadScriptsSequentially(scriptsToLoad, 'test')
      .then(() => {
        console.log('모든 스크립트가 순서대로 로드되었습니다.');
      })
      .catch(error => {
        console.error('스크립트 로딩 중 오류가 발생했습니다:', error);
      });
}

function pastePlaceAndMakeCancelButton(selectedButton) {
    
    // 장소 붙여넣기
    var place = selectedButton.closest("#place").cloneNode(true);
    console.log(place);
    
    var selectedPlace = document.getElementById('selectedPlace');
    console.log(selectedPlace);
    selectedPlace.append(place);

    // 취소 버튼 만들기
    var cancelBtn = place.querySelector('.select');
    cancelBtn.innerText = '취소';
    cancelBtn.style = 'background-color: #E5F2FF; border-color: #E5F2FF;';

    var hr = document.createElement("hr");
    selectedPlace.append(hr);
      
    document.getElementById('places').style='height:132px';
    
    addEventCancleButton(cancelBtn);
}

function addEventCancleButton(cancelBtn) {
   
    cancelBtn.addEventListener("click", function(event) {
        //event.preventDefault();
        isSelected = false;
        this.closest("#selectedPlace").remove();
        document.getElementById('places').style='height:350px';
    });
}

function addEventApplyButton() {
    var button = document.getElementById('applyBtn');
                
    button.addEventListener('click', function() {
        const date = document.getElementById('date').value;
        const startTime = document.getElementById('startTime').value;
        const endTime = document.getElementById('endTime').value;
        const placeNo = selectedPlace.querySelector('.select').value;
        const placeName = selectedPlace.querySelector('#placeName').textContent;
        const placeCategory = selectedPlace.querySelector('#placeCategory').textContent;
        const placeAddr = selectedPlace.querySelector('#placeAddr').textContent;
        var givemedate = localStorage.getItem('givemedate');
        const startHour = convertTo24HourFormat(startTime);
        const endHour = convertTo24HourFormat(endTime);
                    
        setBlock(givemedate, date, startHour, endHour, placeNo);
    });
}

function convertTo24HourFormat(timeString) {
    const date = new Date("2000-01-01 " + timeString);
    return date.getHours().toString().padStart(2, '0');
}

function setBlock(givemedate, date, startHour, endHour, placeNo) {
    let color;
    
    $.ajax({
        type: "POST",
        url: `/setblock`,
        data: {
            date: givemedate,
            startHour: startHour,
            endHour: endHour,
            place_no: placeNo
        },
        success: function(response) {
            color = response.body.color;
            updatePlanDTO(date, startHour, endHour, placeNo, color);     
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("실패했습니다.");
            console.log("HTTP 상태 코드: " + jqXHR.status);
            console.log("서버 응답: " + jqXHR.responseText);
        }
    });
}

function updatePlanDTO(date, startHour, endHour, placeNo, color) {
    const planDTO = {
    "date": date,
    "startHour": startHour,
    "endHour": endHour,
    "place_no": placeNo,
    "place_color": color
  };
  
  const jsonData = JSON.stringify(planDTO);

  fetch('/addPlan', {
      method: 'POST',
      headers: {
          'Content-Type': 'application/json',
      },
      body:jsonData
  })
  .then(response => response.text())
  .then(html => {
	// 응답으로 planForm 받음
	document.querySelector('.frame').innerHTML = html;
    removeCategories();

    resizeMap();
    deleteMapScript();
    loadMapScript();

    setBlockStyle();
    deleteDateRangeScript();
    loadDateRangeScript();
  });
}

function removeCategories() {    
    let cat2 = document.querySelector('.categories');
    cat2.remove();
}

function resizeMap() {
    let map2 = document.getElementById('map');
    console.log(map2);
    map2.style='height: 480px;';
}

function deleteMapScript() {
    var remove = document.querySelectorAll('.test3');

    remove.forEach(function(element) {
        element.remove();
    });
}

function loadMapScript() {
    loadScript("//dapi.kakao.com/v2/maps/sdk.js?appkey=0a4e5f604e58c301ddedd0a6790ec3bf", "test3");
          
    loadScript('js/plan/map.js', "test3");
}

function setBlockStyle() {
    let resColors = document.querySelectorAll('.color');
      let details = document.querySelectorAll('.weatherplace');

      resColors.forEach((colorElement, index) => {
          console.log("res: " + colorElement.value);

          let color = colorElement.value;
          let detail = details[index];  // 현재 colorElement에 대응하는 detail을 가져옴

          // detail에 스타일 적용
          if (color === "GN") {
              detail.setAttribute('style', 'background-color: #CFFFCD; ');
              let span = document.createElement('span');
              span.textContent = "☀";
              span.style.color = "#FFB000";
              detail.appendChild(span);
          } else if (color === "RD") {
              detail.setAttribute('style', 'background-color: #FF495F;color:white');
             
          } else if (color === 'GY') {
              detail.setAttribute('style', 'background-color: #F8F9FA');
          }
      });
}

function deleteDateRangeScript() {
    //https://code.jquery.com/ui/1.12.1/jquery-ui.js
    // 첫 번째 스크립트 로드 후 두 번째 스크립트 로드
    var remove = document.querySelectorAll('.test2');

    // 가져온 요소를 순회하면서 삭제
    remove.forEach(function(element) {
        element.remove();
    });
      
    var hh = document.querySelector('.daterangepicker');
      
    if (hh) {
        hh.remove();
    }
    
    // loadDateRangeScript();
}

function loadDateRangeScript() {
    // planForm에서 daterangepicker와 periodcalculator와 addblock 필요
    // 세션에 저장된 데이터를 보여주기 위해
    const scriptsToLoad = [
	'js/plan/daterangepicker.js',
	'js/plan/planperiodcalculator.js',
	'js/plan/addblock.js'
    ];

    loadScriptsSequentially(scriptsToLoad, 'test2')
      .then(() => {
        console.log('모든 스크립트가 순서대로 로드되었습니다.');
      })
      .catch(error => {
        console.error('스크립트 로딩 중 오류가 발생했습니다:', error);
      });
}

function loadScript(url, className) {
    return new Promise((resolve, reject) => {
      const script = document.createElement('script');
      script.type = 'text/javascript';
      script.setAttribute("class", className);
      script.src = url;
      script.onload = resolve;
      script.onerror = reject;
      document.body.appendChild(script);
    });
  }

  async function loadScriptsSequentially(scriptUrls, className) {
    for (const url of scriptUrls) {
      await loadScript(url, className).catch(error => console.error(`Failed to load script: ${url}`, error));
    }
  }