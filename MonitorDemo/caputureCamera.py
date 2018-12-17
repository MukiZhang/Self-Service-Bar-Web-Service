import requests
import json
import urllib.request
import configparser
from config import *

def caputureCamera(flag=1):
	print('in caputureCamera')
	config = configparser.ConfigParser()

	config.read('config.ini')

	# appKey = config.get('token', 'appKey')
	# appSecret = config.get('token', 'appSecret')
	# deviceSerial = config.get('token', 'deviceSerial')
	# appKey = 'df8fd3e3b272491d8fc852edced06d20'
	# appSecret = 'f5c395f36d5e7ae3c6d04d0d43ea9026'
	# deviceSerial = 'C51413178'
	# appKey = config.appKey
	# appSecret = config.appSecret
	# deviceSerial = config.deviceSerial
	print('last caputureCamera')
	postDataToken = {
		'appKey':appKey,
		'appSecret':appSecret,
	}

	responseToken = requests.post('https://open.ys7.com/api/lapp/token/get', data=postDataToken)
	temp = json.loads(responseToken.text)
	accessToken = temp['data']['accessToken']
	# print(accessToken)


	postData = {
		'accessToken':accessToken,
		'deviceSerial':deviceSerial,
		'channelNo':'1'
	}

	response = requests.post('https://open.ys7.com/api/lapp/device/capture', data=postData)


	# print(response.text)
	temp = json.loads(response.text)

	picUrl = temp['data']['picUrl']

	if flag == 1:
		urllib.request.urlretrieve(picUrl, 'curr.jpg')
	else:
		urllib.request.urlretrieve(picUrl, 'init.jpg')
	print('last caputureCamera')