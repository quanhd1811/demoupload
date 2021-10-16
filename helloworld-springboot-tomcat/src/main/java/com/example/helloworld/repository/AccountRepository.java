package com.example.helloworld.repository;

import com.example.helloworld.entity.Account;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Repository
public class AccountRepository {

    private static final String COLLECTION_NAME = "account";

    public Account getAccountByPhone(String phone) throws ExecutionException,InterruptedException{
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference accountRef = db.collection(COLLECTION_NAME);
        Query query = accountRef.whereEqualTo("phone",phone);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        Account account = null;
        if(!documents.isEmpty()){
            account = documents.get(0).toObject(Account.class);
            account.setId(documents.get(0).getId());
            return account;
        }
        return account;
    }

}
