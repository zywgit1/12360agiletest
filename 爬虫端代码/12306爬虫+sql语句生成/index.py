# coding:utf-8
# 首先，要获取数据，就得知道12306的接口，而拿到接口就要使用抓包的方式：可使用浏览器自带的检查功能或fiddler
# 获取的接口为：Request URL: https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=2018-08-03&
# leftTicketDTO.from_station=LMH&leftTicketDTO.to_station=HZH&purpose_codes=ADULT
# leftTicketDTO.train_date=2018-08-03：查询日期；
# leftTicketDTO.from_station=LMH：出发地
# leftTicketDTO.to_station=HZH&purpose_codes=ADULT：目的地
# purpose_codes=ADULT：车票类型
# 因为URL中地点都是字母，所以要拿到所有车站信息列表才能构造请求，查看源代码获取：
# https://kyfw.12306.cn/otn/resources/js/framework/station_name.js?station_version=1.9059
import random
import re# coding:utf-8
# 首先，要获取数据，就得知道12306的接口，而拿到接口就要使用抓包的方式：可使用浏览器自带的检查功能或fiddler
# 获取的接口为：Request URL: https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=2018-08-03&
# leftTicketDTO.from_station=LMH&leftTicketDTO.to_station=HZH&purpose_codes=ADULT


# leftTicketDTO.train_date=2018-08-03：查询日期；
# leftTicketDTO.from_station=LMH：出发地
# leftTicketDTO.to_station=HZH&purpose_codes=ADULT：目的地
# purpose_codes=ADULT：车票类型


# 因为URL中地点都是字母，所以要拿到所有车站信息列表才能构造请求，查看源代码获取：
# https://kyfw.12306.cn/otn/resources/js/framework/station_name.js?station_version=1.9059

import json
import re
import requests
import pprint
import json
import prettytable as pt
# from __future__ import unicode_literals
import sys

def fun(list):
    colname = ['train_number', 'start_station', 'reach_station','start_time','reach_time','elapsed_time','first_class','second_class','hard_seat','none_seat','price','total_time','date']
    dist = {}
    index = 0
    for item in colname:
        if list[index] == '无' or list[index] == '' or list[index] == None:
            list[index] = '0'
        dist[item] = list[index]

        index += 1
    return dist


# 打开字典：
f = open('station_names.txt', 'r',encoding="utf-8")
a = f.read()
station_name = eval(a)
f.close()

# start = input("起始点：")
start = "苏州"
start_eng = station_name[start]

# end = input("目的地：")
end = "北京"
end_eng = station_name[end]

# date = input("出发时间：")
date = "2019-12-13"
d = str(date)
print("正在查询...")

# 匹配网址：
url = 'https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=' + d + '&leftTicketDTO.from_station=' + start_eng \
      + '&leftTicketDTO.to_station=' + end_eng + '&purpose_codes=ADULT'

headers={
    'User-Agent':'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:68.0) Gecko/20100101 Firefox/68.0',
    'Accept':"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    'Accept-Encoding':"gzip, deflate, br",
    'Accept-Language':"zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2",
    'Connection':"keep-alive",
    'Cookie':'JSESSIONID=CCA79B4DD0FC71356BD3289345F0534D; _jc_save_wfdc_flag=dc; RAIL_EXPIRATION=1575874942351; RAIL_DEVICEID=AlqTA6S9GQOKXbigtdNyxW86zH1q7ai7p_Iy7XUDBm60DufBqzXwkoPjX4oIRQ9rGH3UYXKrstnuza59guHY6ZwGP0-CBmwfw57kxwKOq6eDzomKmP7ek8T7K3f0wt2sTcVKtJ40aNpX42d_Q03DUn11Hkb8_8Qv; _jc_save_toDate=2019-12-07; BIGipServerpool_passport=300745226.50215.0000; route=495c805987d0f5c8c84b14f60212447d; BIGipServerotn=737149450.24610.0000; _jc_save_fromStation=%u5F90%u5DDE%2CXCH; _jc_save_toStation=%u91CD%u5E86%2CCQW; _jc_save_fromDate=2019-12-07',
    'Host':"kyfw.12306.cn",
    'Upgrade-Insecure-Requests':"1"

}
s= requests.Session()
r = s.get(url, headers=headers)  # 传回的是json对象上海
print(r.cookies._cookies)
# print(r.status_code)
# pprint.pprint(r.text)
# print(r.text)

results = r.json()['data']['result']



table = pt.PrettyTable(encoding=sys.stdout.encoding)
# split() 方法通过指定分隔符对字符串进行分割并返回一个列表，默认分隔符为所有空字符，包括空格、换行(\n)、制表符(\t)等：
table.field_names = (["车次", "起始车站", "终点站", "出发地", "目的地", "开始时间", "到达时间", "经历时间", "一等座",
                      "二等座", "硬座", "无座"])

res = []

for result in results:
    try:
        data_list = result.split("|")
        station_train_code = data_list[3]
        # from_station = data_list[4]
        # to_station = data_list[5]

        # 由value值获取key值：
        from_station = list(station_name.keys())[list(station_name.values()).index(data_list[4])]
        to_station = list(station_name.keys())[list(station_name.values()).index(data_list[5])]
        start_station = list(station_name.keys())[list(station_name.values()).index(data_list[6])]
        end_station = list(station_name.keys())[list(station_name.values()).index(data_list[7])]
        start_time = data_list[8]
        arrive_time = data_list[9]
        total_time = data_list[10]
        one_seat = data_list[-6]
        second_seat = data_list[-7]
        yingzuo = data_list[-11]
        no_seat = data_list[-8]
        price = random.randint(14,300)
        tt = fun([station_train_code, start_station, end_station, start_time, arrive_time,total_time, one_seat, total_time, yingzuo, no_seat, '%s'%price,total_time,date])
        res.append(tt)

        """
        table.add_row([station_train_code, from_station, to_station, start_station, end_station, start_time, arrive_time,
                       # total_time, one_seat, second_seat, no_seat, yingzuo])
        """

    except ValueError:
        pass

with open("result.json",'w') as f:
    json.dump(res,f)
    f.close()
# table.align = 'c'
# table.set_style(pt.PLAIN_COLUMNS)
# print(len(results))
# print(table)


