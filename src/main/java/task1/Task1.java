package task1;

public class Task1 {
    public static void main(String[] args) {
//        MessengerOfThread messengerOfThread = new MessengerOfThread();
        MessengerOfThread messengerOfThread = new MessengerOfThread(1, 3);
        messengerOfThread.startThread();
    }
}
