const { request } = require("express");
const httpStatus = require("http-status-codes");
const pool = require("../middleware/db");

class user {
    //바디 스펙 조회

    async Inquery(req, res) {
        try {
            const { user_id } = req.params;

            const user_information = await pool.query(
                "select * from user where user_id=?",
                [user_id]
            );
            return res.send(user_information[0][0]);
        } catch (error) {
            return res.status(500).json(error);
        }
    }

    // 바디 스펙 기록
    async spec(req, res) {
        try {
            const { user_id } = req.params;
            const { gender, weight, height, age } = req.body;

            const user_spec = await pool.query(
                "insert into user(user_id, gender, weight, height, age) values (?, ?, ?, ?, ?) ",
                [user_id, gender, weight, height, age]
            );

            const user_information = await pool.query(
                "select * from user where user_id=?",
                [user_id]
            );

            return res.send(user_information[0][0]);
        } catch (error) {
            return res.status(500).json(error);
        }
    }

    // 바디 스펙 수정
    async modifyspec(req, res) {
        try {
            const { user_id } = req.params;
            const { gender, weight, height, age } = req.body;

            const userUpdate = await pool.query(
                "update user set gender=?, weight=?, height=?, age=? where user_id=?",
                [gender, weight, height, age, user_id]
            );

            const user_information = await pool.query(
                "select * from user where user_id=?",
                [user_id]
            );

            return res.send(user_information[0][0]);
        } catch (error) {
            return res.status(500).json(error);
        }
    }

    //바디 스펙 삭제
    async delete(req, res) {
        try {
            const { user_id } = req.params;
            const userDelete = await pool.query(
                "delete from user where user_id=?",
                [user_id]
            );
            const user_information = await pool.query("select * from user");

            return res.send(user_information[0][0]);
        } catch (error) {
            return res.status(500).json(error);
        }
    }
}
module.exports = user;
