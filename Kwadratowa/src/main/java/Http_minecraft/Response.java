package Http_minecraft;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Response
{
    public String Message;
    public String Status;

    public Response(String Message, String Status)
    {
        this.Message = Message;
        this.Status = Status;
    }
    public Response()
    {

    }
    public static JsonObject ParseData(String Data)
    {
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(Data);
        jsonParser = new JsonParser();
        return (JsonObject) jsonParser.parse(jsonArray.get(0).toString());
    }
}
