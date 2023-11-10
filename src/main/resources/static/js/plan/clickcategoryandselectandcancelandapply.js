var categoryBtn = document.getElementById('category');
       
       categoryBtn.addEventListener('click', function() {
              
              function addEventListeners() {
                 var isSelected = false;
                    
                 // selectBtn
                    document.querySelectorAll(".select").forEach(function(button) {
                        button.addEventListener("click", function(event) {

                            if (!isSelected) {
                               isSelected = true;
                               
                               var frame = document.querySelector(".frame");
                    
                               var selectedPlace = document.createElement("div");
                               selectedPlace.setAttribute("id", "selectedPlace");
                    
                               /*var inputTime = document.createElement("input");
                               inputTime.setAttribute("type", "time");
                               inputTime.setAttribute("id", "appt");
                               inputTime.setAttribute("name", "appt");*/
                               
                               var inputTime = document.createElement("input");
                               inputTime.setAttribute("type", "text");
                               inputTime.setAttribute("class", "timepicker");
                               inputTime.setAttribute("id", "startTime");
                               
                               let body = document.querySelector('body');
                               
                               var inputTime2 = document.createElement("input");
                               inputTime2.setAttribute("type", "text");
                               inputTime2.setAttribute("class", "timepicker");
                               inputTime2.setAttribute("id", "endTime");
                               
                               selectedPlace.append(inputTime);
                               selectedPlace.append(inputTime2);
                               
                               /*let script = document.createElement('script');
                               script.src = 'js/plan/jquery.timepicker.min.js';
                               body.appendChild(script);
                               
                               let script2 = document.createElement('script');
                               script2.src = 'js/plan/timepicker.js';
                               body.appendChild(script2);*/
                               
                               function loadScript(src, callback) {
                            	    let script = document.createElement('script');
                            	    script.src = src;
                            	    script.setAttribute("class", "test");
                            	    script.addEventListener('load', callback);
                            	    document.body.appendChild(script);
                            	}
                               //https://code.jquery.com/ui/1.12.1/jquery-ui.js
                            	// 첫 번째 스크립트 로드 후 두 번째 스크립트 로드
                              
                               var elementsToRemove = document.querySelectorAll('.test');

                            // 가져온 요소를 순회하면서 삭제
                            elementsToRemove.forEach(function(element) {
                                element.remove();
                            });
                            
                            var gg = document.querySelector('.ui-timepicker-container');
                            
                            if (gg) {
                            gg.remove();
                            }
                            
                            	loadScript('https://code.jquery.com/ui/1.12.1/jquery-ui.js', function () {
                            	    loadScript('js/plan/jquery.timepicker.min.js', function () {
                            	        loadScript('js/plan/timepicker.js', function() {
                            	        	
                            	        });
                            	    });
                            	});
                              
                    
                               var place = this.closest("#place").cloneNode(true);
                               var cancelBtn = place.querySelector('.select');
                               cancelBtn.innerText = '취소';
                    
                               // 첫 번째 frame 요소의 첫 번째 자식 요소 뒤에 새로운 div를 추가
                               selectedPlace.append(place);
                    
                               frame.insertBefore(selectedPlace, frame.firstElementChild.nextSibling);
                    
                               var hr = document.createElement("hr");
                    
                               selectedPlace.append(hr);
                    
                            // applyBtn
                              var button = document.getElementById('applyBtn');
                            
                               button.addEventListener('click', function() {
                                  const date = document.getElementById('date').value;
                                  const startTime = document.getElementById('startTime').value;
                                  const endTime = document.getElementById('endTime').value;
                                  const placeNo = selectedPlace.querySelector('.select').value;
                                  const placeName = selectedPlace.querySelector('#placeName').textContent;
                                  const placeCategory = selectedPlace.querySelector('#placeCategory').textContent;
                                  const placeAddr = selectedPlace.querySelector('#placeAddr').textContent;
                                   
                                  console.log(placeNo);
                                  console.log(placeName);
                                  console.log(placeCategory);
                                  console.log(placeAddr);
                                  
                                  function convertTo24HourFormat(timeString) {
                                	    const date = new Date("2000-01-01 " + timeString);
                                	    return date.getHours();
                                	}

                                	const startHour = convertTo24HourFormat(startTime);
                                	const endHour = convertTo24HourFormat(endTime);

                                	console.log(startHour);
                                	console.log(endHour); 
                                	
                                  const planDTO = {
                                		  "date": date,
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
                                      	    script.addEventListener('load', callback);
                                      	    document.body.appendChild(script);
                                      	}
                                         //https://code.jquery.com/ui/1.12.1/jquery-ui.js
                                      	// 첫 번째 스크립트 로드 후 두 번째 스크립트 로드
                                      
                                        	 loadScript('js/plan/daterangepicker.js', function () {
                                        		 loadScript('js/plan/planperiodcalculator.js', function () {
                                      	       
                                        		 });
                                        	 });
                                       
                                         
                                      });
                                     
                                  });
                        
                                  // cancelBtn
                               cancelBtn.addEventListener("click", function(event) {
                                   event.preventDefault();
                                   isSelected = false;
                                   this.closest("#selectedPlace").remove();
                               });
                            }
                        });
                    });
              }
              
               // area에는 지역 선택 부분이 없어도 현재 위치의 위경도가 들어가게 할것임.
               const area = document.getElementById('area').value;   
               const category = categoryBtn.value;
               const date = document.getElementById('date').value;
               const startDateString = document.getElementById('startDate').value;
               const endDateString = document.getElementById('endDate').value;
               
                fetch(`/search?date=${date}&area=${area}&category=${category}&startDate=${startDateString}&endDate=${endDateString}`)
                    .then(response => response.text())
                    .then(html => {
                        document.querySelector('.frame').innerHTML = html;
                        
                        addEventListeners();
                    });
         });