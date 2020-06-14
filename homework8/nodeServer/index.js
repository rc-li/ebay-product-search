const express = require('express');
const app = express();
const debug = require('debug')('myapp:server');

const port = process.env.PORT || 3000;

app.use(express.static('../newProj/dist/newProj'))

app.listen(port, () => {
    //  console.log('Server is up and running on port ', port);
    debug('Server is up and running on port ', port);
})

app.get('/', function (req, res) {
    return res.send()
})