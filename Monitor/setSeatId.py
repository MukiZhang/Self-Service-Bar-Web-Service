from tkinter import *

global root
global L1
global seat_id
global ID

def dialog(flag=1):
	global root
	global seat_id
	global ID

	root = Tk()
	if flag == 1:
		root.title("请输入需要增加或修改座位的ID")
	elif flag == 0:
		root.title("请输入需要删除的座位的ID")
	root.geometry('500x200')   # 是x不是*

	L1 = Label(root, text="seat_id:")
	L1.pack()
	seat_id = StringVar()
	seat = Entry(root, textvariable=seat_id)
	seat_id.set('')
	seat.pack()

	Button(root, text='确认', command = on_click).pack()
	root.mainloop()

	while ID is not None:
		return ID

def on_click():
	global root
	global seat_id
	global ID
	ID = seat_id.get()
	root.destroy()

if __name__ == '__main__':
	dialog()