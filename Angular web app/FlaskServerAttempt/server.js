const express = require('express');
const app = express();
const XMLHttpRequest = require('xmlhttprequest').XMLHttpRequest

app.use(express.static('./static'))

app.get('/', (req, res) => {
  res.send();
});

app.get('/q?', function (req, res) {
    // q?keyword=mask&lowPrice=30&highPrice=50&isNew=on&isExpdtd=on&Sort+by%3A+=pPlusS_h
    // request()
    theURL = 'https://exp571.wl.r.appspot.com' + req.url
    requestEbay = new XMLHttpRequest();
    requestEbay.open("GET", theURL, false);
    requestEbay.send();
    responseEbay = requestEbay.responseText
    res.send(responseEbay)

})

const server = app.listen(8080, () => {
  const host = server.address().address;
  const port = server.address().port;

  console.log(`Example app listening at http://${host}:${port}`);
});

