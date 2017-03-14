import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MovingFilesTask implements Runnable {
    FileOperations fileOperations;
    List<ActionListener> listenerses=new ArrayList<>();

    public MovingFilesTask(FileOperations fileOperations) {
        this.fileOperations = fileOperations;
    }

    @Override
    public void run() {
        fileOperations.moveFiles();

        for(ActionListener actionListener:listenerses ){
            actionListener.actionPerformed(new ActionEvent(this,0,"end"));
        }
    }
    public void addActionListener(ActionListener actionListener){
        listenerses.add(actionListener);
    }



}
