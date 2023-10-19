


//recommend

        $(document).ready(function() {
        function slideList() {
        const $slider = $('.slider');
        const $firstItem = $slider.find('li:first');

        $firstItem.animate({ marginLeft: '-410px' }, 5000,'linear', function() {
            $(this).detach().appendTo($slider).removeAttr('style');
            slideList();
        });
    }
    slideList();  // 2초마다 항목을 슬라이드
    
    
        const defaultImages = [
        	"images/default/default1.jpg",
        	"images/default/default2.jpg",
        	"images/default/default3.jpg",
        	"images/default/default4.jpg",
        	"images/default/default5.jpg",
        	"images/default/default6.jpg",
        	"images/default/default7.jpg",
        	"images/default/default8.jpg",
        	"images/default/default9.jpg",
        	"images/default/default10.jpg",
        ];

        $(".slider-wrapper .slider li .userImg").each(function() {
            const $currentImage = $(this);

            if (!$currentImage.attr("src") || $currentImage.attr("src") === "#") {
                const randomIndex = Math.floor(Math.random() * defaultImages.length);
                const randomImage = defaultImages[randomIndex];
                $currentImage.attr("src", randomImage);
                $currentImage.addClass("default-image");
            }
        });
        $(document).ready(function() {
            $('.default-image').each(function() {
                $('<img>', {
                    'src': 'images/waterMark.png', // 워터마크 이미지의 경로를 설정하세요.
                    'class': 'watermark'
                }).appendTo($(this).parent());
            });
        });
        
       

       

    
    

        
        //weather
    	
    const today = new Date();
    const btns = $(".dates button");
    const yyyy = today.getFullYear();
    const mm = String(today.getMonth() + 1).padStart(2, '0');
    const dd = String(today.getDate()).padStart(2, '0');
    const hours = String(today.getHours()).padStart(2, '0');
    const minutes = String(today.getMinutes()).padStart(2, '0');
    const seconds = String(today.getSeconds()).padStart(2, '0');
    
    const formattedToday = `${yyyy}-${mm}-${dd}`;

    // 오늘 날짜를 기본값으로 설정
    let selectedDate = formattedToday;

    btns.each(function(index) {
        const newDate = new Date(today);
        newDate.setDate(today.getDate() + index);
        
        const yyyy = newDate.getFullYear();
        const mm = String(newDate.getMonth() + 1).padStart(2, '0');
        const dd = String(newDate.getDate()).padStart(2, '0');
        
        const formattedDate = `${yyyy}-${mm}-${dd}`;
        
        $(this).attr('data-date', formattedDate);
        $(this).text(`${dd}일 (${getDayOfWeek(formattedDate).substring(0, 1)})`);

        // 오늘 날짜에 해당하는 버튼을 활성화 상태로 만들기
        if (formattedDate === formattedToday) {
            $(this).addClass('active');
        }
    });
    function getFormattedDate(date) {
        return date.toISOString().split('T')[0];
    }
    
    // 임시 날씨 정보
    const weatherData = {
    	    "서울": {
    	        [getFormattedDate(today)]: {
    	            "09:00": { weather: "맑음", temp: "20°C", rainfall: "0mm", humidity: "60%" },
    	            "10:00": { weather: "흐림", temp: "21°C", rainfall: "0mm", humidity: "62%" },
    	            "12:00": { weather: "흐림", temp: "21°C", rainfall: "0mm", humidity: "62%" },
    	            "13:00": { weather: "흐림", temp: "21°C", rainfall: "0mm", humidity: "62%" },
    	            "14:00": { weather: "흐림", temp: "21°C", rainfall: "0mm", humidity: "62%" },
    	            "15:00": { weather: "흐림", temp: "21°C", rainfall: "0mm", humidity: "62%" },
    	        },
    	        // ... 다른 날짜에 대한 정보
    	    },
    	    "부산": {
    	        [getFormattedDate(today)]: {
    	            "09:00": { weather: "맑음", temp: "20°C", rainfall: "0mm", humidity: "60%" },
    	            "10:00": { weather: "흐림", temp: "21°C", rainfall: "0mm", humidity: "62%" },
    	            "12:00": { weather: "흐림", temp: "21°C", rainfall: "0mm", humidity: "62%" },
    	            "13:00": { weather: "흐림", temp: "21°C", rainfall: "0mm", humidity: "62%" },
    	            "14:00": { weather: "흐림", temp: "21°C", rainfall: "0mm", humidity: "62%" },
    	            "15:00": { weather: "흐림", temp: "21°C", rainfall: "0mm", humidity: "62%" },
    	            "16:00": { weather: "흐림", temp: "21°C", rainfall: "0mm", humidity: "62%" },
    	            "17:00": { weather: "흐림", temp: "21°C", rainfall: "0mm", humidity: "62%" },
    	            "18:00": { weather: "흐림", temp: "21°C", rainfall: "0mm", humidity: "62%" },
    	            "19:00": { weather: "흐림", temp: "21°C", rainfall: "0mm", humidity: "62%" },
    	            "20:00": { weather: "흐림", temp: "21°C", rainfall: "0mm", humidity: "62%" },
    	            "21:00": { weather: "흐림", temp: "21°C", rainfall: "0mm", humidity: "62%" },
    	        },
    	        // ... 다른 날짜에 대한 정보
    	    },
    	    // ... 다른 지역의 날씨 정보
    	    
    	};

    
    $(".locations button[data-location='서울']").addClass('active');
    let selectedLocation = "서울";
    // 초기 날씨 정보 표시
    checkAndDisplayWeather();

    function updateWeatherTable(weatherForDate) {
        const tbody = $("table tbody");
        tbody.empty();

        let hasFutureData = false; // 미래의 데이터가 있는지 확인하는 플래그

        if (weatherForDate) {
            for (const time in weatherForDate) {
                const rowTime = time.split(':');
                const rowHours = parseInt(rowTime[0], 10);
                const rowMinutes = parseInt(rowTime[1], 10);

                // 현재 시간보다 미래의 행만 추가합니다.
                if (rowHours >= hours || (rowHours === hours && rowMinutes > minutes)) {
                    const { weather, temp, rainfall, humidity } = weatherForDate[time];
                    const row = `
                        <tr>
                            <td>${time}</td>
                            <td>${weather}</td>
                            <td>${temp}</td>
                            <td>${rainfall}</td>
                            <td>${humidity}</td>
                        </tr>
                    `;
                    tbody.append(row);
                    hasFutureData = true;
                }
            }

            // 만약 미래의 데이터가 없다면, 알림 행을 추가합니다.
            if (!hasFutureData) {
                const row = `
                    <tr>
                        <td colspan="5">현재 시간 이후의 날씨 데이터가 없습니다.</td>
                    </tr>
                `;
                tbody.append(row);
            }
        } else {
            const row = `
                <tr>
                    <td colspan="5">해당 값이 없습니다.</td>
                </tr>
            `;
            tbody.append(row);
        }
    }

    
     



        

     // 날짜 선택
        $(".dates button").on('click', function() {
            $(".dates button").removeClass('active');  // 모든 날짜 버튼의 active 클래스 제거
            $(this).addClass('active');                // 현재 선택된 버튼에만 active 클래스 추가
            selectedDate = $(this).data('date');
            checkAndDisplayWeather();
        });

        // 지역 선택
        $(".locations button").on('click', function() {
            $(".locations button").removeClass('active');  // 모든 지역 버튼의 active 클래스 제거
            $(this).addClass('active');                    // 현재 선택된 버튼에만 active 클래스 추가
            selectedLocation = $(this).data('location');
            checkAndDisplayWeather();
        });

        function checkAndDisplayWeather() {
            const weatherForLocation = weatherData[selectedLocation] || {};
            const weatherForDate = weatherForLocation[selectedDate];

            if (selectedDate && selectedLocation) {
                updateWeatherTable(weatherForDate);
            }
        }

        function getDayOfWeek(dateString) {
            const days = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
            const date = new Date(dateString);
            return days[date.getDay()];
        }
  
        const formattedDate2 = `(${mm}. ${dd}) ${hours}시`;
        const formattedDate3 = `${yyyy}. ${mm}`;
        $("#todayDate").text(formattedDate3); // 오늘 날짜를 HTML 요소에 설정합니다.
        $("#dateOutput2").text(formattedDate2); // 오늘 날짜를 HTML 요소에 설정합니다.
    
        
        
        
        
        //버튼
        
        
            const $locations = $('.locations');
            const buttonsPerSlide = 5;
            const totalButtons = $locations.children().length;
            const maxSlides = Math.ceil(totalButtons / buttonsPerSlide) - 2;
            let currentSlide = 0;

            $('#nextSlide').on('click', function() {
                if (currentSlide < maxSlides) {
                 currentSlide++;
            } else {
                // 끝 지점에서 오른쪽 화살표를 눌렀을 때 원위치로
                currentSlide = 0;
            }
        updateSlide();
            });

            $('#prevSlide').on('click', function() {
                if (currentSlide > 0) {
                    currentSlide--;
                }
                else{
                    currentSlide = maxSlides;
                }
                updateSlide();
            });

            function updateSlide() {
                const offset = 200 + currentSlide * -210;  // 버튼의 너비와 마진을 고려한 이동 간격
                $locations.css('transform', `translateX(${offset}px)`);
            };
            
            
        


        
        
        
        //map
        
        navigator.geolocation.getCurrentPosition(function(position) {
            const lat = position.coords.latitude;
            const lng = position.coords.longitude;
            // 여기서 lat, lng 값으로 네이버 지도 API 호출
        }, function(error) {
            console.error("Error occurred: " + error.message);
        });
        
        
        });

