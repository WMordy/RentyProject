const express = require("express");
const router = express.Router();
const Post = require("../models/Post");
const User = require("../models/User");
const verify = require("../verification/verifytoken");
const verify2 = require("../verification/verifytoken2");
const fs = require("fs");



router.post("/", verify2, async (req, res) =>{
    try {
      var imagename = Date.now();
      var image = req.files.image;
      imagename = imagename + "." + image.name.split(".")[image.name.split(".").length - 1];
      image.mv("./images/" + imagename, function (error, result) {
          if (error) throw error;
      });
      var parsedData = JSON.parse(req.body.data);
      const post = new Post({
          user: req.header('user-id'),
          title: parsedData.title,
          description: parsedData.description,
          image: "./images/" + imagename,
          field: parsedData.field
        });

      const savedPost = await post.save();
      res.send(post);
    } catch (err) {
      res.status(500).send("Server error adding the post : "+err.message);
    }
});

router.get("/", verify2, async (req, res) =>{
  const owner = req.header('owner');
    if (owner=="0")
    {
        try {
            const posts = await Post.find({ user : req.header('user-id')});
            res.send(posts);
          } catch (err) {
            res.status(500);
            console.log("erreur : " + err.message);
          }
    }
    else{
        try {
            const posts = await Post.find();
            res.send(posts);
          } catch (err) {
            res.status(500);
            console.log("erreur : " + err.message);
          }
    }
});

router.delete("/:id",verify2, async (req, res) => {
  try {
      const currentPost = await Post.findOne({
          _id: req.params.id
      });

      if(req.header('user-id')==currentPost.user){
        try {
          fs.unlinkSync(currentPost.image);
          //Image removed
      } catch (err) {
          console.error("Error while removing the image : " + err);
          return res.send("Error while removing the image : " + err);
      }
      const postDeleted = await Post.findByIdAndRemove({
          _id: req.params.id
      });
      res.send("Post removed successfully");
      }


  } catch (err) {
      console.log("Error while removing the Post : " + err);
      res.send("Error while removing the Post : " + err);
  }
});

router.put("/:id", verify2, async (req, res) => {
  var imagename = Date.now();
  var newPost;
      try {
        var parsedData = JSON.parse(req.body.data);
        if (req.files == null) {
          newPost = {
            user: req.header('user-id'),
            title: parsedData.title,
            description: parsedData.description,
            field: parsedData.field
        };
        }else{
          const oldPost = await Post.findOne({
            _id: req.params.id
        });
          try {
            fs.unlinkSync(oldPost.image);
            //Image removed
        } catch (err) {
            console.error("Error while removing the old image : " + err);
        }
          var image = req.files.image;
          imagename = imagename + "." + image.name.split(".")[image.name.split(".").length - 1];
          image.mv("./images/" + imagename, function (error, result) {
              if (error) throw error;
          });

          newPost = {
            user: req.header('user-id'),
            title: parsedData.title,
            description: parsedData.description,
            image: "./images/" + imagename,
            field: parsedData.field
        };
        }


          const postUpdated = await Post.findOneAndUpdate({_id: req.params.id},
          {
            $set: newPost
          });
          res.send("Post updated successfully");
      } catch (err) {
          res.send("Error while updating the Post without updating image : " + err)
          console.log("Error while updating the Post updating image : " + err)
      };
});


module.exports = router;