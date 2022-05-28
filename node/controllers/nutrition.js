const { request } = require("express");
const httpStatus = require("http-status-codes");
const pool = require("../middleware/db");

class nutrition {
    // 음식 영양소 조회
    async Inquery(req, res) {
        try {
            const { food_name } = req.params;
            const food = await pool.query(
                "select  food_name , kcal, carbohydrate , protein, fat, sugar, sodium, colesterol from food where food_name=?"[
                    food_name
                ] // 문제 db에 저장할때 필요한 것만 가져와서 select *로 조회
            );

            return res.send(food[0]);
        } catch (error) {
            return res.status(500).json(error);
        }
    }

    //사용자 영양소 기록
    async Record(req, res) {
        try {
            const { user_id } = req.params;
            const { user_food1, user_food2, user_food3 } = req.body;

            const user_record = await pool.query(
                "insert into record(user_id, user_food1, user_food2, user_food3) values (?,?,?,?)",
                [user_id, user_food1, user_food2, user_food3]
            );

            return res.send("success");
        } catch (error) {
            return res.status(500).json(error);
        }
    }

    //영양소 추천 (알고리즘(스펙))  // 해야함 !!
    async Recommend(req, res) {
        try {
            return res.send("s");
        } catch (error) {
            return res.status(500).json(error);
        }
    }

    //사용자 영양소 조회
    async recordInquery(req, res) {
        try {
            const user_food = await pool.query("select * from record ");

            return res.send(user_food[0]);
        } catch (error) {
            return res.status(500).json(error);
        }
    }
}
module.exports = nutrition;

// android 연동해서 정보주는 것 까지
// mysql에 원하는 값만 가져와서 넣기 DB
