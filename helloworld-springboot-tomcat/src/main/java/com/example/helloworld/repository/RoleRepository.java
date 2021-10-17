package com.example.helloworld.repository;

import com.example.helloworld.entity.Role;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class RoleRepository {

    private static final String COLLECTION_NAME ="role";

    public Role getRoleById(int id) throws InterruptedException, ExecutionException{
        Role role = null;
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference roleRef = db.collection(COLLECTION_NAME);
        Query query = roleRef.whereEqualTo("id",id);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        if(!documents.isEmpty()){
            role = documents.get(0).toObject(Role.class);
            return role;
        }else return role;
    }

}
