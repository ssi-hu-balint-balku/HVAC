public class HvacApp {

    private static boolean isServer;
    private static int high;
    private static int low;

    public static void main(String[] args) {

        for (String arg : args) {
            System.out.println("arg: " + arg);
        }

        isServer = System.getProperties().getProperty("server").equals("true");
        high = Integer.valueOf(System.getProperties().getProperty("high"));
        low = Integer.valueOf(System.getProperties().getProperty("low"));

        System.out.println("isServer: " + isServer);
        System.out.println("high: " + high);
        System.out.println("low: " + low);
    }

}
