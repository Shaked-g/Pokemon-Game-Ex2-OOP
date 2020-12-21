package api;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;



public class GraphJsonDeserializer implements JsonDeserializer<directed_weighted_graph>
{


    @Override
    public directed_weighted_graph deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2) throws JsonParseException
    {

        directed_weighted_graph g=new DWGraph_DS();
        JsonArray array=json.getAsJsonArray();
        int src,dest;
        double weight;
        for(int i=0;i<array.size();i++)
        {
            JsonObject jsonObject = array.get(i).getAsJsonObject();
            src = jsonObject.get("src").getAsInt();
            dest = jsonObject.get("dest").getAsInt();
            weight = jsonObject.get("weight").getAsDouble();
            g.addNode(new NodeData(src));
            g.addNode(new NodeData(dest));
            g.connect(src, dest, weight);


        }
        return g;

    }

}

