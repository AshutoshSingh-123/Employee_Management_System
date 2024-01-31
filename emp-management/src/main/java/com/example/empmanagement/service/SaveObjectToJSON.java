package com.example.empmanagement.service;
import com.example.empmanagement.util.RecentActivity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Service
public class SaveObjectToJSON {

    public void saveActivity(RecentActivity obj) throws IOException {
        List<RecentActivity> ll=getAllActivities();
        ll.add(obj);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(Paths.get("recentActivities.json").toFile(), ll);
    }

    public List<RecentActivity> getAllActivities() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(Paths.get("recentActivities.json").toFile(), new TypeReference<List<RecentActivity>>(){});
    }
}
