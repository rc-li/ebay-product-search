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
    // setData(0)
    setAllData();
}

function setData(i) {
    let doc = document.getElementById(i);
    doc.getElementsByTagName("img")[0].src = searchResult.item[i].galleryURL

    let div = doc.getElementsByTagName('div')[0]
    let a = div.getElementsByTagName('a')[0]
    a.innerHTML = searchResult.item[i].title
    a.href = searchResult.item[i].viewItemURL[0]

    let catagory = div.getElementsByClassName('category')[0]
    catagory.innerHTML = 'Category: ' + searchResult.item[i].primaryCategory[0].categoryName[0]

    let redirectIMGLink = div.getElementsByClassName('redirectIMGLink')[0]

    let condition = div.getElementsByClassName('condition')[0]
    condition.innerHTML = 'Condition: ' + searchResult.item[i].condition[0].conditionDisplayName[0]

    let accptReturn = div.getElementsByClassName('accptReturn')[0]
    let isAccepted = searchResult.item[i].returnsAccepted[0]
    if (isAccepted) {
        accptReturn.innerHTML = 'Seller accepts returns'
    }
    else {
        accptReturn.innerHTML = 'Seller does not accept returns'
    }

    let shipType = div.getElementsByClassName('shipType')[0]
    let shippingServiceCost = searchResult.item[0].shippingInfo[0].shippingServiceCost[0].__value__
    if (shippingServiceCost == 0.0) {
        shipType.innerHTML = 'Free Shipping'
    }
    else {
        shipType.innerHTML = 'No Free Shipping'
    }

    let priceTag = div.getElementsByClassName('price')[0]
    let priceValue = parseInt(searchResult.item[0].sellingStatus[0].convertedCurrentPrice[0].__value__)
    let shipFrom = searchResult.item[i].location[0]
    if (shippingServiceCost > 0) {
        priceTag.innerHTML = 'Price: $' + priceValue + ' (+ $' + shippingServiceCost + ' for shipping)'
    }
    else {
        priceTag.innerHTML = 'Price: $' + priceValue
    }

    let locTag = div.getElementsByClassName('priceTag_loc')[0]
    locTag.innerHTML = '<i>From ' + shipFrom + '</i>'

}

function setAllData() {
    for (var i = 0; i < 3; i++) {
        setData(i);
    }
}

function updateSummary() {
    document.getElementById('summary').innerHTML = 'Found ' + num_entries + ' entries'
}
