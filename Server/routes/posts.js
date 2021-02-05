const express = require("express");
const router = express.Router();
const Post = require("../models/Post");
const User = require("../models/User");
const verify = require("../verification/verifytoken");
const verify2 = require("../verification/verifytoken2");




router.post("/", verify, async (req, res) =>{


    try {
      const post = new Post({ 
          user: require("../routes/signin").Token[1],
          title: req.body.title,
          description: req.body.description,
          image: req.body.title,
          field: req.body.field
        });

      const savedPost = await post.save();
      res.send(post);
    } catch (err) {
      res.status(500).send("Server error : "+err.message);
    }
})

router.get("/", verify2, async (req, res) =>{
    if (req.body.pass=="1")
    {
        try {
            const posts = await Post.find({ user : require("../routes/signin").Token[1]});
            res.send(posts);
          } catch (err) {
            res.status(500);
            console.log(err.message+"voici l'erreur");
          }
    }
    else{
        try {
            const posts = await Post.find();
            res.send(posts);
          } catch (err) {
            res.status(500);
            console.log(err.message+"voici l'erreur");
          }
    }

})



module.exports = router;