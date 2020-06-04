var ele = document.getElementById("form");
if (ele.addEventListener) {
    ele.addEventListener("submit", callFetch, false); //Modern browsers
} else if (ele.attachEvent) {
    ele.attachEvent('onsubmit', callFetch); //Old IE
}


var result;
var num_entries;
var params;
function callFetch() {
    const formData = new FormData(form);
    params = new URLSearchParams(formData);
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
    setAllData();
}

function setData(i) {
    // set card image
    let doc = document.getElementById(i);
    doc.getElementsByTagName("img")[0].src = searchResult.item[i].galleryURL

    // set the product link
    let div = doc.getElementsByTagName('div')[0]
    let a = div.getElementsByTagName('a')[0]
    a.innerHTML = searchResult.item[i].title
    a.href = searchResult.item[i].viewItemURL[0]

    // set the category
    let catagory = div.getElementsByClassName('category')[0]
    catagory.innerHTML = 'Category: ' + searchResult.item[i].primaryCategory[0].categoryName[0]

    // set the redirect image link
    let redirectIMGLink = div.getElementsByClassName('redirectIMGLink')[0]
    redirectIMGLink.href = searchResult.item[i].viewItemURL[0]

    // set the product condition and top rated image visibility
    let condition = div.getElementsByClassName('condition')[0]
    condition.innerHTML = 'Condition: ' + searchResult.item[i].condition[0].conditionDisplayName[0]
    let topRatedIMG = div.getElementsByClassName('topRatedIMG')[0]
    let isTopRated = searchResult.item[i].topRatedListing[0]
    if (isTopRated == 'true') {
        topRatedIMG.style.visibility = 'visible'
    }
    else {
        topRatedIMG.style.visibility = 'hidden'
    }

    // set if return is accepted
    let accptReturn = div.getElementsByClassName('accptReturn')[0]
    let isAccepted = searchResult.item[i].returnsAccepted[0]
    if (isAccepted) {
        accptReturn.innerHTML = 'Seller accepts returns'
    }
    else {
        accptReturn.innerHTML = 'Seller does not accept returns'
    }

    // set shipping type
    let shipType = div.getElementsByClassName('shipType')[0]
    let shippingServiceCost = searchResult.item[0].shippingInfo[0].shippingServiceCost[0].__value__
    if (shippingServiceCost == 0.0) {
        shipType.innerHTML = 'Free Shipping'
    }
    else {
        shipType.innerHTML = 'No Free Shipping'
    }

    // set price
    let priceTag = div.getElementsByClassName('price')[0]
    let priceValue = parseInt(searchResult.item[0].sellingStatus[0].convertedCurrentPrice[0].__value__)
    let shipFrom = searchResult.item[i].location[0]
    if (shippingServiceCost > 0) {
        priceTag.innerHTML = 'Price: $' + priceValue + ' (+ $' + shippingServiceCost + ' for shipping)'
    }
    else {
        priceTag.innerHTML = 'Price: $' + priceValue
    }

    // set ship from
    let locTag = div.getElementsByClassName('priceTag_loc')[0]
    locTag.innerHTML = '<i>From ' + shipFrom + '</i>'

}

function setAllData() {
    let summary = document.getElementById('summary')
    summary.style.display = 'block'
    let card = document.getElementById('cards')
    card.style.display = 'block'
    for (var i = 0; i < 3; i++) {
        setData(i);
    }
}

function updateSummary() {
    let keyword = params.toString().split('&')[0].split('=')[1]
    document.getElementById('summary').innerHTML = num_entries + ' Results found for <i>' + keyword + '</i><hr>'
}
