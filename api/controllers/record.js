const { request } = require("express");
const httpStatus = require("http-status-codes");
const pool = require("../middleware/db");

class record {
  // 사용자 영양소 조회
  async Inquery(req, res) {
    const user_id = 300;
    try {
      const user_record = await pool.query(
        "select record_id, meal_time, food2.food_name, food_once, kcal, tan, dan, fat from record join food2 where record.user_id=? and record.food_name = food2.food_name;",
        [user_id]
      );
      return res.render("listfood", { user_record: user_record[0] });
    } catch (error) {
      return res.redirect("/");
    }
  }

  // 사용자 영양소 기록 추가
  async Record(req, res) {
    const user_id = 300;
    const { food_name, food_amount, meal_time } = req.body;
    const date = new Date();
    try {
      const user_record = await pool.query(
        "insert into record values (null, ?, ?, ?, ?, ?)",
        [user_id, date, meal_time, food_name, food_amount]
      );
      return res.redirect("/addfood");
    } catch (error) {
      return res.redirect("/");
    }
  }

  // 사용자 영양소 기록 삭제
  async Delete(req, res) {
    const user_id = 300;
    const { record_id } = req.body;
    console.log(user_id, record_id);
    try {
      const user_record = await pool.query(
        "delete from record where user_id = ? and record_id = ?",
        [user_id, record_id]
      );
      return res.redirect("/listfood");
    } catch (error) {
      return res.redirect("/");
    }
  }
}

module.exports = record;
