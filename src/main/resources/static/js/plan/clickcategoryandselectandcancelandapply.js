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
                    
                               var inputTime = document.createElement("input");
                               inputTime.setAttribute("type", "time");
                               inputTime.setAttribute("id", "appt");
                               inputTime.setAttribute("name", "appt");
                    
                               selectedPlace.append(inputTime);
                    
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
                                  const time = document.getElementById('appt').value;
                                  const placeNo = selectedPlace.querySelector('.select').value;
                                  const placeName = selectedPlace.querySelector('#placeName').textContent;
                                  const placeCategory = selectedPlace.querySelector('#placeCategory').textContent;
                                  const placeAddr = selectedPlace.querySelector('#placeAddr').textContent;
                                   
                                  console.log(placeNo);
                                  console.log(placeName);
                                  console.log(placeCategory);
                                  console.log(placeAddr);
                                  
                                  const planDTO = {
                                		  "date": date,
                                		  "time": time,
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
                                          var script = document.createElement('script');
                                          script.src = 'js/plan/daterangepicker.js';
                                          var script2 = document.createElement('script');
                                          script2.src = 'js/plan/planperiodcalculator.js'
                                          var body = document.querySelector('body');
                                          body.appendChild(script);
                                          body.appendChild(script2);
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
             
                fetch(`/search?date=${date}&area=${area}&category=${category}`)
                    .then(response => response.text())
                    .then(html => {
                        document.querySelector('.frame').innerHTML = html;
                        
                        addEventListeners();
                    });
         });