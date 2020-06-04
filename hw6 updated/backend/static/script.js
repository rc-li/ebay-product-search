var ele = document.getElementById("form");
if (ele.addEventListener) {
    ele.addEventListener("submit", callFetch, false); //Modern browsers
} else if (ele.attachEvent) {
    ele.attachEvent('onsubmit', callFetch); //Old IE
}


var result;
var num_entries;
function callFetch() {
    const formData = new FormData(form);
    const params = new URLSearchParams(formData);
    console.log(params.toString());

    req = new XMLHttpRequest();
    req.open("GET", "/q?" + params, false);
    // req.onreadystatechange = myCode();
    req.send();
    res = req.responseText
    obj = JSON.parse(res)
    num_entries = obj.findItemsAdvancedResponse[0].paginationOutput[0].totalEntries[0]
    searchResult = obj.findItemsAdvancedResponse[0].searchResult[0];
    updateSummary();
}

// let resJSON = JSON.parse(result);
// resJSON.


function updateSummary() {
    document.getElementById('summary').innerHTML = 'Found ' + num_entries + ' entries'
}
