// class가 date이고, textContent가 button의 value와 같은 요소 찾기

if (typeof yourVariable !== 'undefined') {
    let dateElements = document.querySelectorAll('.date');
}
else {
    dateElements = document.querySelectorAll('.date');
}

console.log(dateElements);

dateElements.forEach(function (dateElement) {
    let date = dateElement.value;
    console.log(date);
    
    let addButtonList = document.querySelectorAll('#add-schedule-button');
    console.log(addButtonList);
    addButtonList.forEach(function (addButton) {
	if (addButton.value === date) {
	    // 찾은 요소 바로 뒤에 새로운 div 추가
	    addButton.parentNode.insertBefore(dateElement.parentNode, addButton);
	    return;
	}
  });
});

