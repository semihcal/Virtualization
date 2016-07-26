import time
import os
import filecmp

#loop code to check for differences in log.txt and baselog.txt
#baselog.txt is the last known changes in log.txt
while 1:
	if filecmp.cmp('trap_commands/log.txt', 'trap_commands/baselog.txt') == False:
    		#if the file has changed, create a trap for the request.
    		os.system("java mainController")
		#add the newest change from log.txt to baselog.txt so we don't get stuck in an infinite loop of trap creation.	
		with open("trap_commands/log.txt") as f:
    			with open("trap_commands/baselog.txt", "w") as f1:
       				for line in f:
                				f1.write(line)
	
