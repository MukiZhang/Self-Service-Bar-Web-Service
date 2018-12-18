import os
import cv2
import time
import sys
import configparser
from setSeatLoc import *
from similarity import *
from caputureCamera import *
from config import *

global seat_ids_loc
seat_ids_loc = None

def getLoc(seat_id):
	# get point1 and point2 of seat_id
	# parameter:seat_id
	# return: point1 and point2 of seat_id
	global seat_ids_loc
	if seat_id not in seat_ids_loc.keys():
		print('seat ', seat_id, ' does not exist')
		return None

	points = seat_ids_loc[seat_id].replace(' ', '').replace('(', '').replace(')', '').split(',')
	points_float = []
	for point in points:
		points_float.append(float(point))
	point1 = (points_float[0], points_float[1])
	point2 = (points_float[2], points_float[3])
	return point1, point2

def subImage(image, point1, point2):
	# get the sub-image of image, using point1 and point2 to locate
	# parameter:image: raw image, point1 point2:two vertices of the sub-image
	
	min_x = int(min(point1[0],point2[0]))
	min_y = int(min(point1[1],point2[1]))
	width = int(abs(point1[0] - point2[0]))
	height = int(abs(point1[1] -point2[1]))
	cutImg = image[min_y:min_y+height, min_x:min_x+width]
	return cutImg


def isItemLeft(seat_id_list):
	# judge there is item left in seat
	# parameter: seat_id_list, [1, 2, 3]
	# return: True, False
	# True: item left
	# False: no item left

	java_workpath = os.getcwd()
	python_workpath = java_workpath + '\\Monitor'

	# caputure current picture from camera, save as curr.jpg
	start = time.time()
	caputureCamera(picName='curr.jpg', path=python_workpath)
	end = time.time()
	print('the running time of caputuring camera(about 3s):', end-start, 's')

	sim_list = []
	for seat_id in seat_id_list:
		if getLoc(seat_id) == None:
			continue
		point1, point2 = getLoc(seat_id)

		initImage = cv2.imread(python_workpath + '\\init.jpg')
		currImage = cv2.imread(python_workpath + '\\curr.jpg')

		'''
		min_x = int(min(point1[0],point2[0]))
		min_y = int(min(point1[1],point2[1]))
		width = int(abs(point1[0] - point2[0]))
		height = int(abs(point1[1] -point2[1]))

		initCutImage = initImage[min_y:min_y+height, min_x:min_x+width]
		currCutImage = currImage[min_y:min_y+height, min_x:min_x+width]
		'''

		initCutImage = subImage[initImage, point1, point2]
		currCutImage = subImage[currImage, point1, point2]
		
		sim = similarity(initCutImage, currCutImage)
		sim_list.append(sim)
	print('sim_list:', sim_list)

	for sim in sim_list:
		if sim < threshold_sim:
			return False
	return True


def main():
	# java command to excute python program: python detect.py seat_ids_string seat_ids_loc_string
	# seat_ids_string: 1003 1004 1005
	# seat_ids_loc_string:11003 = (642, 296),(734, 516)\n11004 = (495, 204),(624, 320)\n11005 = (283, 134),(440, 251)\n

	# process for seat_ids_string
	seat_ids_string = sys.argv[1]
	seat_ids = seat_ids_string.split()
	print('seat_ids(is a list):', seat_ids)

	# process for seat_ids_loc
	global seat_ids_loc
	seat_ids_loc = {}
	seat_ids_loc_string = sys.argv[2]
	seat_ids_loc_string = seat_ids_loc_string.split('\n')
	for _ in seat_ids_loc_string:
		seat_id = _.split(' = ')[0]
		seat_loc_string = _.split(' = ')[1]
		seat_ids_loc[seat_id] = seat_loc_string
	print('seat_ids_loc(is a dict):', seat_ids_loc)
	result = isItemLeft(seat_ids)
	print('Monitor result(is item lefr, True or False):', result)

	# return to java using print    0:item left, 1:no item left
	if result == True:
		print('0')
	else:
		print('1')
	
if __name__ == '__main__':
	main()
