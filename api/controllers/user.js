const { request } = require("express");
const httpStatus = require("http-status-codes");
const pool = require("../middleware/db");

class user {
  //마이페이지 접근
  async Inquery(req, res) {
    try {
      const user_id = 300;

      const user_info = await pool.query("select * from user where user_id=?", [
        user_id,
      ]);
      return res.render("mypage", { user_info: user_info[0][0] });
    } catch (error) {
      return res.status(500).json(error);
    }
  }

  // 바디 스펙 수정
  async Update(req, res) {
    try {
      const user_id = 300;
      const { age, height, weight } = req.body;

      const userUpdate = await pool.query(
        "update user set weight=?, height=?, age=? where user_id=?",
        [weight, height, age, user_id]
      );

      return res.redirect('/mypage');
    } catch (error) {
      return res.status(500).json(error);
    }
  }
}
module.exports = user;
