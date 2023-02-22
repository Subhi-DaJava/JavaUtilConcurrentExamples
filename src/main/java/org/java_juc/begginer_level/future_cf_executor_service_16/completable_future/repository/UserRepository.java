package org.java_juc.begginer_level.future_cf_executor_service_16.completable_future.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_juc.begginer_level.future_cf_executor_service_16.completable_future.model.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UserRepository {
    public static List<User> getUsers() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File("user_data.json"),  new TypeReference<List<User>>(){});
        } catch (IOException e) {
           e.printStackTrace();
        }
        return null;
    }
}
