package org.openiam.base.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class PropertyMapAdapter extends XmlAdapter<PropertyMap, Map<String, List<String>>>  {

    @Override
    public PropertyMap marshal(Map<String, List<String>> v) throws Exception {
        PropertyMap map = new PropertyMap();
        if (v == null) return map;

        for (Map.Entry<String, List<String>> e : v.entrySet()) {
            PropertyMap.PropertyEntry entry = new PropertyMap.PropertyEntry();
            entry.setValues(e.getValue());
            entry.setKey(e.getKey());
            map.getPropertyEntry().add(entry);
        }
        return map;
    }

    @Override
    public Map<String, List<String>> unmarshal(PropertyMap v) throws Exception {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        if (v == null) return map;

        for (PropertyMap.PropertyEntry e : v.getPropertyEntry()) {
            map.put(e.getKey(), e.getValues());
        }
        return map;
    }
}
