const express = require("express");
const router = express.Router();
const User = require("../models/User");
const bcrypt = require("bcryptjs");

// Validation

const Joi = require("@hapi/joi");

const schema_signin = Joi.object({
  email: Joi.string().min(6).required().email(),
  phone: Joi.string().min(10).required(),
  password: Joi.string().min(4).required(),
  firstName: Joi.string().min(6).required(),
  lastName: Joi.string().min(6).required(),
  avatar: Joi.string().min(6).required(),
});

// Register new user
router.post("/", async (req, res) => {
  // Validate data before creating user
  const { error } = schema_signin.validate(req.body);

  if (error) {
    return res.status(400).send(error.details[0].message);
  }

  // Check if the user exists already
  const emailExist = await User.findOne({ email: req.body.email });
  if (emailExist) return res.status(400).send("Email already exists");

  // Hash the password
  const salt = await bcrypt.genSalt(10);
  const hashedPassword = await bcrypt.hash(req.body.password, salt);

  const user = new User({ 
    email: req.body.email,
    password: hashedPassword,
    phone: req.body.phone,
    firstName: req.body.firstName,
    lastName: req.body.lastName,
    avatar: req.body.avatar
  });
  try {
    const savedUser = await user.save();
    res.send({ user: user._id });
  } catch (err) {
    res.status(500).send("Server error : "+err.message);
  }
});




module.exports = router;