var express = require("express");
var router = express.Router();

const userController = require("../controllers/user");
const user = new userController();

/* 사용자 스펙기록 */
router.post("/spec/:user_id", user.spec);

/* 사용자 스펙 수정  및 조회*/
router.patch("/spec/:user_id", user.modifyspec);

/* 사용자 스펙 조회 */
router.get("/spec/:user_id", user.Inquery);

module.exports = router;
