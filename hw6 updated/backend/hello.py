from flask import Flask
from flask import request
import requests
import urllib
app = Flask(__name__, static_url_path="")

# need to
# 1. parse URL that client sent
# 2. contruct URL to send to eBay
# 3. parse eBay response
# 4. send back to client


@app.route('/searchPage')
def hello():
    print('the light is my strength')
    return app.send_static_file('index.html')


@app.route('/test/<string>/<int>/<int2>/<string2>')
def test(string, int, int2, string2):
    return "routing successful"


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

    choice = request.args.get('Sort by: ')

    # urlHead = 'https://svcs.eBay.com/services/FindingService/v1?'+'OPERATION-NAME=findItemsAdvanced'+'&SERVICE-VERSION'
    url = 'https://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsAdvanced&SERVICE-VERSION=1.0.0&SECURITY-APPNAME=RayLi-exp-PRD-d2eb6beb6-1a4f60ed&RESPONSE-DATA-FORMAT=JSON&'

    print(lowPrice, highPrice)

    counter = 0

    if lowPrice is not '':
        url = url + 'itemFilter(' + str(counter) + ').name=MinPrice&'
        url = url + 'itemFilter(' + str(counter) + ').value=' + str(lowPrice) + '&'
        url = url + 'itemFilter(' + str(counter) + ').paramName=Currency&'
        url = url + 'itemFilter(' + str(counter) + ').paramValue=USD&'
        counter = counter + 1

    if highPrice is not '':
        url = url + 'itemFilter(' + str(counter) + ').name=MaxPrices&'
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
        pass



    url = url + 'keywords=' + urllib.parse.quote_plus(keyword)

    # r = requests.get(url)
    # print(r.json())

    # filters = []
    # if lowPrice is not None and highPrice is not None:
    #     ele = {'name': 'MinPrice', 'value': lowPrice,
    #            'paramName': 'Currency', 'paramValue': 'USD'}
    #     filters = filters + ele
    print(url)
    return "routed"


if __name__ == '__main__':
    app.run(debug=True)

    # resp = requests.get('http://svcs.ebay.com/services/search/FindingService/v1?'
    # 'OPERATION-NAME=findItemsAdvanced&SERVICE-VERSION=1.0.0'
    # '&SECURITY-APPNAME=RayLi-exp-PRD-d2eb6beb6-1a4f60ed&RESPONSE-DATA-FORMAT=JSON'
    # '&keywords=harry%20potter&sortOrder=PricePlusShippingLowest')

    # resp = requests.get('https://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsAdvanced&SERVICE-VERSION=1.0.0&SECURITY-APPNAME=RayLi-exp-PRD-d2eb6beb6-1a4f60ed&RESPONSE-DATA-FORMAT=JSON&keywords=harry%20potter')

    # resp = requests.get('https://google.com')
    # return resp.content
    # ret = str(resp)
    # return ret

    # resp = requests.get('https://todolist.example.com/tasks/')
    # if resp.status_code != 200:
    #     # This means something went wrong.
    #     raise ApiError('GET /tasks/ {}'.format(resp.status_code))
    # for todo_item in resp.json():
    #     print('{} {}'.format(todo_item['id'], todo_item['summary']))
    # return "busteria"
