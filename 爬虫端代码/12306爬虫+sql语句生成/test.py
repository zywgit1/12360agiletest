import json
import pymysql


def writesql(cursor,colname,item):
    sql = "insert into ticket (train_number,start_station,reach_station,start_time,reach_time,elapsed_time,first_class,second_class,hard_seat,none_seat,price,total_time,date) VALUES (%s);\n"
    str = ''
    for itt in colname:
       str +=  "'"+item[itt]+"'"+','
    str = str[:-1]
    sql = sql % str
    with open("result/load.sql",'a') as f:
        f.write(sql)
        f.close()

def main():
    colname = ['train_number', 'start_station', 'reach_station', 'start_time', 'reach_time', 'elapsed_time',
               'first_class', 'second_class', 'hard_seat', 'none_seat', 'price', 'total_time', 'date']
    with open("result.json",'r',encoding='utf-8') as f:
        new_dict = json.load(f)
        f.close()

      # 连接数据库
    connect = pymysql.Connect(
        host='localhost',
        port=3306,
        user='root',
        passwd='12345',
        db='ticket',
        charset='utf8'
    )

    # 获取游标
    cursor = connect.cursor()
    cursor = 0
    for item in new_dict:
        writesql(cursor,colname, item)


if __name__ == '__main__':
    main()

