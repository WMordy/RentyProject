const connectDB = require("./config/db");
const express = require("express");
const cors = require("cors");

// Connect Database
connectDB();

const app = express();
 
// Init Middleware
app.use(cors());
app.use(express.json({ extended: false }));

app.get("/", (req, res) => {
  res.send("API running");
});

// Define Routes
app.use("/api/signin", require("./routes/signin").router);
app.use("/api/signup", require("./routes/signup"));

const PORT = process.env.PORT || 5001;

app.listen(PORT, () => console.log("Server started on port : " + PORT));