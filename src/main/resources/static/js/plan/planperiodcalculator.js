$(function() {
        $('input[name="daterange"]').daterangepicker();
        
        $('input[name="daterange"]').on('change', function () {
            // 값이 변경되면 콘솔에 출력
            console.log('Value changed:', $(this).val());
            console.log($('input[name="daterange"]').data('daterangepicker').startDate._d);
            const startDate = $('input[name="daterange"]').data('daterangepicker').startDate._d;
            const endDate = $('input[name="daterange"]').data('daterangepicker').endDate._d;
            const timeDiff = Math.abs(endDate - startDate);
            console.log(timeDiff);
            const daysDiff = Math.ceil(timeDiff / (1000 * 60 * 60 * 24));
            console.log(`기간: ${daysDiff} 일`);
            
            const detailsElement = document.querySelector('.details');
            console.log(detailsElement);
            
            const childElements = Array.from(detailsElement.children);
           
            childElements.forEach((element) => {
                  element.remove();
              });
                        
            const date = $('input[name="daterange"]').data('daterangepicker').startDate._d;
           for (let i=1; i<=daysDiff; i++) {
               var daySchedule = document.createElement("div");
               daySchedule.setAttribute("class", "form-control2");
               
               detailsElement.append(daySchedule);
               
               var h2 = document.createElement("h2");
               h2.setAttribute("class", "h2");
               h2.innerText = "DAY " + i; 
               
               daySchedule.append(h2);
               
               var button = document.createElement("button");
               button.setAttribute("class", "form-control");
               button.setAttribute("id", "add-schedule-button");
               button.innerText = "일정 추가";
               button.type = "button"
               
               // button value
               date.setDate(date.getDate() + 1);
               button.value = date.toISOString().slice(0, 10).replace(/-/g, '');
               
               button.addEventListener("click", function() {
                  var button = document.getElementById('add-schedule-button');
                  console.log(button);
                  var date = button.value;
                  console.log(date);
                         fetch(`/search?date=${date}`)
                             .then(response => response.text())
                             .then(html => {
                                 document.querySelector('.frame').innerHTML = html;
                             });
                     });
               
               daySchedule.append(button);
               
               var br = document.createElement("br");
               detailsElement.append(br);
            }
          })
          
          var start = document.querySelector(".startDate");
        var start3 = moment(start.textContent, 'YYYYMMDD').format('MM/DD/YYYY');
        var start2 = '03/01/2014';
        console.log(start2);
        console.log(start.textContent);
        	//console.log(start.text);
        	//moment(originalDateString, 'YYYYMMDD').format('DD/MM/YYYY');
         $('input[name="daterange"]').data('daterangepicker').setStartDate(start3);
      });