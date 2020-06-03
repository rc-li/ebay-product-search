var ele = document.getElementById("form");
if (ele.addEventListener) {
    ele.addEventListener("submit", callFetch, false); //Modern browsers
} else if (ele.attachEvent) {
    ele.attachEvent('onsubmit', callFetch); //Old IE
}

var json;
function getJSON(response) {
    json = response.json()
}

var req;
function callFetch() {
    const formData = new FormData(form);
    const params = new URLSearchParams(formData);
    console.log(params.toString());

    req = new XMLHttpRequest();
    req.open("GET", "/q?" + params, true);
    req.onreadystatechange = myCode;
    req.send(null);

    function myCode() {
        if (req.readyState == 4) {
            if (req.Status == 200) {
                var doc = eval('(' + req.responseText + ')');
            }
        }
    }
}

var card_test=document.getElementById('cards')
// console.log(document)
card_test.innerHTML = 'hello'