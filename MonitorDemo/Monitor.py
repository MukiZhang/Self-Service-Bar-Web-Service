import os
import cv2
import configparser
from set_loc import *
from similarity import *
from caputureCamera import *
import time

def initImage():
	caputureCamera(0)

def getLoc(seat_id):
	config = configparser.ConfigParser()
	config.read('config.ini') 

	s = config.get('seatLoc', seat_id).replace(' ', '').replace('(', '').replace(')', '').split(',')
	a = []
	for item in s:
		a.append(float(item))
	point1 = (a[0], a[1])
	point2 = (a[2], a[3])
	return point1, point2


def subImage(image, point1, point2):
	min_x = int(min(point1[0],point2[0]))
	min_y = int(min(point1[1],point2[1]))
	width = int(abs(point1[0] - point2[0]))
	height = int(abs(point1[1] -point2[1]))
	print(min_y, min_y+height, min_x, min_x+width)
	cutImg = image[min_y:min_y+height, min_x:min_x+width]
	return cutImg


def writeSeatLoc(seatID, point1, point2):
	config = configparser.ConfigParser()
	config.read('config.ini')
	# config.add_section('seatLoc')
	config.set('seatLoc', seatID, str(point1)+',' +str(point2))
	config.write(open('config.ini', 'w+'))


def itemLeft(seat_id):
	point1, point2 = getLoc(seat_id)
	print('point1', point1, 'point2', point2)
	initImage = cv2.imread('init.jpg')

	caputureCamera()
	currImage = cv2.imread('curr.jpg')

	initCutImage = subImage(initImage, point1, point2)
	currCutImage = subImage(currImage, point1, point2)

	sim = similarity(initCutImage, currCutImage)
	print('sim:', sim)

def initSeat():
	seatDict = set_loc('init.jpg')
	for key in seatDict.keys():
		writeSeatLoc(key, seatDict[key][0], seatDict[key][1])

initImage()
initSeat()

time.sleep(5)
itemLeft('1')
