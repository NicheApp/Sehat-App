package fragments;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.MJ.Hack.Sehat.Adapter;
import com.MJ.Hack.Sehat.ContainerActivity;
import com.MJ.Hack.Sehat.Note;
import com.MJ.Hack.Sehat.NoteAdapter;
import com.MJ.Hack.Sehat.R;
import com.MJ.Hack.Sehat.yogaadapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mapzen.speakerbox.Speakerbox;

import java.util.ArrayList;
import java.util.List;

import static com.MJ.Hack.Sehat.R.layout.fui_dgts_country_row;
import static com.MJ.Hack.Sehat.R.layout.health;
import static com.MJ.Hack.Sehat.R.layout.showyoga;

public class Health extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference DiseaseRef = db.collection("Disease");
    yogaadapter adapter;
    List<Note> yogalist;
    RecyclerView recyclerView;
    String item;

    ViewPager viewPager;
    Adapter madapter;
    List<Note> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    public  Health(String item)
    { this.item=item;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v=  inflater.inflate(showyoga,container,false);
       // Toast.makeText(getContext(),item,Toast.LENGTH_SHORT).show();
        recyclerView = v.findViewById(R.id.recycler_view);
        yogalist=new ArrayList<>();
        models = new ArrayList<>();
        viewPager = v.findViewById(R.id.viewPager);
        DiseaseRef.document(item).collection("yoga").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
       @Override
       public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
           for(QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots)
           {
               Note note=queryDocumentSnapshot.toObject(Note.class);
               yogalist.add(new Note(queryDocumentSnapshot.get("yoga").toString(),queryDocumentSnapshot.get("desc").toString(),queryDocumentSnapshot.get("link").toString()));

           }
         Speakerbox speakerbox =new Speakerbox(getActivity().getApplication());
           madapter=new Adapter(yogalist,getContext(),speakerbox);
           viewPager.setAdapter(madapter);
           viewPager.setPadding(130, 0, 130, 0);

           viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
               @Override
               public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                   if (position < (madapter.getCount() - 1) && position < (colors.length - 1)) {
                       viewPager.setBackgroundColor(

                               (Integer) argbEvaluator.evaluate(
                                       positionOffset,
                                       colors[position],
                                       colors[position + 1]
                               )
                       );
                   } else {
                       viewPager.setBackgroundColor(colors[colors.length - 1]);
                   }
               }

               @Override
               public void onPageSelected(int position) {

               }

               @Override
               public void onPageScrollStateChanged(int state) {

               }
           });
           Integer[] colors_temp = {
                   getResources().getColor(R.color.color1),
                   getResources().getColor(R.color.color2),
                   getResources().getColor(R.color.color3),
                   getResources().getColor(R.color.color4)
           };

 colors = colors_temp;
 /*
           adapter = new yogaadapter(getContext(),yogalist);
           recyclerView.setHasFixedSize(true);
           recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
           recyclerView.setAdapter(adapter);*/

       }
   });

       return v;

    }
}
