const { request } = require("express");
const { range } = require("express/lib/request");
const { json } = require("express/lib/response");
const httpStatus = require("http-status-codes");
const pool = require("../middleware/db");

class nutrition {
    // 음식 영양소 조회
    async Inquery(req, res) {
        try {
            const { food_name, opt1 } = req.body;
            var query = food_name + "%";
            if(opt1 == "checked"){
                query = "%" + query;
            }
            const food = await pool.query(
                "select * from food2 where food_name like ? order by food_name asc limit 0 , 30",
                [query]
            );
            return res.render('addfood', {food: food[0]});
        } catch (error) {
            return res.redirect('/');
            throw error;
        }
    }

    //영양소 추천
    async Recommend(req, res) {
        try {
            const { user_id } = req.params;

            const recommend_food = await pool.query(
                "select food_name from food2 where (tan <= 30 )and (dan >= 60 ) and (fat >=30) limit 1,3 "
            );

            return res.send(recommend_food[0]);
        } catch (error) {
            return res.redirect('/');
            throw error;
        }
    }
}
module.exports = nutrition;
