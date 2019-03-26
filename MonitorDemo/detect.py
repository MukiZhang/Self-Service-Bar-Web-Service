import os
import cv2
import configparser
from set_loc import *
from similarity import *
from caputureCamera import *
import time
import sys
from config import *

global seat_ids_loc
seat_ids_loc = None

def getLoc(seat_id):
	global seat_ids_loc
	# config = configparser.ConfigParser()
	# config.read('config.ini') 
	# 补充seat_id 是否存在于config.ini的报错信息
	if seat_id not in seat_ids_loc.keys():
		print('座位ID不存在')
		return None

	s = seat_ids_loc[seat_id].replace(' ', '').replace('(', '').replace(')', '').split(',')
	a = []
	for item in s:
		a.append(float(item))
	point1 = (a[0], a[1])
	point2 = (a[2], a[3])
	return point1, point2

def subImage(image, point1, point2):
	print('in subImage')
	print('point1', point1)
	min_x = int(min(point1[0],point2[0]))
	min_y = int(min(point1[1],point2[1]))
	width = int(abs(point1[0] - point2[0]))
	height = int(abs(point1[1] -point2[1]))
	print('in subImage')
	print('min_x', min_x, min_y, width, height)
	# print(min_y, min_y+height, min_x, min_x+width)
	cutImg = image[min_y:min_y+height, min_x:min_x+width]
	print('after subImage')
	return cutImg

# def itemLeft(seat_id):
# 	print('in single seat_id')
# 	if getLoc(seat_id) == None:
# 		return True
# 	point1, point2 = getLoc(seat_id)
# 	# print('point1', point1, 'point2', point2)
# 	initImage = cv2.imread('init.jpg')

# 	# 从摄像头捕获当前截图,保存为curr.jpg
# 	caputureCamera()
# 	currImage = cv2.imread('curr.jpg')

# 	initCutImage = subImage(initImage, point1, point2)
# 	currCutImage = subImage(currImage, point1, point2)

# 	sim = similarity(initCutImage, currCutImage)

# 	# 根据测试结果设定threshold
# 	threshold = 0.85
# 	if sim >= threshold:
# 		print('sim:', sim)
# 		return True
# 	else:
# 		print('sim:', sim)
# 		return False

	# print('sim:', sim)

def itemLeft(seat_id_list):
	
	# print('in itemLeft list')
	print('in itemLeft list')
	sim_list = []
	# 从摄像头捕获当前截图,保存为curr.jpg
	start = time.time()
	print('before caputureCamera')
	caputureCamera()
	print('after caputureCamera')
	end = time.time()
	print('caputure camera time:', end-start)

	for seat_id in seat_id_list:
		if getLoc(seat_id) == None:
			continue
		point1, point2 = getLoc(seat_id)
		print('point1', point1, 'point2', point2)
		print("pythonPath:",pythonPath)
		print(pythonPath + '\\curr.jpg')



		initImage = cv2.imread(pythonPath + '\\init.jpg')
		currImage = cv2.imread(pythonPath + '\\curr.jpg')

		# testImage = currImage[296:296+220,642:642+92]
		# test2Image = initImage[296:296+220,642:642+92]
		# cv2.show(testImage)
		print('in get image')

		min_x = int(min(point1[0],point2[0]))
		min_y = int(min(point1[1],point2[1]))
		width = int(abs(point1[0] - point2[0]))
		height = int(abs(point1[1] -point2[1]))
		print('min_x', min_x, min_y, width, height)

		currCutImage = currImage[min_y:min_y+height, min_x:min_x+width]

		print('out get image')
		# cv2.imshow('img', currCutImage)
		# cv2.waitKey()
		
		# cv2.imshow('img', initImage)
		# cv2.waitKey()
		initCutImage = initImage[min_y:min_y+height, min_x:min_x+width]
		print('out get image')

		
		# initCutImage = subImage(initImage, point1, point2)
		# currCutImage = subImage(currImage, point1, point2)

		sim = similarity(initCutImage, currCutImage)
		# 根据测试结果设定threshold
		
		sim_list.append(sim)
	print(sim_list)
	threshold = 0.88
	for sim in sim_list:
		if sim < threshold:
			return False
	return True

# print(itemLeft(['11008', '11009', '11009']))
# print(itemLeft(['1', '2', '1', '1', '1']))

def main():
	seat_ids_string = sys.argv[1]
	print(seat_ids_string)
	# print('seat_ids_string', seat_ids_string)
	seat_ids = seat_ids_string.split()
	# print('seat_ids', seat_ids)
	print(seat_ids)
	seat_ids_loc_string = sys.argv[2]
	print(seat_ids_loc_string)
	global seat_ids_loc
	seat_ids_loc = {}
	seat_ids_loc_string = seat_ids_loc_string.split('\n')
	print('seat_ids_loc_string',seat_ids_loc_string)
	for _ in seat_ids_loc_string:
		seat_id = _.split(' = ')[0]
		print('seat_id',seat_id)
		seat_loc_string = _.split(' = ')[1]
		print(seat_loc_string)
		# seat_locs = seat_loc_string.split(',')
		# seat_locs_int = []
		# for loc in seat_locs:
		# 	seat_locs_int.append(int(loc))

		seat_ids_loc[seat_id] = seat_loc_string
	print(seat_ids_loc)
	print(itemLeft(seat_ids))
	print('1')


if __name__ == '__main__':
	main()
