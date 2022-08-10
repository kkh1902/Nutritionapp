var express = require("express");
var router = express.Router();
const userController = require("../controllers/user");
const user = new userController();
const searchController = require("../controllers/search");
const search = new searchController();
const cartController = require("../controllers/cart");
const cart = new cartController();
const recordController = require("../controllers/record");
const record = new recordController();

router.get("/", function (req, res, next) {
  res.render("main");
});

router.get("/loading", function (req, res, next) {
    res.render("loading");
  });

router.get("/signin", function (req, res, next) {
  res.render("signin");
  return res.render("main");
});

router.get("/singup", function (req, res, next) {
  res.render("signup");
});


//working

//음식 추가 페이지
router.get("/searchfood", function (req, res, next) {
    return res.render("searchfood", { food: [] });
});
router.post("/searchfood", search.Read);

//음식 목록 페이지
router.get("/listfood", cart.Read);
router.post("/listfood/update", cart.Create);
router.post("/listfood/delete", cart.Delete);
router.post("/listfood/record", record.Create);

//켈린더 페이지
router.get("/calendar", function (req, res, next) {
    return res.render("calendar");
});

//마이페이지
router.get("/mypage", user.Inquery);
router.post("/spec/update", user.Update);

//테스트 페이지
router.get("/test", function (req, res, next) {
    return res.render("loading");
});

module.exports = router;
