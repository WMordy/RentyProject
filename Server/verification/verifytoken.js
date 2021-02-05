const jwt = require("jsonwebtoken");

module.exports = function (req, res, next) {
 // const token = req.header('auth-token');
  const token = require("../routes/signin").Token[0];
  if (!token) return res.json({ msg: "Access Denied" });

  try {
    const verified = jwt.verify(token, require("../config/keys.js").TokenKey);
    req.user = verified;
    res.json({ msg: "Access Granted" });
    next();
  } catch (err) {
    res.json({ msg: "Invalid Token" });
  }
};