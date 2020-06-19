const express = require('express');
require('es6-promise').polyfill();
require('isomorphic-fetch');
const { response } = require('express');
// const XMLHttpRequest = require('xmlhttprequest').XMLHttpRequest
// const request = require('request')
const app = express();
const debug = require('debug')('myapp:server');

const port = process.env.PORT || 3000;

// app.use(express.static('../newProj/dist/newProj'))
app.use(express.static('./static'))

app.listen(port, () => {
    //  console.log('Server is up and running on port ', port);
    debug('Server is up and running on port ', port);
})

app.get('/', function (req, res) {
    res.send()
})

// q?keyword=mask&lowPrice=30&highPrice=50&isNew=on&isExpdtd=on&Sort+by%3A+=pPlusS_h
app.get('/q?', function (req, res) {
    var keyword = req.query.keyword;
    var lowPrice = req.query.lowPrice
    var highPrice = req.query.highPrice

    var isNew = req.query.isNew
    var isUsed = req.query.isUsed
    var isVrGd = req.query.isVrGd
    var isGood = req.query.isGood
    var isAccptb = req.query.isAccptb

    var isRtAccptd = req.query.isRtAccptd

    var isFree = req.query.isFree
    var isExpdtd = req.query.isExpdtd

    var sortOrder = req.query["Sort by: "]

    var url = 'https://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsAdvanced&SERVICE-VERSION=1.0.0&SECURITY-APPNAME=RayLi-exp-PRD-d2eb6beb6-1a4f60ed&RESPONSE-DATA-FORMAT=JSON&REST-PAYLOAD&'
    var counter = 0

    if (lowPrice != '') {
        url = url + 'itemFilter(' + counter + ').name=MinPrice&'
        url = url + 'itemFilter(' + counter + ').value=' + lowPrice + '&'
        url = url + 'itemFilter(' + counter + ').paramName=Currency&'
        url = url + 'itemFilter(' + counter + ').paramValue=USD&'
        counter = counter + 1
    }
    // console.log(url)

    if (highPrice != '') {
        url = url + 'itemFilter(' + counter + ').name=MaxPrice&'
        url = url + 'itemFilter(' + counter + ').value=' + highPrice + '&'
        url = url + 'itemFilter(' + counter + ').paramName=Currency&'
        url = url + 'itemFilter(' + counter + ').paramValue=USD&'
        counter = counter + 1
    }

    if (isNew == 'on' || isUsed == 'on' || isVrGd == 'on' || isGood == 'on' || isAccptb == 'on') {
        let num_cdt = 0
        url = url + 'itemFilter(' + counter + ').name=Condition&'
        if (isNew == 'on')
            url = url + 'itemFilter(' + counter + ').value(' + num_cdt + ')=New&'
        num_cdt = num_cdt + 1
        if (isUsed == 'on')
            url = url + 'itemFilter(' + counter + ').value(' + num_cdt + ')=Used&'
        num_cdt = num_cdt + 1
        if (isVrGd == 'on')
            url = url + 'itemFilter(' + counter + ').value(' + num_cdt + ')=4000&'
        num_cdt = num_cdt + 1
        if (isGood == 'on')
            url = url + 'itemFilter(' + counter + ').value(' + num_cdt + ')=5000&'
        num_cdt = num_cdt + 1
        if (isAccptb == 'on')
            url = url + 'itemFilter(' + counter + ').value(' + num_cdt + ')=6000&'
        num_cdt = num_cdt + 1
        counter = counter + 1
    }

    if (isRtAccptd == 'on') {
        url = url + 'itemFilter(' + counter + ').name=ReturnsAcceptedOnly&'
        url = url + 'itemFilter(' + counter + ').value=true&'
        counter = counter + 1
    }

    if (isFree == 'on') {
        url = url + 'itemFilter(' + counter + ').name=FreeShippingOnly&'
        url = url + 'itemFilter(' + counter + ').value=true&'
        counter = counter + 1
    }

    if (isExpdtd == 'on') {
        url = url + 'itemFilter(' + counter + ').name=ExpeditedShippingType&'
        url = url + 'itemFilter(' + counter + ').value=Expedited&'
        counter = counter + 1
    }

    if (sortOrder == 'bestMatch') {
        url = url + 'sortOrder=BestMatch&'
    }
    else if (sortOrder == 'highestFirst') {
        url = url + 'sortOrder=CurrentPriceHighest&'
    }
    else if (sortOrder == 'pPlusS_h') {
        url = url + 'sortOrder=PricePlusShippingHighest&'
    }
    else if (sortOrder == 'pPlusS_l') {
        url = url + 'sortOrder=PricePlusShippingLowest&'
    }

    url = url + 'keywords=' + keyword
    url = encodeURI(url)
    console.log(url)

    fetch(url)
        .then(response => response.json())
        .then((r) => {
            console.log("====================before processing =======================")
            console.log(r)
            r = processData(r)
            console.log("===================== after processing ========================")
            console.log(r)
            // return processData(r)
            res.send(r)
        })
        // .then((data) => {
        //     console.log("print the data before sending back to Angular")
        //     console.log(data)
        //     res.send({data})
        // })
        // .then(()=> {
        //     console.log("data sent back to Angular")
        // })

})


