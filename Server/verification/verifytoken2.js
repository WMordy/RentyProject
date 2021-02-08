const jwt = require("jsonwebtoken");

module.exports = function (req, res, next) {
  const token = req.header('auth-token');
  //const token = req.body.token;
  if (!token) return res.json({ msg: "Access Denied" });
  try {
    const verified = jwt.verify(token, require("../config/default.json").jwtSecret);
    req.user = verified;
    next();
  } catch (err) {
    res.json({ msg: "Invalid Token" });
  }
};