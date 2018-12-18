import sys
from init import *

if len(sys.argv) >=2:
	if sys.argv[1] == 'addSeat':
		addSeat()
	elif sys.argv[1] == 'delSeat':
		delSeat()
	elif sys.argv[1] == 'modifySeat':
		modifySeat()