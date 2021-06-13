console.log('Hello')
var moment = require('moment');
moment.locale('ru');
var now = moment();
console.time('Время ожидания запроса');
const axios = require('axios');
axios.get("https://jsonplaceholder.typicode.com/todos/1")
    .then(response => {console.log("response", response.data);console.timeEnd('Время ожидания запроса')})
console.log('Начало запроса: ' + now.format('YYYY-MM-DD HH:mm:ss'));










// node index.js