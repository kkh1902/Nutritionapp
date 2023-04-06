const { request } = require("express");
const httpStatus = require("http-status-codes");
const pool = require("../middleware/db");

class cart {
  async Create(req, res) {
    const user_id = 300;
    const { food_name, food_amount, meal_time } = req.body;
    console.log(user_id, food_name, food_amount, meal_time);
    try {
      const user_record = await pool.query(
        "insert into cart values (null, ?, ?, ?, ?)",
        [user_id, meal_time, food_name, food_amount]
      );
      return res.redirect("/searchfood");
    } catch (error) {
      return res.redirect("/");
    }
  }

  async Read(req, res) {
    const user_id = 300;
    try {
      const cart_data = await pool.query(
        "select cart_id, meal_time, food2.food_name, food_once, kcal, tan, dan, fat from cart join food2 where cart.user_id=? and cart.food_name = food2.food_name;",
        [user_id]
      );
      console.log(cart_data[0]);
      return res.render("listfood", { cart_data: cart_data[0] });
    } catch (error) {
      return res.redirect("/");
    }
  }

  async Update(req, res) {
    try {
      
    } catch (error) {
      return res.redirect("/");
    }
  }

  async Delete(req, res) {
    const user_id = 300;
    const { cart_id } = req.body;
    try {
      const user_record = await pool.query(
        "delete from cart where user_id = ? and cart_id = ?",
        [user_id, cart_id]
      );
      return res.redirect("/listfood");
    } catch (error) {
      return res.redirect("/");
    }
  }
}

module.exports = cart;
