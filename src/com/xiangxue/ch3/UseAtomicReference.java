import java.util.concurrent.atomic.AtomicReference;

/**
 * 演示引用类型的原子操作类
 */
public class UseAtomicReference {

    static AtomicReference<UserInfo> useRef = new AtomicReference<UserInfo>();

    public static void main(String[] args) {
        //要修改的实体的实例
        UserInfo user = new UserInfo("Mark",15);
        useRef.set(user);
        //要变化的新实例
        UserInfo updateUser = new UserInfo("yan",25);
        useRef.compareAndSet(user,updateUser);
        System.out.println(useRef.get().getName());
        System.out.println(useRef.get().getAge());
        System.out.println(user.getName());
        System.out.println(user.getAge());
    }

    //定义一个实体类
    static class UserInfo{
        private String name;
        private int age;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public UserInfo(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
