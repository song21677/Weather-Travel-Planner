


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
            $('.default-image').each(function() {
                $('<img>', {
                    'src': 'images/waterMark.png', // 워터마크 이미지의 경로를 설정하세요.
                    'class': 'watermark'
                }).appendTo($(this).parent());
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
    
    
    
    //////////////////////단기기온
    
    function mapSkyToWeather(skyCode) {
        switch (skyCode) {
            case "1":
                return "맑음";
            case "3":
                return "구름많음";
            case "4":
                return "흐림";
            default:
                return "알 수 없음";
        }
    }
    
    function mapPtyToSky(ptyCode){
    	switch (ptyCode) {
        case 1:
        case 2:
            return "비"; 
            break;
        case 3:
            return "눈";
            break;
        case 4:
            return"소나기";
            break;
        default:
            break;
    }

    }
    
    var currentDate = new Date(); // 현재 날짜의 날짜 객체 생성
    var hour123 = currentDate.getHours();
    let formattedHours1 = (hour123 < 10 ? '0' : '') + hour123;
    var currentTime = formattedHours1+":00";
    if(currentDate.getHours())
    var threeDaysLater = new Date();
    threeDaysLater.setDate(currentDate.getDate() + 2);
    
    
    const locationMap = {
    		203:"서울",
     	    229:"부산",
     	    246:"대구",
     	    255:"인천",
     	    265:"광주",
     	    271:"대전",
     	    277:"울산",
     	    283:"세종",
     	    284:"경기",
     	    326:"충북",
     	    341:"충남",
     	    358:"전북",
     	    374:"전남",
     	    397:"경북",
     	    422:"경남",
     	    421:"울릉",
     	    448:"강원",
     	    445:"제주",
    	};
    
    var weatherListElement = $("#weatherList");
    var weatherList = JSON.parse(weatherListElement.text());
    var weatherData = {};

    $.each(weatherList, function(index, item) {
        var second_PRECINCT_NO = item.second_PRECINCT_NO;
        var location = locationMap[second_PRECINCT_NO];
        var forecast_DAY = item.forecast_DAY.replace(/(\d{4})(\d{2})(\d{2})/, '$1-$2-$3');
        var tmp = item.tmp;
        var reh = item.reh;
        var pcp = item.pcp;
        var pty = item.pty;
        var sky = mapSkyToWeather(item.sky);
        var forecast_TIME = item.forecast_TIME.replace(/(\d{2})(\d{2})/, '$1:$2');;
        
        
        // 날씨 정보를 업데이트
        if (pty !== 0 && (pty === 1 || pty === 2)) {
            sky = mapPtyToSky(pty); // 강수형태가 1 또는 2인 경우에만 업데이트
        }else if (pcp!=="강수없음" && tmp>0) {
            sky = "비"; // 강수량이 있는 경우 "비"로 설정
        } 

        // 만들어진 데이터를 weatherData 객체에 추가
        if (!weatherData[location]) {
            weatherData[location] = {};
        }
        
        if (!weatherData[location][forecast_DAY]) {
            weatherData[location][forecast_DAY] = {};
        }
        
        
       
   

        weatherData[location][forecast_DAY][forecast_TIME] = {
            weather: sky, // "weather" 필드는 "sky" 필드로 설정
            temp: tmp,
            rainfall: pcp,
            humidity: reh
        };
       
    });
  
    
 // forecast_TIME을 기준으로 데이터를 정렬
    $.each(weatherData, function(location, locationData) {
        $.each(locationData, function(forecastDay, forecastData) {
            var sortedForecastData = {};
            Object.keys(forecastData).sort().forEach(function(time) {
                sortedForecastData[time] = forecastData[time];
            });
            locationData[forecastDay] = sortedForecastData;
        });
    });

    
    
///////////////////////중기기온
    
    var weatherListElement2 = $("#weatherList2");
    var weatherList2 = JSON.parse(weatherListElement2.text());
    

    $.each(weatherList2, function(index, item) {
        var second_PRECINCT_NO2 = item.second_PRECINCT_NO;
        var location2 = locationMap[second_PRECINCT_NO2];
        var forecast_DAY2 = item.forecast_DAY.replace(/(\d{4})(\d{2})(\d{2})/, '$1-$2-$3');
        var wfam = item.wfam;
        var wfpm = item.wfpm;
        var tamin = item.tamin;
        var tamax = item.tamax;
        var rnstam = item.rnstam;
        var rnstpm = item.rnstpm;
      

        // "오전" 예보를 저장
        var forecastTimeMorning = "오전";
        if (!weatherData[location2]) {
            weatherData[location2] = {};
        }
        if (!weatherData[location2][forecast_DAY2]) {
            weatherData[location2][forecast_DAY2] = {};
        }
        weatherData[location2][forecast_DAY2][forecastTimeMorning] = {
            time: forecast_DAY2,
            weather: wfam,
            temp: tamin,
            rainfall: rnstam,
            humidity: "확인 불가"
        };

        // "오후" 예보를 저장
        var forecastTimeAfternoon = "오후";
        if (!weatherData[location2]) {
            weatherData[location2] = {};
        }
        if (!weatherData[location2][forecast_DAY2]) {
            weatherData[location2][forecast_DAY2] = {};
        }
        weatherData[location2][forecast_DAY2][forecastTimeAfternoon] = {
            time: forecast_DAY2,
            weather: wfpm,
            temp: tamax,
            rainfall: rnstpm,
            humidity: "확인 불가"
        };
       
    });
    
    
    
    
    
    $(".locations button[data-location='서울']").addClass('active');
    let selectedLocation = "서울";
    // 초기 날씨 정보 표시
    checkAndDisplayWeather();

    function updateWeatherTable(weatherForDate) {
        const tbody = $("table tbody");
        tbody.empty();
        
        let maxTemp = -Infinity;
        let maxHumidity = -Infinity;
        let minTemp = Infinity;
        let minHumidity = Infinity;

        let hasFutureData = false; // 미래의 데이터가 있는지 확인하는 플래그

        if (weatherForDate) {
            for (const time in weatherForDate) {
                const rowTime = time.split(':');
                const rowHours = parseInt(rowTime[0], 10);
                const rowMinutes = parseInt(rowTime[1], 10);
                const rowDate = new Date(selectedDate); // 선택한 날짜의 날짜 객체 생성

                // 현재 시간보다 미래의 행만 추가합니다.
                if (rowDate <= threeDaysLater) {
                        const { weather, temp, rainfall, humidity } = weatherForDate[time];
                        const row = `
                            <tr>
                                <td>${time}</td>
                                <td>${weather}</td>
                                <td>${temp}°C</td>
                                <td>${rainfall}</td>
                                <td>${humidity}%</td>
                            </tr>
                        `;
                        tbody.append(row);
                        
                        maxTemp = Math.max(maxTemp, temp);
                        maxHumidity = Math.max(maxHumidity, humidity);
                        minTemp = Math.min(minTemp, temp);
                        minHumidity = Math.min(minHumidity, humidity);
                        
                        hasFutureData = true;
                        
                        if (rainfall !== '강수없음') {
                            $("table tbody tr td:contains('" + rainfall + "')").closest("tr").addClass("rainfall");
                        }
                        
                        if (time === currentTime && currentDate.getDate() === rowDate.getDate()) {
                            $("table tbody tr td:contains('" + currentTime + "')").closest("tr").addClass("currentTime");
                        }
                        
                    
                    
                    
                    
                } else {
                    const { weather, temp, rainfall, humidity } = weatherForDate[time];
                    const row = `
                        <tr>
                            <td>${time}</td>
                            <td>${weather}</td>
                            <td>${temp}°C</td>
                            <td>${rainfall}%</td>
                            <td>${humidity}</td>
                        </tr>
                    `;
                    tbody.append(row);
                    hasFutureData = true;
                }
        }
        }
        
     // 최대값에 해당하는 클래스 추가

        if (maxHumidity !== -Infinity) {
            var maxHumidityValue = maxHumidity + "%";
            $("table tbody tr td").filter(function() {
                return $(this).text() === maxHumidityValue;
            }).closest("tr").addClass("maxHumidity");
        }
        
        
        
        if (maxTemp !== -Infinity) {
            var maxTempValue = maxTemp + "°C";
            $("table tbody tr td").filter(function() {
                return $(this).text() === maxTempValue;
            }).closest("tr").addClass("maxTemp");
        }
        
        
        
        
        if (minTemp !== Infinity) {
            var minTempValue = minTemp + "°C";
            $("table tbody tr td").filter(function() {
                return $(this).text() === minTempValue;
            }).closest("tr").addClass("minTemp");
        }
        
        if (minHumidity !== Infinity) {
            var minHumidityValue = minHumidity + "%";
            $("table tbody tr td").filter(function() {
                return $(this).text() === minHumidityValue;
            }).closest("tr").addClass("minHumidity");
        }


        

        

            // 만약 미래의 데이터가 없다면, 알림 행을 추가합니다.
            if (!hasFutureData) {
                const row = `
                    <tr>
                        <td colspan="5">현재 시간 이후의 날씨 데이터가 없습니다.</td>
                    </tr>
                `;
                tbody.append(row);
        } else {
            const row = `
                <tr>
                    <td colspan="5">하루 끝 :)</td>
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
            console.log("Selected location's data:", weatherData[selectedLocation]);
            console.log("Selected date's data for location:", weatherData[selectedLocation][selectedDate]);

            if (selectedDate && selectedLocation) {
                updateWeatherTable(weatherForDate);
            }
        }

        function getDayOfWeek(dateString) {
            const days = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
            const date = new Date(dateString);
            return days[date.getDay()];
        }
  
        const formattedDate2 = `${mm}월 ${dd}일 ${hours}시`;
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
                const offset = 100 + currentSlide * -600;  // 버튼의 너비와 마진을 고려한 이동 간격
                $locations.css('transform', `translateX(${offset}px)`);
            };
            
            
        


        
        
        
              
        
        });

