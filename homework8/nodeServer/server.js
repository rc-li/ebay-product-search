const express = require('express');
require('es6-promise').polyfill();
require('isomorphic-fetch');
const { response } = require('express');
// const XMLHttpRequest = require('xmlhttprequest').XMLHttpRequest
// const request = require('request')
const app = express();
const debug = require('debug')('myapp:server');

const port = process.env.PORT || 3000;

app.use(express.static('../newProj/dist/newProj'))
// app.use(express.static('./static'))

app.listen(port, () => {
    //  console.log('Server is up and running on port ', port);
    debug('Server is up and running on port ', port);
})

app.get('/', function (req, res) {
    res.send()
})

app.get('/q?', function (req, res) {
    // q?keyword=mask&lowPrice=30&highPrice=50&isNew=on&isExpdtd=on&Sort+by%3A+=pPlusS_h
    // request()
    theURL = 'https://exp571.wl.r.appspot.com' + req.url
    // requestEbay = new XMLHttpRequest();
    // requestEbay.open("GET", theURL, false);
    // requestEbay.send();
    // responseEbay = requestEbay.responseText
    // res.send(responseEbay)

    fetch(theURL)
        .then(response => response.json())
        // .then(console.log(response))
        .then(data => res.send(data))

})