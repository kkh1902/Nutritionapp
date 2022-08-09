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
      return res.redirect("/");
    }
  }

  // 바디 스펙 수정
  async Update(req, res) {
    try {
      const user_id = 300;
      const { age, height, weight } = req.body;
      const userInfo = await pool.query("select * from user where user_id=?", [
        user_id,
      ]);
      const gender = userInfo[0][0].gender;
      let bmr = 0.0;
      console.log(bmr);
      if (gender == "남성") {
        bmr = 66.47 + (13.75 * weight + 5 * height - 6.76 * age);
        bmr = Number(Math.round(bmr));
        console.log(bmr);
      } else if (gender == "여성") {
        bmr = 655.1 + (9.56 * weight + 1.85 * height - 4.68 * age);
        bmr = Number(Math.round(bmr));
      }

      const userUpdate = await pool.query(
        "update user set weight=?, height=?, age=?, bmr=? where user_id=?",
        [weight, height, age, bmr, user_id]
      );

      return res.redirect("/mypage");
    } catch (error) {
      return res.redirect("/");
    }
  }
}
module.exports = user;
