var ele = document.getElementById("form");
if (ele.addEventListener) {
    ele.addEventListener("submit", callFetch, false); //Modern browsers
} else if (ele.attachEvent) {
    ele.attachEvent('onsubmit', callFetch); //Old IE
}


var req;
var result;
function callFetch() {
    const formData = new FormData(form);
    const params = new URLSearchParams(formData);
    console.log(params.toString());

    req = new XMLHttpRequest();
    req.open("GET", "/q?" + params, false);
    // req.onreadystatechange = myCode();
    req.send();
    res=req.responseText
    console.log(res)

    // function myCode() {
    //     if (req.readyState == 4) {
    //         if (req.Status == 200) {
    //             var doc = eval('(' + req.responseText + ')');
    //             result = req.response;
    //         }
    //     }
    // }
}

callFetch();

// let resJSON = JSON.parse(result);
// resJSON.


function updateSummary() {

    // document.getElementById('summary').innerHTML
}

var card_test=document.getElementById('cards')
// console.log(document)
card_test.innerHTML = 'hello'