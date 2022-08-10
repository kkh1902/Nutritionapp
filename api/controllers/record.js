const { request } = require("express");
const httpStatus = require("http-status-codes");
const pool = require("../middleware/db");

class record {
  // 사용자 영양소 조회
  async Create(req, res) {
    const user_id = 300;
    const { year, month, day } = req.body;
    const date = new Date(yaer, month, day);
    console.log(date);
    try {
      const cart_data = await pool.query(
        "select * from cart where user_id = ?;",
        [user_id]
      );
      return res.redirect("/");
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
