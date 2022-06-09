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

            if (gender == "man") {
                const bmr = 66.47 + (13.75 * weight + 5 * height - 6.76 * age);
                let bmr2 = Number(Math.round(bmr))
            } else if (gender == "woman") {
                const bmr = 655.1 + (9.56 * weight + 1.85 * height - 4.68 * age);
                let bmr2 = Number(Math.round(bmr))
            }

            const tan2 = Number(Math.round(bmr2 / 5))
            const dan2 = Number(Math.round(bmr2 / 2))
            const fat2 = Number(Math.round(bmr2 / 3))


            const bmr = await pool.query(
                "update user set bmr =? ,tan2=? ,dan2=? fat2=? where user_id=?",
                [bmr2, tan2, dan2, fat2, user_id]
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
