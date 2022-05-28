var express = require("express");
var router = express.Router();

const nutritionController = require("../controllers/nutrition");
const nutrition = new nutritionController();

/* 음식 영양소 조회*/
router.get("/food/:food_name", nutrition.Inquery);

/* 식단 기록 */
router.post("/record", nutrition.Record);

/* 음식 추천*/
router.post("/recommend", nutrition.Recommend);

/* 식단 기록 조회*/
router.get("/record/:user_id", nutrition.recordInquery);

module.exports = router;
