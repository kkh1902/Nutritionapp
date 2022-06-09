const { request } = require("express");
const httpStatus = require("http-status-codes");
const pool = require("../middleware/db");

class nutrition {
    // 음식 영양소 조회
    async Inquery(req, res) {
        try {
            const { food_name } = req.params;
            const food = await pool.query(
                "select * from food where food_name=?",
                [food_name] // 문제 db에 저장할때 필요한 것만 가져와서 select *로 조회
            );

            return res.send(food[0]);
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
}
module.exports = nutrition;
