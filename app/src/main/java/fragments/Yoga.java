package fragments;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.MJ.Hack.Sehat.ContainerActivity;
import com.MJ.Hack.Sehat.Note;
import com.MJ.Hack.Sehat.NoteAdapter;
import com.MJ.Hack.Sehat.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class Yoga extends Fragment {
    @Nullable
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference DiseaseRef = db.collection("Disease");
    private NoteAdapter adapter;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    public List<String > Diseaselist;
    EditText inputSearch;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=  inflater.inflate(R.layout.yoga, container, false);
        listView = (ListView) v.findViewById(R.id.mobile_list);
        inputSearch = (EditText) v.findViewById(R.id.inputSearch);
        Diseaselist=new ArrayList<>();
       // Query query = DiseaseRef.orderBy("priority", Query.Direction.DESCENDING);
    /*Query query=db.collection("Disease");
    Toast.makeText(getContext(),query.get().toString(),Toast.LENGTH_SHORT).show();
        FirestoreRecyclerOptions<String> options = new FirestoreRecyclerOptions.Builder<String>()
                .setQuery(query, String.class)
                .build();
        adapter = new NoteAdapter(options);
        RecyclerView recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);*/
        db.collection("Disease").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {

                        Diseaselist.add(document.getId());

                    }
                  arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, Diseaselist);

                    listView.setAdapter(arrayAdapter);
                    inputSearch.addTextChangedListener(new TextWatcher() {

                        @Override
                        public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                            // When user changed the Text
                            arrayAdapter.getFilter().filter(cs);

                        }

                        @Override
                        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                                      int arg3) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void afterTextChanged(Editable arg0) {
                            // TODO Auto-generated method stub
                        }
                    });
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedItem = (String) parent.getItemAtPosition(position);

                            Fragment someFragment = new Health(selectedItem);
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container, someFragment ); // give your fragment container id in first parameter
                            transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                            transaction.commit();


                        }
                    });
                    Log.d(TAG, Diseaselist.toString());
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });


        return v;
    }


}
