package poc;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;

public class MyWatcher {

    @SuppressWarnings("rawtypes")
    public static void main(String[] strings) {

        Path myWatchPath = Paths.get("d:\\vishal");
        long preventDuplicateTime = 0;
        FileDelete onDelete = new FileDelete();//this object must be thread safe
        List<String> notifications = new ArrayList<String>();

        WatchService myPathWatchService = null;
        try {
            myPathWatchService = FileSystems.getDefault().newWatchService();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            myWatchPath.register(myPathWatchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean isKeyValid = true;
        while (isKeyValid) {
            WatchKey myPathWatchKey = null;
            try {
                myPathWatchKey = myPathWatchService.take();
            } catch (InterruptedException e) {
                e.printStackTrace();// throw
            }
                for (WatchEvent watchEvent : myPathWatchKey.pollEvents()) {
                    //WatchEvent.Kind kind = watchEvent.kind();
                    if (StandardWatchEventKinds.ENTRY_CREATE.equals(watchEvent
                            .kind())) {
                        String fileName = watchEvent.context().toString();
                        if(onDelete.status == -1)
                         System.out.println("File Created:" + fileName + " "
                                + watchEvent.context());
                        else{
                            if(onDelete.status == 0){
                                onDelete.createdTime = System.nanoTime();
                            if (onDelete.deletedTime / 10000000 == onDelete.createdTime / 10000000) {
                                onDelete.createdFile = watchEvent.context().toString();
                                onDelete.status++;
                                notifications.add("File Created:" + fileName);
                            }else{
                                for (String string : notifications) {
                                    System.out.println(string);
                                }
                                notifications = new ArrayList<String>();
                                System.out.println("File Created:" + fileName + " "
                                        + watchEvent.context());
                                onDelete = new FileDelete();  //Time duration not close (seems not renamed)
                            }
                            }else{
                                //this should never come here!!
                                onDelete = new FileDelete();
                            }
                        }
                    }
                    if (StandardWatchEventKinds.ENTRY_DELETE.equals(watchEvent
                            .kind())) {
                        String fileName = watchEvent.context().toString();
                        if(onDelete.status == -1){
                            onDelete = new FileDelete();
                            onDelete.status++;
                            onDelete.deletedFile = watchEvent.context().toString();
                            onDelete.deletedTime = System.nanoTime();
                            notifications.add("File deleted:" + fileName);
                        }
                        //System.out.println("File deleted:" + fileName);   // push to notfication to array for later use
                    }
                    if (StandardWatchEventKinds.ENTRY_MODIFY.equals(watchEvent
                            .kind())) {
                        long current = System.nanoTime();
                        String fileName = watchEvent.context().toString();
                        if(!(preventDuplicateTime/10000000 == current/10000000))
                            notifications.add("File modified:" + fileName);
                        preventDuplicateTime = (System.nanoTime());
                        onDelete.modifiedFile= fileName;
                        onDelete.modifiedTime =System.nanoTime();
                        if(onDelete.status != 1){
                            for (String messages : notifications) {
                                System.out.println(messages);
                            }
                        onDelete= new FileDelete();
                        notifications = new ArrayList<String>();
                        }
                        else if(onDelete.createdFile.equals(onDelete.modifiedFile))
                                if( onDelete.createdTime /10000000 == onDelete.modifiedTime/10000000){
                                    System.out.println("File renamed:" + fileName);
                                    onDelete = new FileDelete();
                                    notifications = new ArrayList<String>();
                         }
                    }
                /*}*/

            }
            isKeyValid = myPathWatchKey.reset();
        }
    }
}