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
            const { date, food, meal_time } = req.body;

            const foodrecord = await pool.query(
                "insert into record(user_id, date, food, meal_time) values(?, ?, ?, ?)",
                [user_id, date, food, meal_time]
            )

            //
            const tan = await pool.query("select tan from food2 where food_name=?", [
                food
            ])
            const tan2 = tan[0][0]['tan']

            //
            const dan = await pool.query("select dan from food2 where food_name=?", [
                food
            ])
            const dan2 = dan[0][0]['dan']

            //
            const fat = await pool.query("select fat from food2 where food_name=?", [
                food
            ])
            const fat2 = fat[0][0]['fat']

            //
            const sugar = await pool.query("select sugar from food2 where food_name=?", [
                food
            ])
            const sugar2 = sugar[0][0]['sugar']

            //
            const salt = await pool.query("select salt from food2 where food_name=?", [
                food
            ])
            const salt2 = salt[0][0]['salt']

            //
            const kcal = await pool.query("select kcal from food2 where food_name=?", [
                food
            ])
            const kcal2 = kcal[0][0]['kcal']

            //

            if (meal_time == "아침") {

                const foodrecord2 = await pool.query(
                    "update record set tan=? , dan=?, fat=?, sugar=?, salt=? , kcal=? where user_id=? and meal_time=?",
                    [tan2, dan2, fat2, sugar2, salt2, kcal2, user_id, meal_time]

                );

            } else if (meal_time == "점심") {

                const foodrecord3 = await pool.query(
                    "update record set tan=? , dan=?, fat=?, sugar=?, salt=? , kcal=? where user_id=? and meal_time=?",
                    [tan2, dan2, fat2, sugar2, salt2, kcal2, user_id, meal_time]

                );
            } else if (meal_time == "저녁") {

                const foodrecord4 = await pool.query(
                    "update record set tan=? , dan=?, fat=?, sugar=?, salt=? , kcal=? where user_id=? and meal_time=?",
                    [tan2, dan2, fat2, sugar2, salt2, kcal2, user_id, meal_time]

                );
            }


            const record_information = await pool.query(
                "select * from record where user_id=?",
                [user_id]
            );


            return res.send(record_information[0]);
        } catch (error) {
            return res.status(500).json(error);
        }
    }
}

module.exports = record;
