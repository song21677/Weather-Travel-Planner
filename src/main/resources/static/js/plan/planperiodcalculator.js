$(function() {
        $('input[name="daterange"]').daterangepicker();
        
        $('input[name="daterange"]').on('change', function () {
            // 값이 변경되면 콘솔에 출력
            console.log('Value changed:', $(this).val());
            const startDate = $('input[name="daterange"]').data('daterangepicker').startDate._d;
            console.log(startDate);
            const endDate = $('input[name="daterange"]').data('daterangepicker').endDate._d;
            const timeDiff = Math.abs(endDate - startDate);
            const daysDiff = Math.ceil(timeDiff / (1000 * 60 * 60 * 24));
            console.log(`기간: ${daysDiff} 일`);
            
            const detailsElement = document.querySelector('.details');
            console.log(detailsElement);
            
            const childElements = Array.from(detailsElement.children);
           
            childElements.forEach((element) => {
                  element.remove();
              });
                        
            const date = $('input[name="daterange"]').data('daterangepicker').startDate._d;
            const nextDate = new Date(date);
           console.log(date);
           
            for (let i=1; i<=daysDiff; i++) {
               let daySchedule = document.createElement("div");
               daySchedule.setAttribute("class", "form-control2");
               
               detailsElement.append(daySchedule);
               
               let h2 = document.createElement("h2");
               h2.setAttribute("class", "h2");
               h2.innerText = "DAY " + i; 
               
               daySchedule.append(h2);
               
               let button = document.createElement("button");
               button.setAttribute("class", "form-control");
               button.setAttribute("id", "add-schedule-button");
               button.innerText = "일정 추가";
               button.type = "button"
               
               // button value
               //nextDate.setDate(nextDate.getDate() + 1);
               //button.value = nextDate.toISOString().slice(0, 10).replace(/-/g, '');
               button.value = i-1;
               const startDateString = moment(startDate).format('YYYYMMDD');
               const endDateString = moment(endDate).format('YYYYMMDD');
               console.log(startDateString);
               console.log(endDateString);
               
               button.addEventListener("click", function() {
                  console.log(button);
                  let date = button.value;
                  console.log(date);
                         fetch(`/search?date=${date}&startDate=${startDateString}&endDate=${endDateString}`)
                             .then(response => response.text())
                             .then(html => {
                                 document.querySelector('.frame').innerHTML = html;
                             });
                     });
               
               daySchedule.append(button);
               
               let br = document.createElement("br");
               detailsElement.append(br);
            }
          })
          
        var start = document.querySelector(".startDate");
        var end = document.querySelector(".endDate");
        //console.log(start);
        if (start.textContent) {
	        var startValue = moment(start.textContent, 'YYYYMMDD').format('MM/DD/YYYY');
	        var endValue = moment(end.textContent, 'YYYYMMDD').format('MM/DD/YYYY');
	        //var start2 = '03/01/2014';
	        //console.log(start2);
	        //console.log(start.textContent);
	        	//console.log(start.text);
	        	//moment(originalDateString, 'YYYYMMDD').format('DD/MM/YYYY');
	         $('input[name="daterange"]').data('daterangepicker').setStartDate(startValue);
	         $('input[name="daterange"]').data('daterangepicker').setEndDate(endValue);
        }
      });