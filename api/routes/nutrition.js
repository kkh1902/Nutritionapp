var express = require("express");
var router = express.Router();

const nutritionController = require("../controllers/nutrition");
const nutrition = new nutritionController();

/* 음식 영양소 조회*/
router.get("/:food_name", nutrition.Inquery);

/* 음식 추천*/
router.post("/recommend", nutrition.Recommend);

module.exports = router;