function processData(r) {
    try {
        num_entries = r["findItemsAdvancedResponse"][0]["paginationOutput"][0]["totalEntries"][0]
        num_entries = parseInt(num_entries)
        searchResult = r["findItemsAdvancedResponse"][0]["searchResult"][0]
    } catch (error) {
        let data = {}
        let item = []
        _item = {"item":item}
        data = {"searchResult":_item}
        data['numEntries'] = 0
        data['validCards'] = 0
        return data
    }

    r = prepareData()
    // console.log(r)
    return r

    function prepareData() {
        let data = {}
        // console.log(data)
        let item = []
        let cardNum = 0
        let itemNum = 0
        // console.log("preparing data for item " + itemNum)
        while (cardNum < 100 && itemNum < 100) {
            if (checkEmpty(itemNum)) {
                item.push(searchResult["item"][itemNum])
                cardNum = cardNum + 1
            }
            itemNum = itemNum + 1
        }
        _item = {"item":item}
        data = {"searchResult":_item}
        data['numEntries']=num_entries
        data['validCards'] = cardNum
        // console.log(data)
        return data
    }

    function checkEmpty(itemNum) {
        console.log('inside checkEmpty(), item number ' + itemNum)
        try {
            searchResult["item"][itemNum]["galleryURL"][0]
            searchResult["item"][itemNum]["title"][0]
            searchResult["item"][itemNum]["viewItemURL"][0]
            searchResult["item"][itemNum]["primaryCategory"][0]['categoryName'][0]
            searchResult["item"][itemNum]["condition"][0]['conditionDisplayName'][0]
            searchResult["item"][itemNum]["topRatedListing"][0]
            searchResult["item"][itemNum]["returnsAccepted"][0]
            searchResult["item"][itemNum]["shippingInfo"][0]['shippingServiceCost'][0]['__value__']
            searchResult["item"][itemNum]["sellingStatus"][0]['convertedCurrentPrice'][0]['__value__']
            searchResult["item"][itemNum]["location"][0]
            searchResult["item"][itemNum]["shippingInfo"][0]["shippingType"][0]
            searchResult["item"][itemNum]["shippingInfo"][0]["shipToLocations"][0]
            searchResult["item"][itemNum]["shippingInfo"][0]["expeditedShipping"][0]
            searchResult["item"][itemNum]["shippingInfo"][0]["oneDayShippingAvailable"][0]
            searchResult["item"][itemNum]["listingInfo"][0]["bestOfferEnabled"][0]
            searchResult["item"][itemNum]["listingInfo"][0]["buyItNowAvailable"][0]
            searchResult["item"][itemNum]["listingInfo"][0]["listingType"][0]
            searchResult["item"][itemNum]["listingInfo"][0]["gift"][0]
            searchResult["item"][itemNum]["listingInfo"][0]["watchCount"][0]
            return true
        }
        catch(err) {
            return false
        }
    
    }
    

    
}

