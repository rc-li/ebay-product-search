var result;
var num_entries;
var params;
var searchResult;
var obj;
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

function setData(cardNum, itemNum) {
    // set card image
    let doc = document.getElementById(cardNum);
    doc.getElementsByTagName("img")[0].src = searchResult.item[itemNum].galleryURL

    // set the product link
    let div = doc.getElementsByTagName('div')[0]
    let a = div.getElementsByTagName('a')[0]
    a.innerHTML = searchResult.item[itemNum].title
    a.href = searchResult.item[itemNum].viewItemURL[0]

    // set the category
    let catagory = div.getElementsByClassName('category')[0]
    catagory.innerHTML = 'Category: ' + searchResult.item[itemNum].primaryCategory[0].categoryName[0]

    // set the redirect image link
    let redirectIMGLink = div.getElementsByClassName('redirectIMGLink')[0]
    redirectIMGLink.href = searchResult.item[itemNum].viewItemURL[0]

    // set the product condition and top rated image visibility
    let condition = div.getElementsByClassName('condition')[0]
    condition.innerHTML = 'Condition: ' + searchResult.item[itemNum].condition[0].conditionDisplayName[0]
    let topRatedIMG = div.getElementsByClassName('topRatedIMG')[0]
    let isTopRated = searchResult.item[itemNum].topRatedListing[0]
    if (isTopRated == 'true') {
        topRatedIMG.style.visibility = 'visible'
    }
    else {
        topRatedIMG.style.visibility = 'hidden'
    }

    // set if return is accepted
    let accptReturn = div.getElementsByClassName('accptReturn')[0]
    let isAccepted = searchResult.item[itemNum].returnsAccepted[0]
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
    let priceValue = parseFloat(searchResult.item[itemNum].sellingStatus[0].convertedCurrentPrice[0].__value__)
    let shipFrom = searchResult.item[itemNum].location[0]
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

function checkEmpty(itemNum) {
    try {
        if (!(typeof searchResult.item[itemNum].galleryURL != 'undefined')) return false;
        else if (!(typeof searchResult.item[itemNum].title != 'undefined')) return false;
        else if (!(typeof searchResult.item[itemNum].viewItemURL[0] != 'undefined')) return false;
        else if (!(typeof searchResult.item[itemNum].primaryCategory[0].categoryName[0] != 'undefined')) return false;
        else if (!(typeof searchResult.item[itemNum].condition[0].conditionDisplayName[0] != 'undefined')) return false;
        else if (!(typeof searchResult.item[itemNum].topRatedListing[0] != 'undefined')) return false;
        else if (!(typeof searchResult.item[itemNum].returnsAccepted[0] != 'undefined')) return false;
        else if (!(typeof searchResult.item[itemNum].shippingInfo[0].shippingServiceCost[0].__value__ != 'undefined')) return false;
        else if (!(typeof searchResult.item[itemNum].sellingStatus[0].convertedCurrentPrice[0].__value__ != 'undefined')) return false;
        else if (!(typeof searchResult.item[itemNum].location[0] != 'undefined')) return false;
        else return true;
        
    } catch (error) {
        return false
    }

}

function setAllData() {
    let summary = document.getElementById('summary')
    summary.style.display = 'block'
    let card = document.getElementById('cards')
    card.style.display = 'block'
    let cardNum = 0
    let itemNum = 0
    while (cardNum < 3) {
        if (checkEmpty(itemNum)) {
            setData(cardNum,itemNum);
            cardNum++;
        }
        itemNum++;
    }
}

function updateSummary() {
    let keyword = params.toString().split('&')[0].split('=')[1]
    document.getElementById('summary').innerHTML = num_entries + ' Results found for <i>' + keyword + '</i><hr>'
}
