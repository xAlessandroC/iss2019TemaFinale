var express = require('express');
var router = express.Router();

const pic = {
  dish : 20
}
const dic = {
  dish : 0
}

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('homepage', {
     title: 'Maitre',
     pantryInitialContent : JSON.stringify(pic),//.replace(/{|}|"/g,'').replace(":"," "),
     dishwasherInitialContent : JSON.stringify(dic),//.replace(/{|}|"/g,'').replace(":"," "),
     tableInitialContent : null,
     fridgeInitialContent : null
   });
});

module.exports = router;
