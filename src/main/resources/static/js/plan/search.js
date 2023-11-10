var search = document.getElementById('search')
       console.log(search);
       search.addEventListener('keydown', function(event) {
           if (event.keyCode === 13) {
        	   const date = document.getElementById('date').value;
               const startDateString = document.getElementById('startDate').value;
               const endDateString = document.getElementById('endDate').value;
               const title = document.getElementById('title').value;
               const keyword = document.getElementById('search').value;
               
               fetch(`/search?keyword=${keyword}&startDate=${startDateString}&endDate=${endDateString}&date=${date}&title=${title}`)
               .then(response => response.text())
               .then(html => {
                   document.querySelector('.frame').innerHTML = html;
                  
                   
               });
           }
       });