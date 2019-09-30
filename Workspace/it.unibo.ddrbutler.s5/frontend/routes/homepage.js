var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res) {
  res.render('homepage', {
    title: 'Maitre',
    initialContent: '20 dishes'
  });
});

module.exports = router;
