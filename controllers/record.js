const { request } = require("express");
const httpStatus = require("http-status-codes");
const pool = require("../middleware/db");

class record {
    // 사용자 영양소 조회
    async Inquery(req, res) {
        try {
            const { user_id } = req.params;
            const user_record = await pool.query(
                "select * from record where user_id=?",
                [user_id]
            );

            return res.send(user_record[0]);
        } catch (error) {
            return res.status(500).json(error);
        }
    }

    // 사용자 영양소 기록
    async Record(req, res) {
        try {
            const { user_id } = req.params;
            const {
                record_id,
                date,
                food,
                meal_time,
                tan,
                protein,
                fat,
                sugar,
                salt,
                kcal,
            } = req.body;

            const user_record = await pool.query(
                "insert into record(record_id, user_id, date, food, meal_time,tan,protein, fat, sugar, salt,kcal   ) values (?,?,?,?,?,?,?,?,?,?,?)",
                [
                    record_id,
                    user_id,
                    date,
                    food,
                    meal_time,
                    tan,
                    protein,
                    fat,
                    sugar,
                    salt,
                    kcal,
                ]
            );

            return res.send("success");
        } catch (error) {
            return res.status(500).json(error);
        }
    }
}

module.exports = record;
