var express = require('express');
var router = express.Router();

/* POST task handler */
router.post('/task', function(req, res, next) {
   var task = req.params
   console.log(req)
   console.log(task)
   res.send("ciao")
});

module.exports = router;
