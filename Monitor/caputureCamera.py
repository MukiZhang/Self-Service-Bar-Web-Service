import requests
import json
import urllib.request
from config import *

def caputureCamera(picName='curr.jpg', path=''):
	# caputure camera to get current picture
	# parameter:  picName:save the picture as picName, path: save path
	# no return

	postDataToken = {
		'appKey':appKey,
		'appSecret':appSecret,
	}

	# http access for accessToken
	responseToken = requests.post('https://open.ys7.com/api/lapp/token/get', data=postDataToken)
	responseData = json.loads(responseToken.text)
	accessToken = responseData['data']['accessToken']

	postData = {
		'accessToken':accessToken,
		'deviceSerial':deviceSerial,
		'channelNo':'1'
	}

	# http access for caputure camera
	response = requests.post('https://open.ys7.com/api/lapp/device/capture', data=postData)
	responseData = json.loads(response.text)
	picUrl = responseData['data']['picUrl']

	# save picture
	if path != '':
		if picName == 'curr.jpg':
			urllib.request.urlretrieve(picUrl, path + '\\curr.jpg')
		else:
			urllib.request.urlretrieve(picUrl, path + '\\init.jpg')
	else:
		if picName == 'curr.jpg':
			urllib.request.urlretrieve(picUrl, 'curr.jpg')
		else:
			urllib.request.urlretrieve(picUrl, 'init.jpg')
