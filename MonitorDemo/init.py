import os
import cv2
import configparser
from set_loc import *
from seat_id import *
from similarity import *
from caputureCamera import *
import time

# 保存当前图片为init.jpg
def initImage():
	caputureCamera(0)

# 将seatID对应得point1, point2写入config.ini
def writeSeatLoc(seatID, point1, point2):
	config = configparser.ConfigParser()
	config.read('config.ini')
	# config.add_section('seatLoc')
	config.set('seatLoc', seatID, str(point1)+',' +str(point2))
	config.write(open('config.ini', 'w+'))

# 初始化座位坐标
def initSeat():
	seatDict = set_loc('init.jpg')
	for key in seatDict.keys():
		writeSeatLoc(key, seatDict[key][0], seatDict[key][1])

# 座位增加功能
def addSeat():
	seatDict = set_loc('init.jpg')
	for key in seatDict.keys():
		writeSeatLoc(key, seatDict[key][0], seatDict[key][1])

# 座位修改功能
def modifySeat():
	seatDict = set_loc('init.jpg')
	for key in seatDict.keys():
		writeSeatLoc(key, seatDict[key][0], seatDict[key][1])

# 座位删除功能
def delSeat():
	seatID = dialog(0)
	config = configparser.ConfigParser()
	config.read('config.ini')
	if seatID not in config['seatLoc'].keys():
		print('要删除的座位ID不存在')
	else:
		config.remove_option('seatLoc', seatID)
		config.write(open('config.ini','w'))

def init():
	initImage()
	initSeat()
