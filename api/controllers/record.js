const { request } = require("express");
const httpStatus = require("http-status-codes");
const pool = require("../middleware/db");

class record {
    // 사용자 영양소 조회
    async Inquery(req, res) {
        const user_id = 300;
        try {
            const user_record = await pool.query(
                "select meal_time, food2.food_name, food_once, kcal, tan, dan, fat from record join food2 where record.user_id=? and record.food_name = food2.food_name;",
                [user_id]
            );
            console.log(user_record[0]);
            return res.render('listfood', {user_record: user_record[0]})
        } catch (error) {
            return res.render('error')
        }
    }

    // 사용자 영양소 기록
    async Record(req, res) {
        const {food_name, food_amount, meal_time} = req.body;
        const date = new Date();
        try {
            const user_record = await pool.query(
                "insert into record values (null, ?, ?, ?, ?, ?)",
                [300, date, meal_time, food_name, food_amount]
            );
            return res.redirect('/listfood');
        } catch (error) {
            return res.render('error')
        }
    }
}

module.exports = record;
