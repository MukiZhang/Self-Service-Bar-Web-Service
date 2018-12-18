import numpy as np
from scipy.misc import imread
from config import *

def matrixToInt(matrix):
	matrix = matrix.astype('int16')
	return matrix

def similarity(matrix1, matrix2):
	# calculate the similarity of two matrices
	# parameter: matrix1, matrix2
	# return: similarity
	
	matrix1 = matrixToInt(matrix1)
	matrix2 = matrixToInt(matrix2)
	sub = abs(matrix1 - matrix2)
	sub[sub <= threshold_matrix] = 1
	sub[sub > threshold_matrix] = 0
	return np.sum(sub) / np.size(sub)
