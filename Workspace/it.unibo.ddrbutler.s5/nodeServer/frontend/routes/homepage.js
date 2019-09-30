var express = require('express');
var router = express.Router();

const pic = {
  dish : 20
}
const dic = {
  dish : 0
}
const fic ={
  taralli : 20,
  brasciole:20,
  polpette:20,
  cicorie:20
}

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('homepage', {
     title: 'Maitre',
     pantryInitialContent : JSON.stringify(pic),//.replace(/{|}|"/g,'').replace(":"," "),
     dishwasherInitialContent : JSON.stringify(dic),//.replace(/{|}|"/g,'').replace(":"," "),
     tableInitialContent : null,
     fridgeInitialContent : JSON.stringify(fic),
   });
});

module.exports = router;
