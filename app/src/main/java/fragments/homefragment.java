package fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.MJ.Hack.Sehat.R;
import com.MJ.Hack.Sehat.searchadapter;

import static com.MJ.Hack.Sehat.ContainerActivity.articles;

public class homefragment extends Fragment {


    public RecyclerView recyclerView;
    String result = "";
    public com.MJ.Hack.Sehat.searchadapter searchadapter;
    String farmerjson;
   // public List<Article> articles;
    public static ProgressBar progressBar;
    androidx.appcompat.widget.SearchView searchView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment,container,false);
        progressBar=view.findViewById(R.id.searchprogress);
        recyclerView=view.findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        //allfarmers.add(new Farmer("Budhimata Paradha", "9411822210", "jharkhand"));

        progressBar.setVisibility(View.INVISIBLE);

        searchadapter=new searchadapter(getActivity(), articles);
        recyclerView.setAdapter(searchadapter);
        searchadapter.notifyDataSetChanged();

        return  view;

    }





}