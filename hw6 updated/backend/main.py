from flask import Flask
from flask import request
import requests
import urllib
import json
app = Flask(__name__, static_url_path="")

# need to
# 1. parse URL that client sent
# 2. contruct URL to send to eBay
# 3. parse eBay response
# 4. send back to client


@app.route('/')
def hello():
    return app.send_static_file('index.html')


@app.route('/q')
def toEbay():
    keyword = request.args.get('keyword')
    lowPrice = request.args.get('lowPrice')
    highPrice = request.args.get('highPrice')

    isNew = request.args.get('isNew')
    isUsed = request.args.get('isUsed')
    isVrGd = request.args.get('isVrGd')
    isGood = request.args.get('isGood')
    isAccptb = request.args.get('isAccptb')

    isRtAccptd = request.args.get('isRtAccptd')

    isFree = request.args.get('isFree')
    isExpdtd = request.args.get('isExpdtd')

    sortOrder = request.args.get('Sort by: ')

    url = 'https://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsAdvanced&SERVICE-VERSION=1.0.0&SECURITY-APPNAME=RayLi-exp-PRD-d2eb6beb6-1a4f60ed&RESPONSE-DATA-FORMAT=JSON&REST-PAYLOAD&'

    print(lowPrice, highPrice)

    counter = 0

    if lowPrice is not '':
        url = url + 'itemFilter(' + str(counter) + ').name=MinPrice&'
        url = url + 'itemFilter(' + str(counter) + ').value=' + str(lowPrice) + '&'
        url = url + 'itemFilter(' + str(counter) + ').paramName=Currency&'
        url = url + 'itemFilter(' + str(counter) + ').paramValue=USD&'
        counter = counter + 1

    if highPrice is not '':
        url = url + 'itemFilter(' + str(counter) + ').name=MaxPrice&'
        url = url + 'itemFilter(' + str(counter) + ').value=' + str(highPrice) + '&'
        url = url + 'itemFilter(' + str(counter) + ').paramName=Currency&'
        url = url + 'itemFilter(' + str(counter) + ').paramValue=USD&'
        counter = counter + 1

    if isNew == 'on' or isUsed == 'on' or isVrGd == 'on' or isGood == 'on' or isAccptb == 'on':
        num_cdt = 0
        url = url + 'itemFilter(' + str(counter) + ').name=Condition&'
        if isNew == 'on':
            url = url + 'itemFilter(' + str(counter) + ').value('+str(num_cdt)+')=New&'
            num_cdt = num_cdt + 1
        if isUsed == 'on':
            url = url + 'itemFilter(' + str(counter) + ').value('+str(num_cdt)+')=Used&'
            num_cdt = num_cdt + 1
        if isVrGd == 'on':
            url = url + 'itemFilter(' + str(counter) + ').value('+str(num_cdt)+')=4000&'
            num_cdt = num_cdt + 1
        if isGood == 'on':
            url = url + 'itemFilter(' + str(counter) + ').value('+str(num_cdt)+')=5000&'
            num_cdt = num_cdt + 1
        if isAccptb == 'on':
            url = url + 'itemFilter(' + str(counter) + ').value('+str(num_cdt)+')=6000&'
            num_cdt = num_cdt + 1
        counter = counter + 1

    if isRtAccptd == 'on':
        url = url + 'itemFilter(' + str(counter) + ').name=ReturnsAcceptedOnly&'
        url = url + 'itemFilter(' + str(counter) + ').value=true&'
        counter = counter + 1

    if isFree == 'on':
        url = url + 'itemFilter(' + str(counter) + ').name=FreeShippingOnly&'
        url = url + 'itemFilter(' + str(counter) + ').value=true&'
        counter = counter + 1

    if isExpdtd == 'on':
        url = url + 'itemFilter(' + str(counter) + ').name=ExpeditedShippingType&'
        url = url + 'itemFilter(' + str(counter) + ').value=Expedited&'
        counter = counter + 1

    
    if sortOrder == 'bestMatch':
        url = url + 'sortOrder=BestMatch&'
    elif sortOrder == 'highestFirst':
        url = url + 'sortOrder=CurrentPriceHighest&'
    elif sortOrder == 'pPlusS_h':
        url = url + 'sortOrder=PricePlusShippingHighest&'
    elif sortOrder == 'pPlusS_l':
        url = url + 'sortOrder=PricePlusShippingLowest&'

    url = url + 'keywords=' + urllib.parse.quote_plus(keyword)
    print(url)

    r = requests.get(url)
    r= r.json()

    return r


if __name__ == '__main__':
    app.run(port=8080)