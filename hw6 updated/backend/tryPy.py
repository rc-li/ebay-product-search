import json
import requests

url = 'https://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsAdvanced&SERVICE-VERSION=1.0.0&SECURITY-APPNAME=RayLi-exp-PRD-d2eb6beb6-1a4f60ed&RESPONSE-DATA-FORMAT=JSON&REST-PAYLOAD&sortOrder=BestMatch&keywords=iphone'
r = requests.get(url)
r= r.json()

num_entries = r["findItemsAdvancedResponse"][0]["paginationOutput"][0]["totalEntries"][0]
num_entries = int(num_entries)
searchResult = r["findItemsAdvancedResponse"][0]["searchResult"][0]

def checkEmpty(itemNum):
    try:
        searchResult["item"][itemNum]["galleryURL"][0]
        searchResult["item"][itemNum]["title"][0]
        searchResult["item"][itemNum]["viewItemURL"][0]
        searchResult["item"][itemNum]["primaryCategory"][0]
        searchResult["item"][itemNum]["condition"][0]
        searchResult["item"][itemNum]["topRatedListing"][0]
        searchResult["item"][itemNum]["returnsAccepted"][0]
        searchResult["item"][itemNum]["shippingInfo"][0]
        searchResult["item"][itemNum]["sellingStatus"][0]
        searchResult["item"][itemNum]["location"][0]
        return True
    except:
        return False

def prepareData():
    data = {}
    item = []
    cardNum = 0
    itemNum = 0
    while (cardNum < 10 and itemNum < num_entries):
        if checkEmpty(itemNum):
            item.append(searchResult["item"][itemNum])
            cardNum = cardNum + 1
        itemNum = itemNum + 1
    _item = {"item":item}
    data = {"searchResult":_item}
    data['numEntries']=num_entries
    data['validCards'] = cardNum
    return data



#exec(open('tryPy.py').read())
