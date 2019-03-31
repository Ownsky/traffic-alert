package pers.ownsky.trafficalert.dataaccess.datasource;

public class DataSourceContextHolder {
    private static final ThreadLocal<String> datasourceTL = new ThreadLocal<>();
    private static final ThreadLocal<String> contextTL = new ThreadLocal<>();

    public static void putDataSource(String name, String context) {
        if (datasourceTL.get() != null) return;
        datasourceTL.set(name);
        contextTL.set(context);
//        System.out.println(context+name);
    }

    public static String getDataSource() {
        return datasourceTL.get();
    }

    public static void removeDataSource(String context) {
        if (!context.equals(contextTL.get())) return;
//        System.out.println(datasourceTL.get()+context);
        datasourceTL.remove();
        contextTL.remove();
    }
}
