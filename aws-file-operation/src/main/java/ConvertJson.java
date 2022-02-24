import com.google.gson.Gson;

import java.sql.SQLOutput;

public class ConvertJson {

    public ConvertJson() {

    }

    public boolean convertJson(String jsonAsString) throws Exception {
        final Gson gson = new Gson();
        try{
            TestObj obj=gson.fromJson(jsonAsString, TestObj.class);
            System.out.println(obj);
        }
        catch (Exception e){
            System.out.println("Error while converting");
        }
        return true;
    }

}
