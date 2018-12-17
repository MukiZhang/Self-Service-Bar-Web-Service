import cv2 
from seat_id import *

global img 
global point1, point2 
global set_loc

def on_mouse(event, x, y, flags, param): 
	global img, point1, point2, set_loc
	img2 = img.copy() 

	if event == cv2.EVENT_LBUTTONDOWN: #左键点击 
		point1 = (x,y) 
		cv2.circle(img2, point1, 10, (0,255,0), 5) 
		cv2.imshow('image', img2) 
	elif event == cv2.EVENT_MOUSEMOVE and (flags & cv2.EVENT_FLAG_LBUTTON): #按住左键拖曳 
		cv2.rectangle(img2, point1, (x,y), (255,0,0), 5) 
		cv2.imshow('image', img2) 
	elif event == cv2.EVENT_LBUTTONUP: #左键释放 
		point2 = (x,y) 
		cv2.rectangle(img2, point1, point2, (0,0,255), 5) 
		cv2.imshow('image', img2) 
		# cut_img = sub_image(img, point1, point2)
		# cv2.imwrite('lena3.jpg', cut_img) 
		ID = dialog()
		# print(ID)
		if point1[0] > point2[0]:
			point1, point2 = point2, point1

		set_loc[ID] = (point1, point2)

def sub_image(image, point1, point2):
	min_x = min(point1[0],point2[0]) 
	min_y = min(point1[1],point2[1]) 
	width = abs(point1[0] - point2[0]) 
	height = abs(point1[1] -point2[1]) 
	cut_img = imgage[min_y:min_y+height, min_x:min_x+width]
	return cut_img

def set_loc(filename): 
	global img, set_loc
	set_loc = {}   # 返回值，id , point1, point2
	img = cv2.imread(filename) 
	cv2.namedWindow('image') 
	cv2.setMouseCallback('image', on_mouse) 
	cv2.imshow('image', img) 
	if cv2.waitKey(0) == 27:
		print(set_loc)
		cv2.destroyAllWindows()
		return set_loc

if __name__ == '__main__':
	set_loc(filename)