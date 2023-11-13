var categoryBtns = document.querySelectorAll('.cat');

       
categoryBtns.forEach((categoryBtn) => {
		// 카테고리 버튼 클릭 이벤트
       categoryBtn.addEventListener('click', function() {
              
              function addEventListeners() {
                 var isSelected = false;
                    
                 // 선택 버튼 클릭 이벤트
                    document.querySelectorAll(".select").forEach(function(button) {
                        button.addEventListener("click", function(event) {

                            if (!isSelected) {
                               isSelected = true;
                               
                               // 선택 시 selectedPlace div 태그 아래에 시간 입력 칸을 만든다.
                               var frame = document.querySelector(".frame");
                    
                               var selectedPlace = document.createElement("div");
                               selectedPlace.setAttribute("id", "selectedPlace");
                               selectedPlace.setAttribute("class", "container");
                    
                               /*var inputTime = document.createElement("input");
                               inputTime.setAttribute("type", "time");
                               inputTime.setAttribute("id", "appt");
                               inputTime.setAttribute("name", "appt");*/
        
                               
                               let body = document.querySelector('body');
                               
                               let div1 = document.createElement('div');
                               div1.setAttribute("class", "row inputtime");
                               div1.setAttribute("style", "padding: 10px;")

                               let div2 = document.createElement('div');
                               div2.setAttribute("class", "col-md-4 starttime");

                               let label = document.createElement("label");
                               label.textContent = '시작 시간';
                               
                               var inputTime = document.createElement("input");
                               inputTime.setAttribute("type", "text");
                               inputTime.setAttribute("class", "timepicker form-control");
                               inputTime.setAttribute("id", "startTime");
                               inputTime.setAttribute("style", "height: 40px; border: dotted; border-color: #E5F2FF;");
                               
                               div2.appendChild(label);
                               div2.appendChild(inputTime);
                               div1.appendChild(div2);
                               
                               let div3 = document.createElement('div');
                               div3.setAttribute("class", "col-md-4 endtime");
                               
                               let label2 = document.createElement("label");
                               label2.textContent = '끝 시간';
                               
                               var inputTime2 = document.createElement("input");
                               inputTime2.setAttribute("type", "text");
                               inputTime2.setAttribute("class", "timepicker form-control");
                               inputTime2.setAttribute("id", "endTime");
                               inputTime2.setAttribute("style", "height:40px; border: dotted; border-color: #E5F2FF;");
                               
                               div3.appendChild(label2);
                               div3.appendChild(inputTime2);
                               div1.appendChild(div3);
                               
                               selectedPlace.append(div1);
                               
                               /*let script = document.createElement('script');
                               script.src = 'js/plan/jquery.timepicker.min.js';
                               body.appendChild(script);
                               
                               let script2 = document.createElement('script');
                               script2.src = 'js/plan/timepicker.js';
                               body.appendChild(script2);*/
                               
                               // test 속성을 가지고 있는 스크립트를 모두 삭제
                               function loadScript(src, callback) {
                            	    let script = document.createElement('script');
                            	    script.src = src;
                            	    script.setAttribute("class", "test");
                            	    script.addEventListener('load', callback);
                            	    document.body.appendChild(script);
                            	}
                               
                              
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
                            
                            // 타임피커에 필요한 스크립트 로드
                            	loadScript('https://code.jquery.com/ui/1.12.1/jquery-ui.js', function () {
                            	    loadScript('js/plan/jquery.timepicker.min.js', function () {
                            	        loadScript('js/plan/timepicker.js', function() {
                            	        	
                            	        });
                            	    });
                            	});
                            	
                          
                            	
                            	
                            	
                            	// 장소를 selectedPlace에 붙여넣고 취소 버튼을 만든다.
                               var place = this.closest("#place").cloneNode(true);
                               var cancelBtn = place.querySelector('.select');
                               cancelBtn.innerText = '취소';
                               cancelBtn.style = 'background-color: #E5F2FF; border-color: #E5F2FF;';
                              
                    
                               // 첫 번째 frame 요소의 첫 번째 자식 요소 뒤에 새로운 div를 추가
                               selectedPlace.append(place);
                    
                               frame.insertBefore(selectedPlace, frame.firstElementChild.nextSibling);
                    
                               var hr = document.createElement("hr");
                               
                    
                               selectedPlace.append(hr);
                               
                               document.getElementById('places').style='height:132px';
                    
                            // 아.. 적용 버튼 이벤트 발생 시 plan에 저장한다.
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
                                  console.log(placeNo);
                                  console.log(placeName);
                                  console.log(placeCategory);
                                  console.log(placeAddr);
                                  
                                  function convertTo24HourFormat(timeString) {
                                	    const date = new Date("2000-01-01 " + timeString);
                                	    return date.getHours().toString().padStart(2, '0');
                                	}

                                	const startHour = convertTo24HourFormat(startTime);
                                	const endHour = convertTo24HourFormat(endTime);
                                	

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
                                    	
                              		    const color = response.body.color;
                              		    console.log(response.body.color);
                              		    
                              		    const selectedPlaceElement = document.querySelector('#selectedPlace');
                              		    const placeElement = selectedPlaceElement.querySelector('#place');
                              		    const firstChildOfPlace = placeElement.querySelector(':first-child');
                              		    
    	                            		 // place 요소가 있다면 조작 수행
    	                            		 if (firstChildOfPlace) {
    	                            		   console.log(firstChildOfPlace);
    	                            		   
    	                            		   if (color === "GN") {
    	                            			   console.log('yes');
    	                            			   firstChildOfPlace.setAttribute('style', 'border-color: green');
    	                            		   }
    	                            		   
    	                            		   if (color === "RD") {
    	                            			   firstChildOfPlace.setAttribute('style', 'border-color: red');
    	                            		   }
    	                            		   
    	                            		   if (color === "GY") {
    	                            			   firstChildOfPlace.setAttribute('style', 'border-color: black');
    	                            		   }
    	                            		 } else {
    	                            		   console.log('place 요소를 찾을 수 없습니다.');
    	                            		 }
                                    	
                                    	
                                    	
                                    	
                                    },
                                    error: function(jqXHR, textStatus, errorThrown) {
                                        console.log("실패했습니다.");
                                        // 오류가 발생한 경우의 동작
                                        console.log("HTTP 상태 코드: " + jqXHR.status);
                                        console.log("서버 응답: " + jqXHR.responseText);
                                    }
                                });
                           
                           
                           
                         	
                            	
                              			
                              
                          			
                          			
	                            		 
                          	
                  
                              	
                                const planDTO = {
                              		  "date": givemedate,
                              		  "startHour": startHour,
                              		  "endHour": endHour,
                              		  "place_no": placeNo
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
                                          
//                                          let script = document.createElement('script');
//                                          script.src = 'js/plan/daterangepicker.js';
//                                          let script2 = document.createElement('script');
//                                          script2.src = 'js/plan/planperiodcalculator.js'
//                                          let body = document.querySelector('body');
//                                          body.appendChild(script);
//                                          body.appendChild(script2);
                                          
                                          function loadScript(src, callback) {
                                      	    let script = document.createElement('script');
                                      	    script.src = src;
                                      	  script.setAttribute("class", "test2");
                                      	    script.addEventListener('load', callback);
                                      	    document.body.appendChild(script);
                                      	}
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
                                         
                                      
                                          // planForm에서 daterangepicker와 periodcalculator와 addblock 필요
                                          // 세션에 저장된 데이터를 보여주기 위해
                                        	 loadScript('js/plan/daterangepicker.js', function () {
                                        		 loadScript('js/plan/planperiodcalculator.js', function () {
                                        			 loadScript('js/plan/addblock.js', function() {
                                        				 
                                        			 })
                                        		 });
                                        	 });
                                       	
                                      });
                                     
                                  });
                        
                                  // cancelBtn
                               cancelBtn.addEventListener("click", function(event) {
                                   event.preventDefault();
                                   isSelected = false;
                                   this.closest("#selectedPlace").remove();
                                   document.getElementById('places').style='height:350px';
                               });
                            }
                        });
                    });
              }
              
               // area에는 지역 선택 부분이 없어도 현재 위치의 위경도가 들어가게 할것임.
               const area = document.getElementById('area').value; 
               console.log(area);
               const category = categoryBtn.value;
               const date = document.getElementById('date').value;
               const startDateString = document.getElementById('startDate').value;
               const endDateString = document.getElementById('endDate').value;
               const title = document.getElementById('title').value;
               console.log(title);
               
                fetch(`/search?area=${area}&category=${category}&startDate=${startDateString}&endDate=${endDateString}&date=${date}&title=${title}`)
                    .then(response => response.text())
                    .then(html => {
                        document.querySelector('.frame').innerHTML = html;
                        
                        addEventListeners();
                    });
         });
});  