from detect import *
from flask import Flask, request
import json
app=Flask(__name__)

@app.route('/project', methods=['POST', 'get'])
def project():
	if request.method=='GET':
		return '0'
	if request.method=='POST':
		data = request.get_data()
		json_data = json.loads(data)
		seat_id = json_data.get('seatId')
		print(seat_id)
		re = itemLeft(seat_id)
		print(re)
		# return re
		if re == True:
			return '1'
		else:
			return '0'
app.run(host="0.0.0.0",debug=True, port=5000)