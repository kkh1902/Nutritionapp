var express = require("express");
var router = express.Router();

const recordController = require("../controllers/record");
const record = new recordController();

//사용자 영양소 조회
router.get("/:user_id", record.Inquery);

// 사용자 영양소 기록
router.post("/:user_id", record.Record);

module.exports = router;
