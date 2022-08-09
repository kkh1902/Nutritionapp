var express = require("express");
var router = express.Router();

const userController = require("../controllers/user");
const user = new userController();

/* 사용자 스펙 조회 */
router.get("/spec/:user_id", user.Inquery);

/* 사용자 스펙기록 */
router.post("/spec/update", user.Update);

module.exports = router;
