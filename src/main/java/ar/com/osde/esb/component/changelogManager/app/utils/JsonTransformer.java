package ar.com.osde.esb.component.changelogManager.app.utils;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer{

    private Gson gson;

    public JsonTransformer(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String render(Object model) {
        return this.gson.toJson(model);
    }

}
