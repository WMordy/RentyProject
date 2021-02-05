const express = require("express");
const router = express.Router();

const verifyToken = require("./verifyToken");

router.get("/", (req, res, next) => {
  verifyToken(req, res, next);
  next();
});

module.exports = router;