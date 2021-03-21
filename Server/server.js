const connectDB = require("./config/db");
const express = require("express");
const cors = require("cors");
const upload = require("express-fileupload");


// Connect Database
connectDB();

const app = express();
 
// Init Middleware
app.use(cors());
app.use(express.json({ extended: false }));
app.use(upload()); // permet l'upload des images


app.get("/", (req, res) => {
  res.send("API running");
});

// Define Routes
app.use("/api/signin", require("./routes/signin"));
app.use("/api/signup", require("./routes/signup"));
app.use("/api/posts", require("./routes/posts"));
app.use("/api/profile", require("./routes/profile"));

const PORT = process.env.PORT || 5001;

app.listen(PORT, () => console.log("Server started on port : " + PORT));