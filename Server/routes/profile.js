const express = require("express");
const router = express.Router();
const Post = require("../models/Post");
const User = require("../models/User");
const verify = require("../verification/verifytoken");
const verify2 = require("../verification/verifytoken2");
const fs = require("fs");
const jwt = require("jsonwebtoken");

router.get('/', async (req,res) =>{
    try{
        const Users = await User.find(null,{ firstName: 1, lastName: 1, phone: 1, subscribed_field: 1, avatar: 1, _id: 0});
        return res.json(Users);
    }catch(err){
        console.log(err)
        return res.send("Error getting the profiles : "+ err);
    }
});


router.get('/:id', async (req,res) =>{
    try{
        const currentProfile = await User.findOne({
            _id: req.params.id
        },{ firstName: 1, lastName: 1, phone: 1, avatar: 1, _id: 0});
        return res.json(currentProfile);
    }catch(err){
        console.log(err)
        return res.send("Error getting the specific profile : "+ err);
    }
});

router.put('/', async (req,res)=>{
    try {
        const currentProfile = await User.updateOne({_id: (jwt.verify(req.header('auth-token'), require("../config/default.json").jwtSecret))._id},
        {
            $push: { subscribed_field: req.body.field }
          });
        return res.send("Field added Successfully");
    } catch (err) {
        console.log(err);
        return res.send("error adding the new field")
    }
})
module.exports = router;