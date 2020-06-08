var result;
var num_entries;
var params;
var searchResult;
var obj;
var numValidCards = 0

function valueCheck() {
    let lowPrice = parseFloat(document.getElementById('lowPrice').value)
    let highPrice = parseFloat(document.getElementById('highPrice').value)
    if (lowPrice < 0.0) {
        alert("Price Range values cannot be negative! Please try a value greater than or equal to 0.0")
        return false
    }
    else if (lowPrice > highPrice) {
        alert("Oops! Lower price limit cannot be greater than upper price limit! Please try again.")
        return false
    }
    else return true
}

function callFetch() {
    if (valueCheck()){
        const formData = new FormData(form);
        params = new URLSearchParams(formData);
        console.log(params.toString());
    
        req = new XMLHttpRequest();
        req.open("GET", "/q?" + params, false);
        req.send();
        res = req.responseText
        obj = JSON.parse(res)
        num_entries = obj.numEntries
        searchResult = obj.searchResult
        numValidCards = obj.validCards
    
        updateSummary();
        setAllData();
    }
}

function setData(cardNum, itemNum) {
    // set card image
    let card = document.getElementById(cardNum);

    if (searchResult.item[itemNum].galleryURL[0] == "https://thumbs1.ebaystatic.com/pict/04040_0.jpg") {
        card.getElementsByTagName("img")[0].src = 'ebay_default.jpg'
    }
    else {
        card.getElementsByTagName("img")[0].src = searchResult.item[itemNum].galleryURL[0]
    }

    // set the product link
    let div = card.getElementsByTagName('div')[0]
    let a = div.getElementsByTagName('a')[0]
    a.innerHTML = "<p class='titleText'>" + searchResult.item[itemNum].title[0] + '</p>'
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
        accptReturn.innerHTML = 'Seller <b>accepts</b> returns'
    }
    else {
        accptReturn.innerHTML = 'Seller <b>does not accept</b> returns'
    }

    // set shipping type
    let shipType = div.getElementsByClassName('shipType')[0]
    let shippingServiceCost = searchResult.item[itemNum].shippingInfo[0].shippingServiceCost[0].__value__
    if (shippingServiceCost == 0.0) {
        shipType.innerHTML = 'Free Shipping'
    }
    else {
        shipType.innerHTML = 'No Free Shipping'
    }

    let expedited = searchResult.item[0].shippingInfo[0].expeditedShipping[0]
    if (expedited == "true") {
        shipType.innerHTML += " -- Expedited Shipping available"
    }

    // set price
    let priceTag = div.getElementsByClassName('price')[0]
    let priceValue = parseFloat(searchResult.item[itemNum].sellingStatus[0].convertedCurrentPrice[0].__value__)
    let shipFrom = searchResult.item[itemNum].location[0]
    if (shippingServiceCost > 0) {
        priceTag.innerHTML = '<b>Price: $' + priceValue + '</b>' + ' (+ $' + shippingServiceCost + ' for shipping)'
    }
    else {
        priceTag.innerHTML = '<b>Price: $' + priceValue + '</b>'
    }

    // set ship from
    let locTag = div.getElementsByClassName('priceTag_loc')[0]
    locTag.innerHTML = '<i>From ' + shipFrom + '</i>'

}

function setAllData() {
    let summary = document.getElementById('summary')
    summary.style.display = 'block'
    document.getElementById('cards').style.display = 'block'
    let cardToShow;
    document.getElementById('ShowMore').style.display = 'none'
    document.getElementById('ShowLess').style.display = 'none'

    if (numValidCards > 3) {
        document.getElementById('ShowMore').style.display = 'block'
        cardToShow = 3
    }
    else {
        cardToShow = numValidCards
    }
    for (var i = 0; i < 10; i++) {
        let card = document.getElementById(i)
        card.style.display = 'none'
    }
    let itemNum = 0
    for (var cardNum = 0; cardNum < cardToShow; cardNum++){
        let card = document.getElementById(cardNum)
        card.style.display = 'flex'
        setData(cardNum,itemNum);
        itemNum++;
    }
}

function showMore() {
    document.getElementById('ShowMore').style.display = 'none'
    document.getElementById('ShowLess').style.display = 'block'
    let itemNum = 0
    for (var cardNum = 0; cardNum < numValidCards; cardNum++) {
        let card = document.getElementById(cardNum)
        card.style.display = 'flex'
        setData(cardNum,itemNum)
        itemNum++
    }
    document.getElementById('ShowLess').scrollIntoView({behavior: "smooth"})
}

function showLess() {
    document.getElementById('ShowLess').style.display = 'none'
    document.getElementById('ShowMore').style.display = 'block'
    for (var cardNum = 3; cardNum < numValidCards; cardNum++) {
        let card = document.getElementById(cardNum)
        card.style.display = 'none'
    }
    document.getElementsByClassName('logo')[0].scrollIntoView({behavior: "smooth"})
}

function updateSummary() {
    let keyword = params.toString().split('&')[0].split('=')[1]
    document.getElementById('summary').innerHTML = num_entries + ' Results found for <i>' + keyword.replace(/\+/g, ' ') + '</i><hr>'
}

function expandCard(cardNum) {
    let arr = document.getElementById(cardNum).getElementsByClassName('canHide')
    for (var i = 0; i < arr.length; i++){
        arr[i].style.display = 'block'
    }
    let titleArr = document.getElementById(cardNum).getElementsByClassName('titleText')
    for (var i = 0; i < titleArr.length; i++) {
        titleArr[i].className = 'somethingElse'
    }
}

function closeButton(cardNum) {
    let arr = document.getElementById(cardNum).getElementsByClassName('canHide')
    for (var i = 0; i < arr.length; i++){
        arr[i].style.display = 'none'
    }
    let titleArr = document.getElementById(cardNum).getElementsByClassName('somethingElse')
    for (var i = 0; i < titleArr.length; i++) {
        titleArr[i].className = 'titleText'
    }
}

function clearAll() {
    document.getElementById('summary').style.display = 'none'
    document.getElementById('cards').style.display = 'none'
}