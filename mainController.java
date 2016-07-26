import static java.nio.file.StandardWatchEventKinds.*;

public class mainController {

    public static void main(String[] args){
		mainController mc = new mainController();
		mc.controller();
    }

    public void controller(){
			WatchService watcher = FileSystems.getDefault().newWatchService();
			Path dir = "trap_commands/";
			try{
				WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
			} catch (IOException x){
				System.err.println(x);
			}
			
			while(true){
				WatchKey key;
				try{
					//wait for a key to be available
					key = watcher.take();
				} catch (InterruptedException ex){
					return;
				}
				
				for(WatchEvent<?> event : key.pollEvents()){
					//get event type
					WatchEvent.Kind<?> kind = event.kind();
					
					if(kind == ENTRY_MODIFY){
						trap system_trap = new trap();
						system_trap.checkValidity();
					}
				}
				
				boolean valid = key.reset();
				if(!valid){
					break;
				}
			}
    }
}
