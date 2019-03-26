import requests
seat_id = '1,2'
post_data={
    "seat_id":seat_id,
}
res=requests.post(url="http://127.0.0.1:5000/project",data=post_data)
print(res.text)