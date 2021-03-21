const express = require("express");
const router = express.Router();
const Post = require("../models/Post");
const User = require("../models/User");
const verify = require("../verification/verifytoken");
const verify2 = require("../verification/verifytoken2");
const fs = require("fs");

router.get('/', async (req,res) =>{
    try{
        const Users = await User.find(null,{ firstName: 1, lastName: 1, phone: 1, avatar: 1, _id: 0});
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


module.exports = router;