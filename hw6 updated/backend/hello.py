from flask import Flask
from flask import request
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
def test(string,int,int2,string2):
    return "routing successful"


@app.route('/q')
def toEbay():
    keyword = request.args.get('keyword')
    lowPrice = request.args.get('LowPrice')
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
    return "routed"

# http://localhost:5000/keyword=abathur&lowPrice=132&highPrice=&Sort+by%3A+=bestMatch"

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
