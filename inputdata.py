# Python3 샘플 코드 #

import pprint
import requests
import json
import mysql.connector
import pymysql

# url = 'http://apis.data.go.kr/1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1'
# params ={'serviceKey' : 'dfOMVz38LJCKErVnUoP7iuHRhHVCi0JuNrKmV4O7dvgnAtNLFhJKt2pcoyk7pzpiX9jKnIe8aD4IPekonkXiMQ==', 'desc_kor' : '', 'pageNo' : '', 'numOfRows' : '1', 'bgn_year' : '', 'animal_plant' : '', 'type' : 'json' }

# response = requests.get(url, params=params)
# # print(response.content)

# pp = pprint.PrettyPrinter(indent=4)
# print(pp.pprint(response.content)) # tab씌워서 조금 이쁘게 출력해라

# json_ob = json.loads(response.content)
# print(json_ob)   # json 형태로 깔끔하게 출력해라
# print(type(json_ob)) 

# food_name = json_ob['body']['items'][0]['DESC_KOR']
# food_once = json_ob['body']['items'][0]['SERVING_WT'] # json 형태에서 body 값만 가져와라
# kcal = json_ob['body']['items'][0]['NUTR_CONT1']
# tan = json_ob['body']['items'][0]['NUTR_CONT2']
# dan = json_ob['body']['items'][0]['NUTR_CONT3']
# fat = json_ob['body']['items'][0]['NUTR_CONT4']
# sugar = json_ob['body']['items'][0]['NUTR_CONT5']
# salt = json_ob['body']['items'][0]['NUTR_CONT6']

# print(food_name)
# print(food_once)
# print(kcal)
# print(tan)
# print(dan)
# print(fat)
# print(sugar)
# print(salt)





# mydb = pymysql.connect(
#     host="localhost",
#     user="root",
#     passwd="1234",
#     database="nutrition"
# )

# sql = "INSERT INTO food (food_name,food_once,kcal,tan,dan,fat,sugar,salt) values (%s,%s,%s,%s,%s,%s,%s,%s) "

# with mydb:
#     with mydb.cursor() as cur:
#         cur.execute(sql,(food_name,food_once,kcal,tan,dan,fat,sugar,salt))
#         mydb.commit()


A=22700
for i in range(22700,23000):
    

    
    url = 'http://apis.data.go.kr/1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1'
    params ={'serviceKey' : 'dfOMVz38LJCKErVnUoP7iuHRhHVCi0JuNrKmV4O7dvgnAtNLFhJKt2pcoyk7pzpiX9jKnIe8aD4IPekonkXiMQ==', 'desc_kor' : '', 'pageNo' : A, 'numOfRows' : '1', 'bgn_year' : '', 'animal_plant' : '', 'type' : 'json' }

    response = requests.get(url, params=params)
    
    try:
        json_ob = json.loads(response.content)
    


        food_name = json_ob['body']['items'][0]['DESC_KOR']
        food_once = json_ob['body']['items'][0]['SERVING_WT'] # json 형태에서 body 값만 가져와라
        kcal = json_ob['body']['items'][0]['NUTR_CONT1']
        tan = json_ob['body']['items'][0]['NUTR_CONT2']
        dan = json_ob['body']['items'][0]['NUTR_CONT3']
        fat = json_ob['body']['items'][0]['NUTR_CONT4']
        sugar = json_ob['body']['items'][0]['NUTR_CONT5']
        salt = json_ob['body']['items'][0]['NUTR_CONT6']

        sys = pymysql.connect(
        host="localhost",
        user="root",
        passwd="1234",
        database="sys"
        )

        sql = "INSERT INTO food (food_name,food_once,kcal,tan,dan,fat,sugar,salt) values (%s,%s,%s,%s,%s,%s,%s,%s)"

        

        with sys:
            with sys.cursor() as cur:
                cur.execute(sql,(food_name,food_once,kcal,tan,dan,fat,sugar,salt))
                sys.commit()
        A+=1
    
    except ValueError:
        print("-1")