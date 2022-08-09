var express = require("express");
var router = express.Router();
const nutritionController = require("../controllers/nutrition");
const nutrition = new nutritionController();
const recordController = require("../controllers/record");
const record = new recordController();
const userController = require("../controllers/user");
const user = new userController();

router.get("/", function (req, res, next) {
  res.render("main");
});



router.get("/signin", function (req, res, next) {
  res.render("signin");
});

router.get("/singup", function (req, res, next) {
  res.render("signup");
});


//working

router.get("/addfood", function (req, res, next) {
    res.render("addfood", { food: [] });
});

router.post("/addfood", nutrition.Inquery);

router.post("/listfood/update", record.Record);

router.get("/listfood", record.Inquery);

router.get("/mypage", user.Inquery);

router.post("/spec/update", user.Update);

module.exports = router;
