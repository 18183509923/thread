import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * 遍历指定目录（含子目录）找寻指定类型文件
 * 使用RecursiveAction,无需返回值
 */
public class FindDirsFiles extends RecursiveAction{
    //当前任务搜寻的目录
    private File path;

    public FindDirsFiles(File path) {
        this.path = path;
    }

    public static void main(String[] args) {
        try {


            //用一个FOrkJoinPool实例调度总任务
            ForkJoinPool pool = new ForkJoinPool();
            FindDirsFiles task = new FindDirsFiles(new File("G:/"));

            //异步调用
            pool.execute(task);
            System.out.println("task在运行");
            Thread.sleep(1);
            int otherWork = 0;
            for (int i = 0; i < 100; i++) {
                otherWork = otherWork + i;
            }
            System.out.println("主线程做统计" + otherWork);
            //阻塞的方法
            task.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void compute() {
        List<FindDirsFiles> subTasks = new ArrayList<>();

        File[] files = path.listFiles();
        if (files!=null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    subTasks.add(new FindDirsFiles(file));
                }else {
                    if(file.getAbsolutePath().endsWith("txt")) {
                        System.out.println("文件"+file.getAbsolutePath());
                    }
                }
            }
            if(!subTasks.isEmpty()) {
                for (FindDirsFiles subTask : invokeAll(subTasks)){
                    //等待子任务执行完成
                    subTask.join();
                }
            }
        }
    }
}
