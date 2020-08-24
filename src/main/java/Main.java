import static spark.Spark.*;
import com.webapi;
import org.json.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Main {

    public static String sayHello(){
        return "Good morning";
    }

    public static String sayHello(String name){
        return "Good morning, "+name;
    }

    public static void main(String args[]){


//        HashMap<String, String> map=new HashMap<String, String>();
//        map.put("asd","asd");
//        map.put("cbd","cbd");
//        map.put("xx","xx");
//        map.put("xx","kk");
//        for (String key : map.keySet()){
//            System.out.println(key+ map.get(key));
//        }
//
//        Iterator<Map.Entry<String, String>> itr=map.entrySet().iterator();
//        while (itr.hasNext()){
//            Map.Entry<String, String> entry=itr.next();
//            System.out.println(entry.getKey()+ entry.getValue());
//        }
//        System.exit(0);


        





        webapi api =new webapi();
        port(3030);
        String[] myArr={"1","2","3"};
//        api.main(myArr);
        get("/hello", (req, res)->sayHello());

        get("/hello/:name",(req,res)->sayHello(req.params("name")));

        JSONObject obj = new JSONObject();
        obj.put("title", "First title");
        obj.put("date", "2020-10-09");
        obj.put("addr", "./blog/post.1.md");

        get("/post/:name",(req,res)->obj);
    }

}
