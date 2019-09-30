var express = require('express');
var router = express.Router();

/* GET home page. */
router.post('/task', function(req, res) {
  var prepare = req.body;
  console.log("Ricevuto "+ prepare);
});

module.exports = router;
