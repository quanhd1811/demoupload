package com.example.helloworld.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Service
public class FirebaseInitialization {
    @PostConstruct
    public void initialization() {
//        ClassLoader classLoader = getClass().getClassLoader();
        Resource resource = new ClassPathResource("serviceAccountKey.json");
        FileInputStream serviceAccount = null;
        try {
            //classLoader.getResouces("serviceAccountKey.json).getFile
            serviceAccount = new FileInputStream(resource.getFile());
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
