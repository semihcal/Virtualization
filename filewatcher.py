import time
import os
import filecmp


while 1:
	if filecmp.cmp('trap_commands/log.txt', 'trap_commands/baselog.txt') == False:
    		os.system("java mainController")
		with open("trap_commands/log.txt") as f:
    			with open("trap_commands/baselog.txt", "w") as f1:
       				for line in f:
                				f1.write(line)
	
