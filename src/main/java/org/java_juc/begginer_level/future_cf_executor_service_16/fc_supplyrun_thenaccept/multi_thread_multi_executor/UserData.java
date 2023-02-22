package org.java_juc.begginer_level.future_cf_executor_service_16.fc_supplyrun_thenaccept.multi_thread_multi_executor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_juc.begginer_level.future_cf_executor_service_16.completable_future.model.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UserData {
    public static List<User> getAllUsers() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            final List<User> users = mapper.readValue(new File("user_data.json"), new TypeReference<List<User>>() {
            });
            return users;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
