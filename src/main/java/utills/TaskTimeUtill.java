package utills;

import entity.TaskDescription;
import entity.Tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by nikita93 on 03.07.2017.
 */
public class TaskTimeUtill {
    public static ArrayList<TaskDescription> getClosingTasks(Tasks tasks) throws ParseException {
        ArrayList <TaskDescription> outTaskDescriptions = new ArrayList<TaskDescription>();
        Date dateNow = new Date();
        ArrayList<TaskDescription> taskDescriptions = tasks.getTasks();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd', 'HH:mm");

        for (TaskDescription taskDescription :
                taskDescriptions) {
            if (taskDescription.getSolving_date() != "") {
                System.out.println(taskDescription.getSolving_date());
                Date date = simpleDateFormat.parse(taskDescription.getSolving_date());
                Long diff = date.getTime() - dateNow.getTime();
                if ( (diff/1000 <= 2700) && (diff > 0) ) {
                    outTaskDescriptions.add(taskDescription);
                }
            }
        }
        return outTaskDescriptions;
    }
}
