const express = require("express");
const router = express.Router();
const Post = require("../models/Post");
const User = require("../models/User");
const verify = require("../verification/verifytoken");
const verify2 = require("../verification/verifytoken2");
const fs = require("fs");
const jwt = require("jsonwebtoken");


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
          user: (jwt.verify(req.header('auth-token'), require("../config/default.json").jwtSecret))._id,
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
            const posts = await Post.find({ user : (jwt.verify(req.header('auth-token'), require("../config/default.json").jwtSecret))._id});
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

      if((jwt.verify(req.header('auth-token'), require("../config/default.json").jwtSecret))._id==currentPost.user){
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
      else{
        return res.send("You cannot delete this post");
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
      const oldPost = await Post.findOne({_id: req.params.id});
      if((jwt.verify(req.header('auth-token'), require("../config/default.json").jwtSecret))._id==oldPost.user){
        var parsedData = JSON.parse(req.body.data);
        if (req.files == null) {
          newPost = {
            user: (jwt.verify(req.header('auth-token'), require("../config/default.json").jwtSecret))._id,
            title: parsedData.title,
            description: parsedData.description,
            field: parsedData.field
        };
        }else{
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
            user: (jwt.verify(req.header('auth-token'), require("../config/default.json").jwtSecret))._id,
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
      }else{
        return res.send("You cannot edit this post")
      }

      } catch (err) {
          res.send("Error while updating the Post : " + err)
          console.log("Error while updating the Post : " + err)
      };
});


module.exports = router;