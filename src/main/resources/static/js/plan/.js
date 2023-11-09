 $(function() {
        $('input[name="daterange"]').daterangepicker({
          opens: 'left'
        }, function(start, end, label) {
          const startDate = new Date(start.format('YYYY-MM-DD'));
          console.log(startDate);
          const endDate = new Date(end.format('YYYY-MM-DD'));
          // 날짜 차이를 계산
          const timeDiff = Math.abs(endDate - startDate);

          // 날짜 차이를 밀리초(ms)에서 일(Day)로 변환
          const daysDiff = Math.ceil(timeDiff / (1000 * 60 * 60 * 24)) + 1;

          console.log(`기간: ${daysDiff} 일`);
          
          const detailsElement = document.querySelector('.details');
          console.log(detailsElement);
          
          const childElements = Array.from(detailsElement.children);
          
          console.log(childElements);
         
          childElements.forEach((element) => {
                element.remove();
            });
          
          const date = new Date(start.format('YYYY-MM-DD'));
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
        });
      });