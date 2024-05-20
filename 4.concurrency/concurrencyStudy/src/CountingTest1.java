public class CountingTest1 {
    public static void main(String[] args) {
        Count1 count = new Count1();
        for (int i = 0; i < 100; i++) {
            new Thread(){
                public void run(){
                    for (int j = 0; j < 100; j++) {
                        System.out.println(count.view());
                    }
                }
            }.start();
        }
    }
}
class Count1 {
    private int count;
    public int view() {return count++;}
    public int getCount() {return count;}
}