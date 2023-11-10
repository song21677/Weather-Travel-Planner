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
               
               detailsElement.appendChild(daySchedule);
               
               let h2 = document.createElement("h2");
               h2.setAttribute("class", "h2");
               h2.innerText = "DAY " + i; 
               
               daySchedule.appendChild(h2);
               
               let button = document.createElement("button");
               button.setAttribute("class", "form-control");
               button.setAttribute("id", "add-schedule-button");
               button.innerText = "일정 추가";
               button.type = "button";
               
               // button value
               //nextDate.setDate(nextDate.getDate() + 1);
               //button.value = nextDate.toISOString().slice(0, 10).replace(/-/g, '');
               button.value = i-1;
               
               
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
                                 document.querySelector('.frame').innerHTML = html;
                                 
                                 function loadScript(src, callback) {
                               	    let script = document.createElement('script');
                               	    script.src = src;
                               	  script.setAttribute("class", "test3");
                               	    script.addEventListener('load', callback);
                               	    document.body.appendChild(script);
                               	}
                                 
                                 var remove = document.querySelectorAll('.test3');

                                 // 가져온 요소를 순회하면서 삭제
                                 remove.forEach(function(element) {
                                     element.remove();
                                 });
                                 
                                 loadScript('js/plan/search.js', function () {
                            	 });
                                 
                             });
                     });
               
               daySchedule.appendChild(button);
               
               let br = document.createElement("br");
               detailsElement.appendChild(br);
            }
          })
          
        var start = document.querySelector(".startDate");
        var end = document.querySelector(".endDate");
        //console.log(start);
        if (start.value) {
	        var startValue = moment(start.value, 'YYYYMMDD').format('MM/DD/YYYY');
	        var endValue = moment(end.value, 'YYYYMMDD').format('MM/DD/YYYY');
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