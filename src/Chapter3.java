import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@FunctionalInterface
interface BufferedReaderProcessor {
    String process(BufferedReader b) throws IOException;
}

public class Chapter3 {
    void all_execute(){
        func3_2_1();
        func3_3();
        func3_3_3();
    }

    void func3_2_1() {
        Runnable r1 = ()-> {
            System.out.println("Hello World 1");
        };
        Runnable r2 = new Runnable() {
            public void run() {
                System.out.println("Hello World 2");
            }
        };
        process(r1);
        process(r2);
        process(()->System.out.println("Hello World 3"));
    }

    void func3_3(){
        System.out.println("===chapter3_3_1");
        try{
            System.out.println(processFile());
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    void func3_3_3(){
        System.out.println("3.3.3 test");
        try {
            String oneLine = processFile((BufferedReader br) -> br.readLine());
            System.out.println(oneLine);
            System.out.println("=====");

            String result = processFile((BufferedReader br) -> br.readLine() +" and " +  br.readLine());
            System.out.println(result);

            System.out.println("===All line Read");
            String allLine = processFile((BufferedReader br) -> {
                String res = new String();
                while(true) {
                    String readLine = br.readLine();
                    if(readLine == null) break;
                    res += readLine + ", ";
                }
                return res;
            });
            System.out.println(allLine);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void process(Runnable r) {
        r.run();
    }

    public String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("C:\\Users\\SD\\Desktop\\Git\\modern_in_java\\Modern-Java-in-Action-Study\\src\\data.txt"))) {
            return p.process(br);
        }
    }

    public String processFile() throws IOException {
        try (BufferedReader br =
                new BufferedReader(new FileReader("C:\\Users\\SD\\Desktop\\Git\\modern_in_java\\Modern-Java-in-Action-Study\\src\\data.txt"))) {
            return br.readLine();
        }
    }
}
