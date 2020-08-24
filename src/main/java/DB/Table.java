package DB;
import sun.tools.jconsole.Tab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Table {
    private String buildString;
    private String tableName;
    private Map<String, String> properties;

    private Table(TableBuilder tablebuilder){
        this.buildString= tablebuilder.buildString;
        this.tableName=tablebuilder.tableName;
        this.properties=tablebuilder.properties;
        if (this.properties.isEmpty()){
            this.buildString = "create table "+this.tableName+";";
        }
        this.buildString="create table "+this.tableName+" (";
        for(String p: this.properties.keySet() ){
            this.buildString+=p+" "+this.properties.get(p)+", ";
        }
        this.buildString+=");";
    }


    public String getString(){
        return this.buildString;
    }


    public static class TableBuilder{
        private String buildString;
        private String tableName;
//        private ArrayList properties;
        private Map<String, String> properties;

        public TableBuilder(String tableName){
            this.tableName=tableName;
            this.properties=new HashMap<String, String>();
        }

        public TableBuilder addProperty(String property, String type){
            if (this.properties.containsKey(property)){
                this.properties.put(property, type);
            }
            else{
                this.properties.put(property, type);
            }
            return this;
        }

        public Table build(){
            return new Table(this);
        }
    }
}
