
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

        
        //weather
    	
    const today = new Date();
    const btns = $(".dates button");

    const yyyy = today.getFullYear();
    const mm = String(today.getMonth() + 1).padStart(2, '0');
    const dd = String(today.getDate()).padStart(2, '0');
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
    
    // 임시 날씨 정보 (실제로는 API나 서버에서 데이터를 가져와야 합니다.)
    const weatherData = {
    	    [getFormattedDate(today)]: {
    	        "서울": "맑음, 20°C",
    	        "부산": "흐림, 18°C",
    	        "대구": "비, 19°C"
    	    },
    	    [getFormattedDate(new Date(today.getTime() + 24*60*60*1000))]: {  // 다음 날
    	        "서울": "흐림, 18°C",
    	        "부산": "비, 17°C",
    	        "대구": "맑음, 21°C"
    	    },
    	    [getFormattedDate(new Date(today.getTime() + 2*24*60*60*1000))]: { // 다다음 날
    	        "서울": "흐림, 18°C",
    	        "부산": "비, 17°C",
    	        "대구": "맑음, 21°C"
    	    },
    	    // ... 추가적인 날씨 데이터
    	};

    $(".locations button[data-location='서울']").addClass('active');
    let selectedLocation = "서울";
    // 초기 날씨 정보 표시
    checkAndDisplayWeather();

       

    
     



        

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
            if (selectedDate && selectedLocation) {
                const weather = weatherData[selectedDate][selectedLocation];
                $("#weatherInfo").text(`${selectedLocation}, ${selectedDate}: ${weather}`).show();
            }
        }

            const $locations = $('.locations');
            const maxSlides = Math.ceil($locations.children().length / 5) - 1;
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
                const offset = currentSlide * -300; // 10개의 버튼에 대한 넓이 (각 버튼을 약 100px로 가정)
                $locations.css('transform', `translateX(${offset}px)`);
            }
        ;
        

        function getDayOfWeek(dateString) {
            const days = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
            const date = new Date(dateString);
            return days[date.getDay()];
        }
    
    
           
        

  
        const formattedDate3 = `${yyyy}년 ${mm}월`;
        
        $("#todayDate").text(formattedDate3); // 오늘 날짜를 HTML 요소에 설정합니다.
    
        });
        
        
        //map

