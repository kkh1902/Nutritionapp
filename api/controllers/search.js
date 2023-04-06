const { request } = require("express");
const { range } = require("express/lib/request");
const { json } = require("express/lib/response");
const httpStatus = require("http-status-codes");
const pool = require("../middleware/db");

class search {
    // 음식 영양소 조회
    async Read(req, res) {
        try {
            const { food_name, opt1 } = req.body;
            var query = food_name + "%";
            if(opt1 == "checked"){
                query = "%" + query;
            }
            const food = await pool.query(
                "select * from food2 where food_name like ? order by food_name asc limit 0 , 10",
                [query]
            );
            console.log(food);
            return res.render('searchfood', {food: food[0]});
        } catch (error) {
            return res.redirect('/');
        }
    }
}
module.exports = search;
