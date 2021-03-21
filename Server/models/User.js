const mongoose = require("mongoose");

const userSchema = new mongoose.Schema({
  email: {
    type: String,
    required: true,
    min: 6,
  },
  phone: {
    type: String,
    required: true,
    min: 6,
  },
  password: {
    type: String,
  },
  firstName: {
    type: String,
    required: true,
    min: 6,
  },
  lastName: {
    type: String,
    required: true,
    min: 6,
  },
  avatar: {
    type: String,
    required: true,
    min: 6,
  },
  subscribed_field:{
    type: [String]
  }
});

module.exports = mongoose.model("User", userSchema);