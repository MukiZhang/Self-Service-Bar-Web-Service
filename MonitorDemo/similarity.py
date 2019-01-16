# from PIL import Image
# import cv2
import numpy as np
from scipy.misc import imread

def ImageToMatrix(img):
	# img = imread(filename)
	img = img.astype('int16')
	return img

def similarity(image1, image2):
	matrix1 = ImageToMatrix(image1)
	matrix2 = ImageToMatrix(image2)
	sub = abs(matrix1 - matrix2)
	sub[sub <= 15] = 1
	sub[sub > 15] = 0
	return np.sum(sub) / np.size(sub)

# def ImageResize(filename, size=(720, 1280)):
#     img = imread(filename)
#     print(img.shape)
#     re_img = imresize(img, size)
#     re_img = re_img.astype('int16')
#     return re_img

# def mode(matrix1, matrix2):
#     sub = matrix1 - matrix2
#     r = sub[:,:,0]
#     m = stats.mode(r)


# matrix1 = ImageToMatrix("1.jpg")
# matrix2 = ImageToMatrix("2.jpg")
# matrix3 = ImageToMatrix("3.jpg")

# re_mtx1 = ImageResize('1.jpg')
# re_mtx2 = ImageResize('2.jpg')
# re_mtx3 = ImageResize('3.jpg')
# # sim(matrix1, matrix2)
# # sim(matrix2, matrix3)
# ticks_begin = time.time()
# s = sim(matrix1, matrix2)
# ticks_end = time.time()
# print('img: 1  2     time: ', ticks_end - ticks_begin, '    sim: ', s )

# ticks_begin = time.time()
# s = sim(re_mtx1, re_mtx2)
# ticks_end = time.time()
# print('img: r1  r2     time: ', ticks_end - ticks_begin, '    sim: ', s )

# ticks_begin = time.time()
# s = sim(matrix2, matrix3)
# ticks_end = time.time()
# print('img: 2  3     time: ', ticks_end - ticks_begin, '    sim: ', s )

# ticks_begin = time.time()
# s = sim(re_mtx2, re_mtx3)
# ticks_end = time.time()
# print('img: r2  r3     time: ', ticks_end - ticks_begin, '    sim: ', s )

# print(matrix1[0,0,:], matrix2[0,0,:], m[0,0,:])
