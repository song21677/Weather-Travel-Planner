var start = document.querySelector(".startDate");
        // var start3 = moment(start.textContent, 'YYYYMMDD').format('MM/DD/YYYY');
        var start2 = '03/01/2014';
        console.log(start2);
        console.log(start.textContent);
        	//console.log(start.text);
        	//moment(originalDateString, 'YYYYMMDD').format('DD/MM/YYYY');
         $('input[name="daterange"]').data('daterangepicker').setStartDate(start3);