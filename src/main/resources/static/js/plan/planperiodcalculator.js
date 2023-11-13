$(function() {
        $('input[name="daterange"]').daterangepicker({
        	    locale: {
        		    "separator": " ~ ",                     // 시작일시와 종료일시 구분자
        		    "format": 'YYYY-MM-DD',     // 일시 노출 포맷
        		    "applyLabel": "확인",                    // 확인 버튼 텍스트
        		    "cancelLabel": "취소",                   // 취소 버튼 텍스트
        		    "daysOfWeek": ["일", "월", "화", "수", "목", "금", "토"],
        		    "monthNames": ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"]
        		    },
        		    showDropdowns: true,                     // 년월 수동 설정 여부
        		    autoApply: false                         // 확인/취소 버튼 사용여부
        		}
        	);
        
        $('input[name="daterange"]').on('change', function () {
            // 값이 변경되면 콘솔에 출력
            //console.log('Value changed:', $(this).val());
        	
            const startDate = $('input[name="daterange"]').data('daterangepicker').startDate._d;
            //console.log(startDate);
            
            const endDate = $('input[name="daterange"]').data('daterangepicker').endDate._d;
            const timeDiff = Math.abs(endDate - startDate);
            const daysDiff = Math.ceil(timeDiff / (1000 * 60 * 60 * 24));
            //console.log(`기간: ${daysDiff} 일`);
            
            const detailsElement = document.querySelector('.details');
            //console.log(detailsElement);
            
            const childElements = Array.from(detailsElement.children);
           
            childElements.forEach((element) => {
                  element.remove();
              });
                        
            const date = $('input[name="daterange"]').data('daterangepicker').startDate._d;
            const nextDate = new Date(date);
           // console.log(date);
           
            for (let i=1; i<=daysDiff; i++) {
               let daySchedule = document.createElement("div");
               daySchedule.setAttribute("class", "form-control2");
               
               detailsElement.appendChild(daySchedule);
               
               let div = document.createElement("div");
               div.setAttribute("class", "div");
               div.style='padding:10px 10px 10px 30px;';
            
               
               let h2 = document.createElement("h2");
               h2.setAttribute("class", "h2");
               h2.style='padding:10px 10px 10px 10px; display:inline-block;';
               h2.innerText = "DAY " + i; 
               
               div.appendChild(h2);
               
               let span = document.createElement("span");
               
               let nextDateMoment = moment(nextDate).add(i - 1, 'days');
               span.setAttribute("class", "span");
               span.innerText = nextDateMoment.format('YYYYMMDD');
               span.style = 'display:inline-block';
              
               div.appendChild(span);
               
               daySchedule.appendChild(div);
              
               
               let button = document.createElement("button");
               button.setAttribute("class", "form-control");
               button.setAttribute("id", "add-schedule-button");
               button.innerText = "일정 추가";
               button.style="border: none";
               button.type = "button";
               
               // button value
               //nextDate.setDate(nextDate.getDate() + 1);
               //button.value = nextDate.toISOString().slice(0, 10).replace(/-/g, '');
               button.value = i-1;
               
               
               // 일정 추가 버튼 눌렀을 시
               button.addEventListener("click", function() {
            	   let title = document.querySelector('.title').value;
            	   let startDateString = moment(startDate).format('YYYYMMDD');
                   let endDateString = moment(endDate).format('YYYYMMDD');
                   let date = button.value;
                   console.log(title);
                   // console.log(date);
                   
                         fetch(`/search?title=${title}&startDate=${startDateString}&endDate=${endDateString}&date=${date}`)
                             .then(response => response.text())
                             .then(html => {
                            	 // searchForm.html
                                 document.querySelector('.frame').innerHTML = html;
                                 
                                 // 지도 위에 카테고리 만들기
                                 let map = document.querySelector('.map');
                                 console.log(map);
                                
                                var categories = document.querySelector('.categories');
                                
                                if (!categories) {
                                 // 부트스트랩 비율
                                 let div2 = document.createElement('div');
                                 div2.setAttribute("class", "row categories");
                                 div2.setAttribute("style", "padding: 0px 10px 10px 10px;")

                                 let div3 = document.createElement('div');
                                 div3.setAttribute("class", "col-md-4");

                                 let div = document.createElement('div');
                                 div.setAttribute("class", "category row");
                                 
                                 // 음식점
                                 let button = document.createElement('button');
                                 button.type = 'button';
                                 button.setAttribute("class", "form-control cat");
                                 button.setAttribute("name", "category");
                                 button.setAttribute("id", "category");
                                 button.setAttribute("value", "음식점");
                                 button.style = "border: dotted; border-color: #E5F2FF; margin:5px;";
                                 button.textContent = "음식점";

                                 div3.appendChild(div);
                                 div.appendChild(button);

                                 // 관광지
                                 let div4 = document.createElement('div');
                                 div4.setAttribute("class", "col-md-4");  

                                 let div5 = document.createElement('div');
                                 div5.setAttribute("class", "category row");

                                 let button2 = document.createElement('button');
                                 button2.setAttribute("class", "form-control cat");
                                 button2.setAttribute("name", "category");
                                 button2.setAttribute("value", "관광지");
                                 button2.textContent = "관광지";
                                 button2.style = "border: dotted; border-color: #E5F2FF; margin:5px;";

                                 div4.appendChild(div5);
                                 div5.appendChild(button2);

                                 // 행사/공연/축제
                                 let div6 = document.createElement('div');
                                 div6.setAttribute("class", "col-md-4"); 

                                 let div7 = document.createElement('div');
                                 div7.setAttribute("class", "category row");

                                 let button3 = document.createElement('button');
                                 button3.setAttribute("class", "form-control cat");
                                 button3.setAttribute("name", "category");
                                 button3.setAttribute("value", "행사/공연/축제");
                                 button3.textContent = "행사/공연/축제";
                                 button3.style = "border: dotted; border-color: #E5F2FF; margin:5px;";

                                 div6.appendChild(div7);
                                 div7.appendChild(button3);

                                 div2.appendChild(div3);
                                 div2.appendChild(div4);
                                 div2.appendChild(div6); 
                                 map.insertBefore(div2, map.firstChild);
                                
                                 // map 사진 높이 바꾸는 거 추후에 할 예정
                                 let map2 = document.getElementById('map');
                                 console.log(map2);
                                 map2.style='height: 400px;';
                                
                                 // 존재하던 스크립트 지우고 다시 실행 -> 지우지 않으면 에러 발생
                                 function loadScript(src, callback) {
                               	    let script = document.createElement('script');
                               	    script.src = src;
                               	  script.setAttribute("class", "test3");
                               	    script.addEventListener('load', callback);
                               	    document.body.appendChild(script);
                               	}
                                 
                                 var remove = document.querySelectorAll('.test3');

                                 remove.forEach(function(element) {
                                     element.remove();
                                 });
                                 
                                 
								 loadScript("//dapi.kakao.com/v2/maps/sdk.js?appkey=0a4e5f604e58c301ddedd0a6790ec3bf", function() {
								                                	 
								                                 
								 });
                                 
                                 loadScript('js/plan/map.js', function() {
                                	 
                                 });
                                 
                                 // 지역 + 카테고리 클릭 시 서버에 db 검색 요청 & 선택, 취소, 적용 버튼 이벤트
                                 loadScript('js/plan/clickcategoryandselectandcancelandapply.js', function() {
                                	 
                                 });
                                 
                                 // 키워드 검색 서버 요청
                                 loadScript('js/plan/search.js', function () {
                            	 });
                                }
                             });
                             
                     });
               
               daySchedule.appendChild(button);
               
               let br = document.createElement("br");
               detailsElement.appendChild(br);
            }
          })
         
        var start = document.querySelector(".startDate");
        var end = document.querySelector(".endDate");
        console.log(start);
        if (start.value) {
	        var startValue = moment(start.value, 'YYYYMMDD').format('YYYY-MM-DD');
	        var endValue = moment(end.value, 'YYYYMMDD').format('YYYY-MM-DD');
	        console.log(startValue);
	        console.log(endValue);
	        //var start2 = '03/01/2014';
	        //console.log(start2);
	        //console.log(start.textContent);
	        	//console.log(start.text);
	        	//moment(originalDateString, 'YYYYMMDD').format('DD/MM/YYYY');
	         $('input[name="daterange"]').data('daterangepicker').setStartDate(startValue);
	         $('input[name="daterange"]').data('daterangepicker').setEndDate(endValue);
        }
      });